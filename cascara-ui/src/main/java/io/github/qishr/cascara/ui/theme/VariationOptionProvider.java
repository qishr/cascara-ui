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


package io.github.qishr.cascara.ui.theme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.qishr.cascara.ui.option.AbstractOptionProvider;
import io.github.qishr.cascara.ui.option.Option;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;

public class VariationOptionProvider extends AbstractOptionProvider {
    public static final String NAME = "ui-theme-variation";

    private ObjectProperty<Option> activeOption;

    public VariationOptionProvider() {
        super(NAME, null, null, null);
    }

    @Override
    public void initialize() {
        this.activeOption = ThemeEngine.activeVariationOptionProperty();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Option getActiveOption(Map<String,Property<?>> contextData, String parameter) {
        return activeOption.get();
    }

    @Override
    public List<Option> getOptions(Map<String,Property<?>> dataContext, String parameter) {
        List<Option> optionList = new ArrayList<>();
        Property<?> themeProperty = dataContext.get("theme");
        if (themeProperty != null && themeProperty.getValue() instanceof String themeId) {
            CascaraTheme theme = ThemeEngine.getTheme(themeId);
            if (theme != null) {
                for (Variation variation : theme.getVariations()) {
                    if (variation instanceof Option option) {
                        optionList.add(option);
                    }
                }
            }
        }
        return optionList;
    }
}

