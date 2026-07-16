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


package io.github.qishr.cascara.ui.form;

import java.net.URI;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;

import io.github.qishr.cascara.common.service.ServiceProviderLayer;
import io.github.qishr.cascara.ui.api.data.DataProvider;
import io.github.qishr.cascara.ui.render.RenderDispatcher;
import io.github.qishr.cascara.ui.render.RendererAllocator;
import io.github.qishr.cascara.ui.render.RendererFactory;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.application.Platform;
import javafx.beans.Observable;

public abstract class AbstractFieldFactory {
    protected URI uri;
    protected boolean isUpdatingControl = false;
    protected boolean isUpdatingProperty = false;
    private final Map<Object, ViewAndControl> viewCache = new WeakHashMap<>();

    protected Runnable onRefreshForm;
    protected Consumer<Boolean> onChangeFieldValue;

    protected DataProvider dataProvider;

    protected final RendererFactory rendererFactory;
    protected final RendererAllocator rendererAllocator;

    protected AbstractFieldFactory(ServiceProviderLayer spl) {
        if (spl == null) {
            spl = ServiceProviderLayer.getRootLayer();
        }
        rendererFactory = new RendererFactory(spl);
        rendererAllocator = new RendererAllocator(rendererFactory);
    }

    public URI getUri() { return uri; }
    public void setUri(URI uri) { this.uri = uri; }
    public void setDataProvider(DataProvider dataProvider) { this.dataProvider = dataProvider; }
    public void setOnChangeFieldValue(Consumer<Boolean> handler) { this.onChangeFieldValue = handler; }
    public void setOnRefreshForm(Runnable handler) { this.onRefreshForm = handler; }


    protected ViewAndControl createControl(FieldMetadata meta, Observable data) {
        String key = meta.getName() + data;
        if (viewCache.containsKey(key)) {
            return viewCache.get(key);
        }
        if (meta.isStringField() && !meta.hasOptionProvider()) {
            meta.setOnChange(() -> notifyDocumentChanged(false));
        } else {
            meta.setOnChange(() -> notifyDocumentChanged(true));
        }

        Label view = new Label();
        Node control = RenderDispatcher.render(view, data, dataProvider, meta);
        ViewAndControl viewAndControl = new ViewAndControl(view, control);
        viewCache.put(key, viewAndControl);

        // Non-scalar controls can take up more space...
        if (data instanceof ObservableList) {
            Platform.runLater(() -> {
                view.setMaxWidth(Double.POSITIVE_INFINITY);
                HBox.setHgrow(view, Priority.ALWAYS);
            });
        }

        return viewAndControl;
    }

    protected void notifyDocumentChanged(boolean urgent) {
        if (onChangeFieldValue != null) {
            onChangeFieldValue.accept(urgent);
        }
    }
}
