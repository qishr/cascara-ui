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


package io.github.qishr.cascara.ui.control;

import io.github.qishr.cascara.ui.style.custom.DocumentTabHeaderStyle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ResourceTabPane extends VBox {
    private TabPane tabPane = null;
    private ObservableList<ResourceTab> allTabs = FXCollections.observableArrayList();
    protected final ObjectProperty<ResourceTab> selectedTab = new SimpleObjectProperty<>();


    public ObjectProperty<ResourceTab> selectedTabProperty() {return selectedTab;}
    public ResourceTab getSelectedTab() {return this.selectedTab.get();}
    public void setUri(ResourceTab v) {this.selectedTab.set(v);}


    public ResourceTabPane() {
        tabPane = new TabPane();
        tabPane.getStyleClass().setAll(DocumentTabHeaderStyle.DOCUMENT_TAB_PANE);
        tabPane.getSelectionModel().selectedItemProperty().addListener(this::onSelectionChanged);
        VBox.setVgrow(tabPane, Priority.ALWAYS);
        HBox.setHgrow(tabPane, Priority.ALWAYS);
        getChildren().add(tabPane);
    }

    private void onSelectionChanged(ObservableValue<? extends Tab> obs, Tab old, Tab tab) {
        selectedTab.set((ResourceTab)tab);
    }

    public void selectTab(ResourceTab tab) {
        tabPane.getSelectionModel().select(tab);
    }

    public ObservableList<ResourceTab> getTabs() {
        return allTabs;
    }

    public ResourceTab createTab(boolean isTransient) {
        ResourceTab tab = new ResourceTab(tabPane);
        tab.setTransient(isTransient);

        tab.setPinned(false);

        // TODO: Bind this to ducment edited property
        tab.setModified(false);

        // tab.setOnContextMenuRequested(contextMenu -> {
        //     showContextMenu(contextMenu, tab);
        // });
        tabPane.getTabs().add(tab);
        allTabs.add(tab);
        return tab;
    }

    public void removeTab(ResourceTab tab) {
        tabPane.getTabs().remove(tab);
        allTabs.remove(tab);
    }
}
