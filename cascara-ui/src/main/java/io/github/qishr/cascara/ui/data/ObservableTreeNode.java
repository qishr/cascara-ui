// # License & Terms
//
// This file is part of **Cascara**.
//
// **Cascara** is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see <https://www.gnu.org/licenses/>.
//
// ---
//
// ## Special Runtime Exception
//
// As a special exception, the copyright holders of this library give you
// permission to link this library with independent modules to produce an
// executable, regardless of the license terms of these independent modules,
// and to copy and distribute the resulting executable under terms of your
// choice, provided that you also meet, for each linked independent module,
// the terms and conditions of the license of that module.
//
// An independent module is a module which is not derived from or based on
// this library. If you modify this library, you may extend this exception
// to your version of the library, but you are not obligated to do so. If
// you do not wish to do so, delete this exception statement from your
// version.


package io.github.qishr.cascara.ui.data;

import java.util.HashMap;
import java.util.Map;

import io.github.qishr.cascara.common.lang.type.PrimitiveType;
import io.github.qishr.cascara.schema.annotation.SchemaProperty;
import io.github.qishr.cascara.schema.structure.SchemaNode;
import io.github.qishr.cascara.ui.api.data.ObservableTreeData;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public abstract class ObservableTreeNode<T extends ObservableTreeNode<T,V>,V extends Object> extends ObservableObject implements ObservableTreeData<T,V> {
    private final ObjectProperty<PrimitiveType> schemaType = new SimpleObjectProperty<>();
    private final ObjectProperty<SchemaNode> schema = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<V> physicalValue;
    private final SimpleObjectProperty<T> parent;
    private final ObservableList<T> children = FXCollections.observableArrayList();

    @SchemaProperty
    private ObjectProperty<String> nodeName; // AbstractObservableAstNode.NODE_NAME_PROPERTY imust match this exactly

    public ObservableTreeNode(T parent, String nodeName) {
        super();
        this.nodeName.set(nodeName);
        this.parent = new SimpleObjectProperty<>(this, "parent", parent);
        this.physicalValue = new SimpleObjectProperty<>(parent, "physicalValue");
        setupListeners();
    }

    protected abstract T self();

    //
    // Properties
    //

    public ObjectProperty<PrimitiveType> schemaTypeProperty() { return schemaType; }
    public ObjectProperty<PrimitiveType> typeProperty() { return schemaType; }
    public ObjectProperty<SchemaNode> schemaProperty() { return schema; }

    public final ObjectProperty<String> nodeNameProperty() {return nodeName;}
    public final SimpleObjectProperty<T> parentProperty() {return parent;}
    public SimpleObjectProperty<V> payloadProperty() { return physicalValue; }

    //
    // Identity
    //

    public final String getNodeName() {return this.nodeName.get();}
    public final void setNodeName(String v) {this.nodeName.set(v);}

    //
    // Schema
    //

    public SchemaNode getSchema() {
        return schema.get();
    }
    public void setSchema(SchemaNode schema) { this.schema.set(schema); }

    public final PrimitiveType getSchemaType() {
        return schema.get() == null ? PrimitiveType.NULL : schema.get().getType();
    }

    //
    // Structure
    //

    /// Called when a child is added to this node.
    protected void onChildAdded(T node) {}

    /// Called when a child is removed from this node.
    protected void onChildRemoved(T node) {}

    /// Called when the value held by this node changes.
    protected void onValueChanged(V value) {}

    public T getParent() {return this.parent.get();}

    public ObservableList<T> getChildren() { return children; }

    public final void setParent(T v) {
        this.parent.set(v);
    }

    public T getChild(String name) {
        for (T child : children) {
            if (child.internalGetName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    @Override
    public boolean isBranch() {
        // If the list has items, it's a branch.
        if (!children.isEmpty()) {
            return true;
        }

        // Otherwise, ask the specific implementation if it "expects" children.
        // This defaults to false so non-lazy trees don't show phantom arrows.
        return canHaveChildren();
    }

    /// Override this only in classes that load data on demand.
    protected boolean canHaveChildren() {
        return false;
    }

    public String getTreePathInsertParent(String parentName) {
        String path = getTreePath();
        int i = path.indexOf("/");
        String root = path.substring(0, i);
        String subPath = path.substring(i);
        return root + "/" + parentName + subPath;
    }

    public String getTreePath() {
        String path = internalGetName();
        ObservableTreeNode<?,?> current = this;

        while (current.getParent() != null) {
            current = current.getParent();
            if (current.getParent() == null) {
                path = "#/" + path;
            } else {
                path = current.internalGetName() + "/" + path;
            }
        }

        return path;
    }

    public T getByPath(String path) {
        if (path.startsWith("#")) {
            path = path.substring(1);
        }
        String[] segments = path.split("/");
        T currentItem = self();
        for (String segment : segments) {
            if (segment.isEmpty()) continue;

            if (currentItem.getChild(segment) instanceof T item) {
                currentItem = item;
            } else {
                return null;
            }
        }
        return currentItem;
    }

    //
    // Values
    //

    public V getPayload() {
        return physicalValue.getValue();
    }

    public void setPayload(V v) {
        physicalValue.setValue(v);
    }

    public Map<String,Property<?>> getChildMap() {
        return propertyMap(this);
    }

    @Override
    public Map<String,Property<?>> getDataContext() {
        return propertyMap(getParent());
    }

    private Map<String,Property<?>> propertyMap(ObservableTreeNode<T,V> parent) {
        Map<String,Property<?>> contextMap = new HashMap<>();
        if (parent != null) {
            for (ObservableTreeNode<T,V> child : parent.getChildren()) {
                contextMap.put(child.internalGetName(), child.payloadProperty());
            }
        }
        return contextMap;
    }

    //
    // Private Methods
    //

    String internalGetName() {
        return this.nodeName.get();
    }

    private void setupListeners() {
        physicalValue.addListener((obs,old,val) -> {
            onValueChanged(val);
        });
        getChildren().addListener((ListChangeListener.Change<? extends T> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (T item : c.getAddedSubList()) {
                        item.setParent(self());
                        onChildAdded(item);
                    }
                }
                if (c.wasRemoved()) {
                    for (T item : c.getRemoved()) {
                        item.setParent(null);
                        onChildRemoved(item);
                    }
                }
            }
        });
    }
}
