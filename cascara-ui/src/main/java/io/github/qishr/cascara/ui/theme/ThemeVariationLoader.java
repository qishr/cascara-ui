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

import io.github.qishr.cascara.lang.yaml.ast.YamlMapEntry;
import io.github.qishr.cascara.lang.yaml.ast.YamlMap;
import io.github.qishr.cascara.lang.yaml.ast.YamlNode;
import io.github.qishr.cascara.lang.yaml.ast.YamlScalar;
import io.github.qishr.cascara.ui.color.ColorDefinition;

public class ThemeVariationLoader {
    public static Variation load(YamlMap variationNode, ThemeVariationFactory factory) {
        Variation variation = factory.createVariation();
        variation.setName(variationNode.getString("name"));
        variation.setPath(variationNode.getString("path"));

        if (variationNode.getMap("baseColors") instanceof YamlMap map) {
            for (YamlMapEntry entry : map.getEntries()) {
                ColorDefinition cd = loadDefinition(entry);
                variation.getBaseColors().put(cd.getId(), cd);
            }
        }

        if (variationNode.getMap("transforms") instanceof YamlMap map) {
            for (YamlMapEntry entry : map.getEntries()) {
                ColorDefinition cd = loadDefinition(entry);
                variation.getTransformDefinitions().put(cd.getId(), cd);
            }
        }

        if (variationNode.getMap("paletteColors") instanceof YamlMap map) {
            for (YamlMapEntry entry : map.getEntries()) {
                ColorDefinition cd = loadDefinition(entry);
                variation.getPaletteColors().put(cd.getId(), cd);
            }
        }

        if (variationNode.getMap("uiColors") instanceof YamlMap map) {
            for (YamlMapEntry entry : map.getEntries()) {
                ColorDefinition cd = loadDefinition(entry);
                variation.getUiColors().put(cd.getId(), cd);
            }
        }

        if (variationNode.getMap("codeColors") instanceof YamlMap map) {
            for (YamlMapEntry entry : map.getEntries()) {
                ColorDefinition cd = loadDefinition(entry);
                variation.getCodeColors().put(cd.getId(), cd);
            }
        }

        return variation;
    }

    private static ColorDefinition loadDefinition(YamlMapEntry entry) {
        if (!(entry.getKey() instanceof YamlScalar key)) {
            return null;
        }
        String id = key.asString();
        YamlNode valueNode = entry.getValue();
        if (valueNode instanceof YamlMap map) {
            ColorDefinition colordef = new ColorDefinition();
            colordef.setId(nonNull(id));
            colordef.setName(nonNull(map.getString("name")));
            colordef.setHexColor(nonNull(map.getString("hexColor")));
            colordef.setLeftHexColor(nonNull(map.getString("leftColor")));
            colordef.setRightHexColor(nonNull(map.getString("rightColor")));
            colordef.setLerp(nonNull(map.getString("lerp")));
            colordef.setTransformDefinition(nonNull(map.getString("transformDefinition")));
            colordef.setTransformId(nonNull(map.getString("transformId")));
            colordef.setBaseColorId(nonNull(map.getString("baseColorId")));
            colordef.setPaletteColorId(nonNull(map.getString("paletteColorId")));
            return colordef;
        } else {
            return null;
        }
    }

    private static String nonNull(String s) {
        return s == null ? "": s;
    }
}
