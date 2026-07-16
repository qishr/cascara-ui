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

import java.net.URI;

import io.github.qishr.cascara.common.lang.type.PrimitiveType;
import io.github.qishr.cascara.ui.api.data.DataProvider;
import io.github.qishr.cascara.ui.api.render.ScalarRenderer;
import io.github.qishr.cascara.ui.form.FieldMetadata;
import io.github.qishr.cascara.ui.render.AbstractScalarRenderer;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Labeled;

public class UriRenderer extends AbstractScalarRenderer implements ScalarRenderer {
    public UriRenderer() {
        super(null, PrimitiveType.STRING, "uri");
    }

    @Override
    public Node render(Labeled view, Object data, DataProvider dataProvider, FieldMetadata meta) {
        if (data instanceof String text) {
            Hyperlink link = new Hyperlink(text);
            URI uri = URI.create(text);
            link.setOnMouseClicked(mouse -> {
                System.out.println("Unimplemented: URI renderer: " + uri);
                // try {
                //     appCtx.getDocumentService().openDocument(uri, true);
                // } catch (DocumentException e) {
                //     dialogService.showErrorMessage("Failed to open document: " + uri, e.getMessage(), e);
                // }
            });

            view.setGraphic(null);
            view.setText(null);
        }
        return null;
    }
}