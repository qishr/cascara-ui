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


package io.github.qishr.cascara.ui.style.part;

import io.github.qishr.cascara.ui.style.PartStyle;
import io.github.qishr.cascara.ui.theme.ColorID;

public class ColumnHeaderRules extends PartStyle {
    public ColumnHeaderRules(String controlClass) {
        super();
        this.controlClass = controlClass;

        // Table Headers

        defineRule(newRule()
            .addSelector (classSelector("column-header"))
            .addSelector (classSelector("column-header-background"))
            .addSelector (classSelector("column-header .filler"))
            .addSelector (classSelector("column-header-background .filler"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.SIDEBAR_BACKGROUND), 5)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("column-header"))
            .addDeclaration(FX_PADDING, values(ZERO, ZERO, ZERO, em(1)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("column-header:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.SIDEBAR_BACKGROUND), 10)), false)
            .build()
        );

        defineRule( newRule()
            .addSelector (classSelector("column-header .label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.SIDEBAR_FOREGROUND)), false)
            .addDeclaration(FX_PADDING, values(px(1), em(2), px(1), ZERO), false)
            .addDeclaration(TEXT_TRANSFORM, values(UPPERCASE), false)
            .addDeclaration(FX_FONT_SIZE, values(em(.75)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .addDeclaration(FX_ALIGNMENT, values(CENTER_LEFT), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("column-header:hover .label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.CONTROL_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("align-right .label"))
            .addDeclaration(FX_ALIGNMENT, values(CENTER_RIGHT), false)
            .build()
        );


        defineRule(newRule()
            .addSelector (classSelector("column-header.table-column-selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT, color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(sides(ZERO, ZERO, ZERO, ZERO), sides(px(1), px(1), px(1), px(1))), false)
            .build()
        );


        // Buttons

        defineRule(newRule()
            .addSelector (childSelector(" .column-header-background > .show-hide-columns-button"))
            .addSelector (childSelector(" .column-header-background > .show-hide-columns-button"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CONTROL_FOREGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_PADDING, literal("0.17em 0.333333em 0.17em 0.333333em"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (childSelector(" .column-header-background > .show-hide-columns-button:hover"))
            .addSelector (childSelector(" .column-header-background > .show-hide-columns-button:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_HOVER_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("show-hide-column-image"))
            .addSelector (childSelector(" .column-header-background > .show-hide-columns-button:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CONTROL_FOREGROUND)), false)
            .addDeclaration(FX_PADDING, literal("0.083em"), false)
            .addDeclaration(FX_SCALE_SHAPE, literal(FALSE), false)
            .addDeclaration(FX_SHAPE, shape("M33.561,25.389c0,0.828-0.672,1.5-1.5,1.5s-1.5-0.672-1.5-1.5c0-0.828,0.672-1.5,1.5-1.5S33.561,24.561,33.561,25.389z M32.061,30.532c-0.828,0-1.5,0.672-1.5,1.5c0,0.828,0.672,1.5,1.5,1.5s1.5-0.672,1.5-1.5C33.561,31.204,32.889,30.532,32.061,30.532z M32,37.107c-0.828,0-1.5,0.672-1.5,1.5c0,0.828,0.672,1.5,1.5,1.5s1.5-0.672,1.5-1.5C33.5,37.779,32.828,37.107,32,37.107z"), false)
            .build()
        );


        // Column Headers hidden

    }
}
