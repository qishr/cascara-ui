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

public class TitledPaneStyle extends ControlStyle {
    public TitledPaneStyle() {
        super();
        defineRule(newRule()
            .addSelector (classSelector("titled-pane"))
            // .addDeclaration(FX_BACKGROUND_COLOR, values(VALUE_TRANSPARENT), false)
            // .addDeclaration(FX_BACKGROUND_COLOR, literal("#aa9933"), false)
            .addDeclaration(FX_BORDER_COLOR, values(derive(color(ColorID.FOREGROUND), -50)), false)
            .addDeclaration(FX_BORDER_RADIUS, values(px(2)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("titled-pane > .title > .text"))
            .addDeclaration(FX_FILL, values(color(ColorID.FOREGROUND)), false)
            // .addDeclaration(FX_FONT_FAMILY, literal(""), false)
            .addDeclaration(FX_FONT_SIZE, literal("1em"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("titled-pane > .title"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("titled-pane > .content"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            // .addDeclaration(FX_BACKGROUND_COLOR, literal("#aa3399"), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_PADDING, values(em(0.5)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("titled-pane > .content > AnchorPane"))
            .addSelector (classSelector("titled-pane > .content > BorderPane"))
            .addSelector (classSelector("titled-pane > .content > FlowPane"))
            .addSelector (classSelector("titled-pane > .content > GridPane"))
            .addSelector (classSelector("titled-pane > .content > HBox"))
            .addSelector (classSelector("titled-pane > .content > Pane"))
            .addSelector (classSelector("titled-pane > .content > StackPane"))
            .addSelector (classSelector("titled-pane > .content > TilePane"))
            .addSelector (classSelector("titled-pane > .content > VBox"))
            .addDeclaration(FX_PADDING, literal("5px"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("titled-pane > .title > .arrow-button"))
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal(ZERO), false)
            .addDeclaration(FX_PADDING, literal("0 4 0 0"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("titled-pane > .title > .arrow-button > .arrow"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_HEADER_FOREGROUND)), false)
            .addDeclaration(FX_SHAPE, shape(SHAPE_ARROW_DOWN), false)
            .addDeclaration(FX_EFFECT, literal(NULL), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("titled-pane:focused > .title > .arrow-button > .arrow"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_HEADER_FOREGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_EFFECT, literal(NULL), false)
            .build()
        );
    }
}
