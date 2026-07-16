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

public class CheckBoxStyle extends ControlStyle {
    public CheckBoxStyle() {
        super();

        defineRule(newRule()
            .addSelector (classSelector("check-box"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("check-box > .box"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(px(2)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_BORDER)), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .addDeclaration(FX_BORDER_STYLE, values(SOLID), false)
            .addDeclaration(FX_BORDER_RADIUS, values(px(2)), false)
            .addDeclaration(FX_PREF_WIDTH, values(em(1.1)), false)
            .addDeclaration(FX_PREF_HEIGHT, values(em(1.1)), false)
            .addDeclaration(FX_PADDING, values(px(1)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("check-box > .box > .mark"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_PADDING, values(px(2)), false)
            .addDeclaration(FX_SHAPE, shape("M17.939,5.439L7.5,15.889l-5.439-5.449l0.879-0.879L7.5,14.111 l9.561-9.551L17.939,5.439z"), false)
            .build()
        );


        // Selected

        defineRule(newRule()
            .addSelector (classSelector("check-box:selected > .box"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_SELECTED_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_SELECTED_BORDER)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("check-box:selected > .box > .mark"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_FOREGROUND)), false)
            .build()
        );


        // Focused

        defineRule(newRule()
            .addSelector (classSelector("check-box:focused > .box"))
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            // .addDeclaration(FX_BACKGROUND_RADIUS, values(VALUE_ZERO), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_BORDER)), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .addDeclaration(FX_BORDER_STYLE, values(SOLID), false)
            .addDeclaration(FX_BORDER_INSETS, values(ZERO), false)
            // .addDeclaration(FX_BORDER_WIDTH, literal("1, 2"), false)
            // .addDeclaration(FX_BORDER_STYLE, literal("segments(1, 2), solid"), false)
            // .addDeclaration(FX_BORDER_INSETS, literal("-3, 0"), false)
            .build()
        );

        // Focused & Selected

        defineRule(newRule()
            .addSelector (classSelector("check-box:focused:selected > .box"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_SELECTED_BORDER)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("check-box:focused:selected:hover > .box"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_SELECTED_BORDER)), false)
            .build()
        );




        // Indeterminate

        defineRule(newRule()
            .addSelector (classSelector("check-box:focused:indeterminate > .box"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_BORDER)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("check-box:indeterminate > .box > .mark"))
            .addDeclaration(FX_SHAPE, shape("M0,0H10V2H0Z"), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .build()
        );


        // Disabled

        defineRule(newRule()
            .addSelector (classSelector("check-box:disabled"))
            .addDeclaration(FX_OPACITY, literal("0.4"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("check-box:disabled > .box"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_DISABLED_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("check-box:disabled > .box > .mark"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_DISABLED_FOREGROUND)), false)
            .build()
        );

    }
}
