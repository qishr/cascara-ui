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

public class RadioButtonStyle extends ControlStyle {
    public RadioButtonStyle() {
        super();

        defineRule(newRule()
            .addSelector (classSelector("radio-button"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("radio-button > .radio"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(em(1)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_BORDER)), false)
            .addDeclaration(FX_BORDER_STYLE, values(SOLID), false)
            .addDeclaration(FX_BORDER_RADIUS, values(em(1)), false)

            // Why does this break it?
            // Looks like it just doesn't have a border color property
            // .addDeclaration(FX_BORDER_COLOR, values(ColorID.RADIO_ACTIVE_BORDER), false)

            .addDeclaration(FX_BORDER_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .addDeclaration(FX_PADDING, values(px(2)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("radio-button > .radio > .dot"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(em(1)), false)
            .addDeclaration(FX_PADDING, values(em(0.3)), false)
            .build()
        );

        // Selected

        defineRule(newRule()
            .addSelector (classSelector("radio-button:selected > .radio"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_SELECTED_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_SELECTED_BORDER)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("radio-button:selected > .radio > .dot"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_FOREGROUND)), false)
            .build()
        );

        // Focused

        defineRule(newRule()
            .addSelector (classSelector("radio-button:focused > .radio"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_BORDER)), false)
            .addDeclaration(FX_BORDER_STYLE, values(SOLID), false)
            .addDeclaration(FX_BORDER_RADIUS, values(em(1)), false)
            .addDeclaration(FX_BORDER_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .build()
        );

        // Focus & Selected

        defineRule(newRule()
            .addSelector (classSelector("radio-button:focused:selected > .radio"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_SELECTED_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_SELECTED_BORDER)), false)
            .build()
        );

        // Hover

        defineRule(newRule()
            .addSelector (classSelector("radio-button:hover > .radio"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CHECKBOX_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_BORDER)), false)
            .build()
        );

        // Hover & Focus

        defineRule(newRule()
            .addSelector (classSelector("radio-button:focused:hover > .radio"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_BORDER)), false)
            .build()
        );

        // Disabled

        defineRule(newRule()
            .addSelector (classSelector("radio-button:disabled"))
            .addDeclaration(FX_OPACITY, literal("0.4"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("radio-button:disabled > .radio"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CHECKBOX_BORDER)), false)
            .build()
        );
    }
}
