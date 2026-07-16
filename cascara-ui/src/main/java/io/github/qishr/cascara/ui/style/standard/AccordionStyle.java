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

public class AccordionStyle extends ControlStyle {
    public AccordionStyle() {
        super();

        defineRule(newRule()
            .addSelector (classSelector("accordion"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_HEADER_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.SIDEBAR_BORDER)), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("accordion > .titled-pane"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.SIDEBAR_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("accordion > .titled-pane > .title"))
            .addDeclaration(FX_EFFECT, literal(NONE), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_HEADER_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("accordion > .titled-pane > .title > .text"))
            .addDeclaration(FX_FILL, values(color(ColorID.SIDEBAR_HEADER_FOREGROUND)), false)
            .addDeclaration(FX_FONT_SIZE, values(em(0.75)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("accordion > .titled-pane > .content"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.SIDEBAR_BORDER)), false)
            .addDeclaration(FX_BORDER_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_BACKGROUND)), false)
            .addDeclaration(FX_PADDING, values(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("accordion > .titled-pane > .content .table-view"))
            .addSelector (classSelector("accordion > .titled-pane > .content .tree-view"))
            .addSelector (classSelector("accordion > .titled-pane > .content .tree-table-view"))
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_PADDING, values(ZERO), false)
            .build()
        );
    }
}
