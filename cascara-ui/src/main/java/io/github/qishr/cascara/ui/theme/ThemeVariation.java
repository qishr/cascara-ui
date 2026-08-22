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

import java.util.HashMap;
import java.util.Map;

import io.github.qishr.cascara.common.annotation.DataIgnore;
import io.github.qishr.cascara.ui.color.ColorDefinition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ThemeVariation implements Variation {
    @DataIgnore
    public static final String GROUP_ID_BASE_COLORS = "baseColors";
    @DataIgnore
    public static final String GROUP_ID_TRANSFORMS = "transforms";
    @DataIgnore
    public static final String GROUP_ID_PALETTE_COLORS = "paletteColors";
    @DataIgnore
    public static final String GROUP_ID_UI_COLORS = "uiColors";
    @DataIgnore
    public static final String GROUP_ID_CODE_COLORS = "codeColors";

    private String name = "";
    private String path = "";

    // private ColorDefinitionGroup baseColors = new ColorDefinitionGroup(ColorDefinitionGroup.BASE_COLORS);
    // private ColorDefinitionGroup transforms = new ColorDefinitionGroup(ColorDefinitionGroup.TRANSFORMS);
    // private ColorDefinitionGroup paletteColors = new ColorDefinitionGroup(ColorDefinitionGroup.PALETTE_COLORS);
    // private ColorDefinitionGroup uiColors = new ColorDefinitionGroup(ColorDefinitionGroup.UI_COLORS);
    // private ColorDefinitionGroup codeColors = new ColorDefinitionGroup(ColorDefinitionGroup.CODE_COLORS);

    private Map<String, ColorDefinition> baseColors = new HashMap<>();
    private Map<String, ColorDefinition> transforms = new HashMap<>();
    private Map<String, ColorDefinition> paletteColors = new HashMap<>();
    private Map<String, ColorDefinition> uiColors = new HashMap<>();
    private Map<String, ColorDefinition> codeColors = new HashMap<>();

    public ThemeVariation() {
        // for (String colorId : ColorID.PALETTE_COLORS) {
        //     ColorDefinition colordef = new ColorDefinition();
        //     colordef.setId(colorId);
        //     paletteColors.add(colordef);
        // }
    }

    @Override
    public String getOptionId() {
        return name;
    }

    @Override
    public String getOptionText() {
        return name;
    }

    @Override
    public String getOptionTranslationKey() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, ColorDefinition> getBaseColors() {
        return baseColors;
    }

    public ColorDefinition getBaseColor(String id) {
        return baseColors.get(id);
    }

    public void setBaseColor(String id, String hexColor) {
        // ColorDefinition colordef = baseColors.getOrAdd(id);
        // colordef.setHexColor(hexColor);
        ColorDefinition colordef = baseColors.get(id);
        if (colordef == null) {
            colordef = new ColorDefinition();
            baseColors.put(id, colordef);
        }
        colordef.setHexColor(hexColor);
    }

    public Map<String, ColorDefinition> getTransformDefinitions() {
        return transforms;
    }

    public ColorDefinition getTransformDefinition(String id) {
        return transforms.get(id);
    }

    public void setTransformDefinition(String id, String transform) {
        // ColorDefinition colordef = transforms.getOrAdd(id);
        // colordef.setTransformDefinition(transform);
        ColorDefinition colordef = transforms.get(id);
        if (colordef == null) {
            colordef = new ColorDefinition();
            transforms.put(id, colordef);
        }
        colordef.setHexColor(transform);
    }

    public Map<String, ColorDefinition> getPaletteColors() {
        return paletteColors;
    }

    public ColorDefinition getPaletteColor(String id) {
        return paletteColors.get(id);
    }

    public void setPaletteColor(String id, ColorDefinition definition) {
        paletteColors.replace(id, definition);
    }

    public void setPaletteColor(String id, String hexColor) {
        // ColorDefinition colordef = paletteColors.getOrAdd(id);
        // colordef.setHexColor(hexColor);
        ColorDefinition colordef = paletteColors.get(id);
        if (colordef == null) {
            colordef = new ColorDefinition();
            paletteColors.put(id, colordef);
        }
        colordef.setHexColor(hexColor);
    }

    public Map<String, ColorDefinition> getUiColors() {
        return uiColors;
    }

    public ColorDefinition getUiColor(String id) {
        return uiColors.get(id);
    }

    public void setUiColor(String id, String hexColor) {
        // ColorDefinition colordef = uiColors.getOrAdd(id);
        // colordef.setHexColor(hexColor);
        ColorDefinition colordef = uiColors.get(id);
        if (colordef == null) {
            colordef = new ColorDefinition();
            uiColors.put(id, colordef);
        }
        colordef.setHexColor(hexColor);
    }

    public Map<String, ColorDefinition> getCodeColors() {
        return codeColors;
    }

    public ColorDefinition getCodeColor(String id) {
        return codeColors.get(id);
    }

    public void setCodeColor(String id, String hexColor) {
        // ColorDefinition colordef = codeColors.getOrAdd(id);
        // colordef.setHexColor(hexColor);
        ColorDefinition colordef = codeColors.get(id);
        if (colordef == null) {
            colordef = new ColorDefinition();
            codeColors.put(id, colordef);
        }
        colordef.setHexColor(hexColor);
    }

    public Map<String, ColorDefinition> getGroup(String groupId) {
        switch(groupId) {
            case GROUP_ID_BASE_COLORS:
                return baseColors;
            case GROUP_ID_TRANSFORMS:
                return transforms;
            case GROUP_ID_PALETTE_COLORS:
                return paletteColors;
            case GROUP_ID_UI_COLORS:
                return uiColors;
            case GROUP_ID_CODE_COLORS:
                return codeColors;
            default:
                return null;
        }
    }

    public static class ColorGroup {
        private String name;
        private String color;

        public ColorGroup() {
            // Nothing to see here
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }


    private final BooleanProperty modified = new SimpleBooleanProperty();
    public final BooleanProperty modifiedProperty() {return modified;}
    public final Boolean getModified() {return this.modified.get();}
    public final void setModified(Boolean v) {this.modified.set(v);}
}

