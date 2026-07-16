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

import io.github.qishr.cascara.lang.yaml.ast.YamlMapNode;
import io.github.qishr.cascara.lang.yaml.ast.YamlNode;
import io.github.qishr.cascara.lang.yaml.ast.YamlSequenceNode;
import io.github.qishr.cascara.lang.yaml.processor.YamlAstParser;

public class CascaraTheme {
    private YamlMapNode projectYaml = null;
    private String name;
    private String themeId;
    private List<Variation> variations = new ArrayList<>();

    public CascaraTheme() {
    }

    public CascaraTheme(String yamlString) {
        loadYamlString(yamlString);

        name = projectYaml.getString("name");

        variations = new ArrayList<>();
        YamlSequenceNode seq = projectYaml.getSequence("variations");
        List<YamlNode> variationsNodes = seq.getChildren();
        for (YamlNode variationNode : variationsNodes) {
            if (variationNode instanceof YamlMapNode map) {
                ThemeVariationFactoryImpl factory = new ThemeVariationFactoryImpl();
                Variation variation = ThemeVariationLoader.load(map, factory);
                variations.add(variation);
            }
        }
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String path) {
        this.themeId = path;
    }

    public List<Variation> getVariations() {
        return variations;
    }

    public Variation getVariation(String variationId) {
        for (Variation variation : variations) {
            if (variationId.equals(variation.getName())) {
                return variation;
            }
        }
        return null;
    }

    protected void loadYamlString(String content) {
        YamlAstParser parser = new YamlAstParser();
        if (parser.parse(content) instanceof YamlMapNode map) {
            this.projectYaml = map;
        } else {
            throw new RuntimeException("Expected map as root of project YAML"); // TODO: custom exception type
        }
    }
}
