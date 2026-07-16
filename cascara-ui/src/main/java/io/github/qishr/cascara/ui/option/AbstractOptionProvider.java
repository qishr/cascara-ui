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


package io.github.qishr.cascara.ui.option;

import java.util.ArrayList;
import java.util.List;

import io.github.qishr.cascara.common.util.Properties;
import io.github.qishr.cascara.ui.api.ServicePropertyName;
import io.github.qishr.cascara.ui.api.render.ScalarRenderer;

public abstract class AbstractOptionProvider implements OptionProvider {
    protected final List<Runnable> listeners = new ArrayList<>();

    private final String name;
    private final String contentType;
    private final String schemaType;
    private final String format;

    private Properties properties;

    /// @param name The name used to reference this provider from a schema
    /// @param schemaType
    /// @param format
    /// @param contentEncoding
    protected AbstractOptionProvider(String name, String contentType, String schemaType, String format) {
        this.name = name;
        this.contentType = contentType;
        this.schemaType = schemaType;
        this.format = format;
    }

    @Override
    public Properties getServiceProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.set(ServicePropertyName.NAME.asString(), name);
            if (contentType != null) properties.set(ServicePropertyName.CONTENT_TYPE.asString(), contentType);
            if (schemaType != null) properties.set(ServicePropertyName.SCHEMA_TYPE.asString(), schemaType);
            if (format != null) properties.set(ServicePropertyName.SCHEMA_FORMAT.asString(), format);
        }
        return properties;
    }

    @Override
    public void initialize() {
        // To be overridden by providers that need initialization
    }

    @Override public void addListener(Runnable listener) { listeners.add(listener); }
    @Override public void removeListener(Runnable listener) { listeners.remove(listener); }
    public ScalarRenderer getRenderer() { return null; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getContentType() { return contentType; }

    @Override
    public String getSchemaType() { return schemaType; }

    @Override
    public String getSchemaFormat() { return format; }


}
