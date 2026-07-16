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


package io.github.qishr.cascara.ui.render.control;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.github.qishr.cascara.ui.api.data.DataProvider;
import io.github.qishr.cascara.ui.api.render.ArrayEditorRenderer;
import io.github.qishr.cascara.ui.control.TagChooser;
import io.github.qishr.cascara.ui.form.FieldMetadata;
import io.github.qishr.cascara.ui.option.TagOption;
import io.github.qishr.cascara.ui.render.AbstractArrayRenderer;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Labeled;

public class TagChooserRenderer extends AbstractArrayRenderer implements ArrayEditorRenderer {

    public TagChooserRenderer() {
        super("cascara/tag", null, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Node render(Labeled view, @SuppressWarnings("rawtypes") ObservableList list, DataProvider dataProvider, FieldMetadata meta) {

        // OptionListEditor editor = provider.get();

        final AtomicBoolean isUpdating = new AtomicBoolean(false);
        final ObservableList<TagOption> uiSelectedIds = FXCollections.observableArrayList();

        TagChooser editor = new TagChooser(uiSelectedIds, meta.getOptionProvider(), meta.getProviderParameter());
        editor.setPrefHeight(10);


        // Helper to sync from AST to UI
        final Runnable syncFromAst = () -> {
            if (isUpdating.get()) return;
            List<TagOption> current = new ArrayList<>();
            for (Object o : list) {
                current.add((TagOption)o);
            }
            // Prevent recursive listener triggers
            if (!uiSelectedIds.equals(current)) {
                uiSelectedIds.setAll(current);
            }
        };
        syncFromAst.run();

        // UI -> AST: Listen to the TagChooser's list
        uiSelectedIds.addListener((ListChangeListener.Change<? extends TagOption> c) -> {
            if (isUpdating.get()) return;
            isUpdating.set(true);
            try {
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (TagOption id : c.getAddedSubList()) {
                            boolean exists = list.contains(id);
                            if (!exists) {
                                try {
                                    list.add(id);
                                } catch (UnsupportedOperationException e) {
                                    // System.out.println("Debug AFF: " + list.getClass().getName());
                                }
                            }
                        }
                    }
                    if (c.wasRemoved()) {
                        for (TagOption id : c.getRemoved()) {
                            try {
                                list.remove(id);
                            } catch (UnsupportedOperationException e) {
                                // System.out.println("Debug AFF: " + list.getClass().getName());
                            }
                        }
                    }
                }
                // notifyDocumentChanged(false);
                if (meta.getOnChange() != null) {
                    meta.getOnChange().run();
                }
            } finally {
                isUpdating.set(false);
            }
        });

        // AST -> UI: Listen for external changes (e.g., Undo, Table edits)
        list.addListener(new ListChangeListener<TagOption>() {
            public void onChanged(Change<? extends TagOption> c) {
                if (!isUpdating.get()) {
                    syncFromAst.run();
                }
            }
        });

        // editor.configure(uiSelectedIds, meta.getOptionProvider(), meta.getProviderParameter());
        // if (editor instanceof Node control) {
        //     return control;
        // }

        view.setGraphic(editor);
        view.setText(null);
        return editor;
    }
}
