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

public class SliderStyle extends ControlStyle {
    public SliderStyle() {
        super();
        defineRule(newRule()
            .addSelector (classSelector("slider"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_EFFECT, values(NULL), false)
            .addDeclaration(FX_PADDING, literal("10px"), false)
            .build()
        );

        //
        // Axis
        //

        defineRule(newRule()
            .addSelector (classSelector("slider > .axis"))

            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, literal(ZERO), false)
            .addDeclaration(FX_TICK_LABEL_FILL, values(color(ColorID.FOREGROUND)), false)
            .addDeclaration(FX_TICK_LENGTH, literal("15px"), false)
            .addDeclaration(FX_MINOR_TICK_LENGTH, literal("5px"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("slider > .axis > .axis-tick-mark"))
            .addDeclaration(FX_STROKE, values(color(ColorID.FOREGROUND)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("slider > .axis > .axis-minor-tick-mark"))
            .addDeclaration(FX_STROKE, values(color(ColorID.FOREGROUND)), false)
            .build()
        );

        //
        // Track
        //

        defineRule(newRule()
            .addSelector (classSelector("slider > .track"))
            .addSelector (classSelector("slider:hover > .track"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.INPUT_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.CONTROL_FOREGROUND)), false)
            .addDeclaration(FX_BORDER_RADIUS, literal("4"), false)
            .addDeclaration(FX_BORDER_INSETS, literal(ZERO), false)
            .addDeclaration(FX_OPACITY, literal("0.7"), false)
            .addDeclaration(FX_PREF_HEIGHT, literal("8"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("slider:vertical > .track"))
            .addDeclaration(FX_PREF_WIDTH, literal("8"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("slider:hover > .track"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.INPUT_BACKGROUND)), false)
            .build()
        );

        //
        // Thumb
        //

        defineRule(newRule()
            .addSelector (classSelector("slider > .thumb"))
            .addDeclaration(FX_EFFECT, literal(NULL), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.ACCENT_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal("2px"), false)
            .addDeclaration(FX_PADDING, literal("10px 4px 10px 4px"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("slider:vertical > .thumb"))
            .addDeclaration(FX_PADDING, literal("4px 10px 4px 10px"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("slider:hover > .thumb"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.ACCENT_HOVER_BACKGROUND)), false)
            .build()
        );

        //
        // Disabled
        //

        defineRule(newRule()
            .addSelector (classSelector("slider:disabled"))
            .addDeclaration(FX_OPACITY, literal("0.4"), false)
            .build()
        );
    }
}
