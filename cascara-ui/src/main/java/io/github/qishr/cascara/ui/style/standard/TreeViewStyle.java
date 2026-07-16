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


package io.github.qishr.cascara.ui.style.standard;

import io.github.qishr.cascara.ui.style.ControlStyle;
import io.github.qishr.cascara.ui.style.part.CellPseudoRules;
import io.github.qishr.cascara.ui.style.part.CellRules;
import io.github.qishr.cascara.ui.style.part.TabularRules;
import io.github.qishr.cascara.ui.theme.ColorID;

public class TreeViewStyle extends ControlStyle {
    private static final String CONTROL_CLASS = "tree-view";
    private static final String CELL_CLASS = "tree-cell";

    public TreeViewStyle() {
        super();

        incorporateRules(new TabularRules(CONTROL_CLASS));
        incorporateRules(new CellRules(CELL_CLASS));
        incorporateRules(new CellPseudoRules(CONTROL_CLASS, CELL_CLASS));

        // Arrow

        defineRule(newRule()
            .addSelector (classSelector("tree-cell > .tree-disclosure-node"))
            .addDeclaration(FX_PADDING, sides(px(8), px(2), px(4), px(8)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("tree-cell > .tree-disclosure-node > .arrow"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_FOREGROUND)), false)
            .addDeclaration(FX_PADDING, sides(px(8), px(2), px(4), px(8)), false)
            .addDeclaration(FX_SHAPE, shape(SHAPE_ARROW_DOWN), false)
            .addDeclaration(FX_EFFECT, values(NULL), false)
            .addDeclaration(FX_SCALE_SHAPE, values(FALSE), false)
            .addDeclaration(FX_ROTATE, degrees(-90), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("tree-cell:expanded > .tree-disclosure-node > .arrow"))
            .addDeclaration(FX_ROTATE, literal("0"), false)
            .build()
        );
    }
}
