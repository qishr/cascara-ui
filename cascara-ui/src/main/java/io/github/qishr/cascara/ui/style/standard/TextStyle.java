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

public class TextStyle extends ControlStyle {
    public static final String TITLE = "title";
    public static final String HEADING = "heading";
    public static final String SUBHEADING = "subheading";
    public static final String BOLD = "bold";

    public TextStyle() {
        super();

        // Text in controls
        defineRule(newRule()
            .addSelector (classSelector("text"))
            .addDeclaration(FX_BOUNDS_TYPE, literal("logical_vertical_center"), false)
            .addDeclaration(FX_FONT_SMOOTHING_TYPE, literal("lcd"), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .build()
        );

        // defineRule(newRule()
        //     .addSelector (classSelector("paragraph > *"))
        //     .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
        //     .addDeclaration(FX_FILL, values(color(ColorID.FOREGROUND)), false)
        //     .build()
        // );

        defineRule(newRule()
            .addSelector (classSelector("label"))
            .addSelector (classSelector("text-flow"))
            .addSelector (classSelector("paragraph > *"))
            .addSelector (classSelector("text-flow > *"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.FOREGROUND)), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("label." + TITLE))
            .addSelector (classSelector("text-flow ." + TITLE))
            // .addSelector (classSelector("label." + TITLE + " .text")) // TODO: Should this be removed?
            .addDeclaration(FX_FONT_SIZE, values(em(2)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(LIGHTER), false)
            .addDeclaration(FX_FONT_FAMILY, literal("Dosis Medium"), false)
            .build()
        );

        // TODO: cascara://organizer/CASC-00027AF9 - this might need removed!!!
        defineRule(newRule()
            .addSelector (classSelector("label." + HEADING))
            .addSelector (classSelector("text-flow ." + HEADING))
            .addDeclaration(FX_FONT_SIZE, values(em(1.2)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("label." + SUBHEADING))
            .addSelector (classSelector("text-flow ." + SUBHEADING))
            .addDeclaration(FX_FONT_SIZE, values(em(1.1)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("label." + BOLD))
            .addSelector (classSelector("text-flow ." + BOLD))
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .build()
        );
    }
}
