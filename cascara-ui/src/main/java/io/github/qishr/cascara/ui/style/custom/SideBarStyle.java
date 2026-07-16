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


package io.github.qishr.cascara.ui.style.custom;

import io.github.qishr.cascara.ui.style.ControlStyle;
import io.github.qishr.cascara.ui.theme.ColorID;

public class SideBarStyle extends ControlStyle {
    public SideBarStyle() {
        super();

        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_HEADER_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.SIDEBAR_BORDER)), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("sidebar .sidebar-title-box"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_TITLE_BACKGROUND)), false)
            .addDeclaration(FX_PADDING, sides(px(8),px(0),px(8),px(14)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("sidebar .sidebar-title-label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.SIDEBAR_TITLE_FOREGROUND)), false)
            .addDeclaration(FX_FONT_SIZE, values(em(0.85)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            // .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.SIDEBAR_BORDER)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.SIDEBAR_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .title"))
            .addDeclaration(FX_EFFECT, literal(NONE), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_HEADER_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(ZERO), false)
            .addDeclaration(FX_PADDING, sides(px(2), px(2), px(2), px(0)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .title > .text"))
            .addDeclaration(FX_FILL, values(color(ColorID.SIDEBAR_HEADER_FOREGROUND)), false)
            .addDeclaration(FX_FONT_SIZE, values(em(0.85)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .addDeclaration(FX_PADDING, sides(px(0), px(0), px(0), px(0)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .content"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.SIDEBAR_BORDER)), false)
            .addDeclaration(FX_BORDER_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_BACKGROUND)), false)
            .addDeclaration(FX_PADDING, values(ZERO), false)
            .build()
        );



        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .content .table-view"))
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .content .tree-view"))
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .content .tree-table-view"))
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_PADDING, sides(ZERO, ZERO, ZERO, ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .content  .tree-view .tree-cell > .tree-disclosure-node"))
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .content  .tree-table-view .tree-table-row-cell > .tree-disclosure-node"))
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .content  .tree-table-view > .virtual-flow > .clipped-container > .sheet > .tree-table-row-cell > .tree-disclosure-node > .arrow"))
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_PADDING, sides(px(2), px(2), px(2), px(12)), false)
            .build()
        );


        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .title > .arrow-button"))
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal(ZERO), false)
            .addDeclaration(FX_PADDING, sides(px(2), px(8), px(2), px(4)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane > .title > .arrow-button > .arrow"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_HEADER_FOREGROUND)), false)
            .addDeclaration(FX_SHAPE, shape(SHAPE_ARROW_DOWN), false)
            .addDeclaration(FX_EFFECT, values(NULL), false)
            .addDeclaration(FX_PADDING, sides(px(2), px(2), px(2), px(0)), false)
            .addDeclaration(FX_SCALE_SHAPE, values(FALSE), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("sidebar .accordion > .titled-pane:focused > .title > .arrow-button > .arrow"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_HEADER_FOREGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_EFFECT, values(NULL), false)
            .build()
        );
    }
}
