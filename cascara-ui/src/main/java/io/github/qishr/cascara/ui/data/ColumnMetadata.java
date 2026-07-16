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

import java.util.Comparator;

import io.github.qishr.cascara.common.lang.type.ScalarDescriptor;
import io.github.qishr.cascara.common.lang.type.TypeDescriptor;
import io.github.qishr.cascara.schema.structure.SchemaNode;
import io.github.qishr.cascara.ui.api.render.Renderer;
import io.github.qishr.cascara.ui.api.render.ScalarEditorRenderer;
import io.github.qishr.cascara.ui.api.render.ScalarRenderer;
import io.github.qishr.cascara.ui.form.FieldMetadata;
import io.github.qishr.cascara.ui.language.Localization;
import io.github.qishr.cascara.ui.render.RendererAllocator;
import io.github.qishr.cascara.ui.render.RendererFactory;
import io.github.qishr.cascara.ui.render.Renderers;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ColumnMetadata extends FieldMetadata {

    private Comparator<ObservableValue<?>> comparator;
    private TypeDescriptor<?> typeDescriptor;

    private String headerStyle = "";
    private String cellStyle = "";
    private double minWidth = -1;
    private double prefWidth = -1;
    private double maxWidth = -1;


    public ColumnMetadata(String name, String title, SchemaNode schema,
                RendererFactory rendererFactory,
                Callback<TableColumn<ObservableObject, Object>, TableCell<ObservableObject, Object>> cellFactory) {

        super(name, schema, rendererFactory);
        if (title != null) setTitle(title);
        setRenderers(new RendererAllocator(rendererFactory).allocate(this));
    }

    public ColumnMetadata(String name, String title, SchemaNode schema,
                RendererFactory rendererFactory) {

        super(name, schema, rendererFactory);
        if (title != null) setTitle(title);
        setRenderers(new RendererAllocator(rendererFactory).allocate(this));
    }

    public ColumnMetadata(String name, String title, Renderer renderer) {
        super(name, null, null);
        if (renderer instanceof ScalarEditorRenderer s) {
            this.setRenderers(new Renderers(null, s, null));
            this.allowEdit = true;
        } else if (renderer instanceof ScalarRenderer s) {
            this.setRenderers(new Renderers(s, null, null));
            this.allowEdit = false;
        }
        if (title != null) setTitle(title);
    }

    public ColumnMetadata(String name, String title) {
        super(name, null, null);
        if (title != null) setTitle(title);
    }

    public ColumnMetadata(String name) {
        super(name, null, null);
    }

    public ColumnMetadata bindTitle(String key) {
        Localization.bind(titleProperty(), key);
        return this;
    }

    public TypeDescriptor<?> getTypeDescriptor() { return typeDescriptor; }
    public Comparator<ObservableValue<?>> getComparator() {
        if (comparator == null) {
            configureComparator();
        }
        return comparator;
    }
    public double getMinWidth() { return minWidth; }
    public double getPrefWidth() { return prefWidth; }
    public double getMaxWidth() { return maxWidth; }
    public String getCellStyle() { return cellStyle; }
    public String getHeaderStyle() { return headerStyle; }

    public ColumnMetadata setTypeDescriptor(TypeDescriptor<?> v) { typeDescriptor = v; return this;}
    public ColumnMetadata setComparator(Comparator<ObservableValue<?>> v) { comparator = v; return this; }
    public ColumnMetadata setMinWidth(double value) { this.minWidth = value; return this; }
    public ColumnMetadata setPrefWidth(double value) { this.prefWidth = value; return this; }
    public ColumnMetadata setMaxWidth(double value) { this.maxWidth = value; return this; }
    public ColumnMetadata setCellStyle(String value) { this.cellStyle = value; return this; }
    public ColumnMetadata setHeaderStyle(String value) { this.headerStyle = value; return this; }
    public ColumnMetadata setAllowEdit(boolean v) { allowEdit = v; return this; }

    @SuppressWarnings("unchecked")
    private void configureComparator() {
        comparator = (ObservableValue<?> v1, ObservableValue<?> v2) -> {
            Object o1 = v1.getValue();
            Object o2 = v2.getValue();

            if (typeDescriptor instanceof ScalarDescriptor descriptor) {
                String s1 = descriptor.toPrimitive(o1).toString();
                String s2 = descriptor.toPrimitive(o2).toString();

                return s1.compareTo(s2);
            }

            if (o1 instanceof Comparable c1 && o2 instanceof Comparable c2) {
                return c1.compareTo(c2);
            }

            return 0;
        };
    }
}
