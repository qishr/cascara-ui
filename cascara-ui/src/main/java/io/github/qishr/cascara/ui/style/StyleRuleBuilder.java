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


package io.github.qishr.cascara.ui.style;

import java.util.ArrayList;
import java.util.List;

// New class: StyleRuleBuilder.java (or inner class of ControlStyle)

public class StyleRuleBuilder {
    private final StringBuilder declarations = new StringBuilder();
    private final List<String> selectors = new ArrayList<>();

    // Replaces new CSSStyleRule().addSelector(...)
    public StyleRuleBuilder addSelector(String selector) {
        selectors.add(selector);
        return this;
    }

    // Replaces .addDeclaration(PROPERTY, VALUES, IMPORTANT)
    public StyleRuleBuilder addDeclaration(String property, String value, boolean important) {
        declarations.append("    ")
                    .append(property)
                    .append(": ")
                    .append(value);
        if (important) {
            declarations.append(" !important");
        }
        declarations.append(";\n");
        return this;
    }

    // Final method to generate the required string
    public String build() {
        if (selectors.isEmpty()) return "";

        return String.join(", ", selectors) + " {\n" +
               declarations.toString() +
               "}\n";
    }
}