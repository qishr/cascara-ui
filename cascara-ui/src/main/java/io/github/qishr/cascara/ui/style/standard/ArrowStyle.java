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
import io.github.qishr.cascara.ui.theme.ColorID;

public class ArrowStyle extends ControlStyle {
    public ArrowStyle() {
        super();

        // Arrows

        // defineRule(newRule()
        //     .addSelector (classSelector("combo-box-base > .arrow-button > .arrow"))
        //     .addSelector (classSelector("choice-box > .open-button > .arrow"))
        //     .addSelector (classSelector("menu-button > .arrow-button > .arrow"))
        //     .addSelector (classSelector("split-menu-button > .arrow-button > .arrow"))
        //     .addDeclaration(FX_PADDING, literal("0.2em 0.4em 0.2em 0.4em"), false)
        //     .addDeclaration(FX_SHAPE, shape(SHAPE_ARROW_DOWN), false)
        //     .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_FOREGROUND)), false)
        // );
        defineRule(newRule()
            .addSelector (classSelector("menu-button:openvertically > .arrow-button > .arrow"))
            .addDeclaration(FX_SHAPE, shape(SHAPE_ARROW_DOWN), false)
            .build()
        );

        // Nested Columns

        defineRule(newRule()
            .addSelector (classSelector("table-view > .column-header-background .nested-column-header > .column-header > GridPane > .arrow"))
            .addSelector (classSelector("tree-table-view > .column-header-background .nested-column-header > .column-header > GridPane > .arrow"))
            .addDeclaration(FX_PADDING, literal("0.5em 0.4em 0.5em 0.4em"), false)
            .addDeclaration(FX_SHAPE, shape(SHAPE_ARROW_DOWN), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );
    }
}
