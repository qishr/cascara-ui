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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CodeColorsStyleSheet {
    private List<StyleClass> classes = new ArrayList<>();

    public CodeColorsStyleSheet() {
        // Nothing to see here
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (StyleClass styleClass : classes) {
            boolean isFirst = true;
            for (String name : styleClass.getNames()) {
                if (!isFirst) {
                    sb.append(", ");
                }
                sb.append(".");
                sb.append(name);
                isFirst = false;
            }
            sb.append(" {\n");
            for (Entry<String,String> attribute : styleClass.getAttributes().entrySet()) {
                if (attribute.getValue() != null && !attribute.getValue().isEmpty()) {
                    sb.append("    ");
                    sb.append(attribute.getKey());
                    sb.append(": ");
                    sb.append(attribute.getValue());
                    sb.append(";\n");
                }
            }
            sb.append("}\n");
        }
        return sb.toString();
    }

    public StyleClass addClasses(String... names) {
        StyleClass styleClass = new StyleClass(names);
        classes.add(styleClass);
        return styleClass;
    }

    public StyleClass addClass(String name) {
        StyleClass styleClass = new StyleClass(name);
        classes.add(styleClass);
        return styleClass;
    }

    public class StyleClass {
        List<String> names = new ArrayList<>();
        Map<String,String> attributes = new HashMap<>();

        public StyleClass(String... names) {
            for (String name : names) {
                this.names.add(name);
            }
        }

        public void addName(String name) {
            names.add(name);
        }

        public StyleClass(String name) {
            names.add(name);
        }

        public String getName() {
            return names.getFirst();
        }

        public List<String> getNames() {
            return names;
        }

        public void setAttribute(String name, String value) {
            attributes.put(name, value);
        }

        public Map<String,String> getAttributes() {
            return attributes;
        }

        public String getAttribute(String name) {
            return attributes.get(name);
        }
    }
}
