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


package io.github.qishr.cascara.ui.language.binding;

import io.github.qishr.cascara.ui.language.ObservableLocalizer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;

public class PropertyLocalizationBinding extends LocalizationBinding {
    @Override
    protected PropertyLocalizationBinding self() { return this; }

    @SuppressWarnings("unchecked")
    public PropertyLocalizationBinding(ObservableLocalizer localizer, Property<?> target, String key, Object... args) {
        super(localizer, key, args);
        if (target instanceof ObjectProperty objProp) {
            listener = o -> {
                objProp.setValue(localizer.format(key, args));
            };
        } else if (target instanceof StringProperty stringProp) {
            listener = o -> {
                stringProp.setValue(localizer.format(key, args));
            };
        }
        startListening();
    }
}
