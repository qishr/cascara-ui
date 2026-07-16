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

public class DatePickerStyle extends ControlStyle {
    public DatePickerStyle() {
        super();

        defineRule(newRule()
            .addSelector (classSelector("date-picker"))
            .addSelector (classSelector("date-picker:hover"))
            .addSelector (classSelector("date-picker:focused"))
            .addSelector (classSelector("date-picker:focused:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.INPUT_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(px(2)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.INPUT_BORDER)), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .addDeclaration(FX_BORDER_RADIUS, values(px(2)), false)

            .addDeclaration(FX_PADDING, values(ZERO), false)

            .addDeclaration(FX_FONT_SIZE, values(em(1)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.INPUT_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("date-picker .text-input"))
            .addSelector (classSelector("date-picker .text-field"))
            .addSelector (classSelector("date-picker .label"))
            .addSelector (classSelector("date-picker .list-cell"))
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            // .addDeclaration(FX_BACKGROUND_COLOR, values("#44DDad"), false)
            // .addDeclaration(FX_PADDING, values(ZERO), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("date-picker:focused"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.INPUT_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(px(2)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("date-picker:focused > .text-field"))
            .addSelector (classSelector("date-picker > .text-field:focused"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.INPUT_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(px(2)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("date-picker > .arrow-button"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(px(2)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .addDeclaration(FX_MAX_WIDTH, values(em(0.5)), false)
            .addDeclaration(FX_MAX_HEIGHT, values(em(0.8)), false)
            // .addDeclaration(FX_PADDING, sides(em(0.8), em(0.4), em(0.7), em(0.4)), false)
            .addDeclaration(FX_PADDING, sides(em(0.6), em(0.4), em(0.5), em(0.4)), false)
            // .addDeclaration(FX_PADDING, sides(em(1), em(0.4), em(0.9), em(0.4)), false)
            .build()
        );


        // TODO: Fix this
        defineRule(newRule()
            .addSelector (classSelector("date-picker  .arrow-button .arrow"))
            .addSelector (classSelector("date-picker  .open-button  .arrow"))
            .addDeclaration(FX_SHAPE, shape(SHAPE_ARROW_DOWN), false)
            // .addDeclaration(FX_BACKGROUND_COLOR, values("#AA6633"), false)
            .build()
        );
    }
}
