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

public class ToolTipStyle extends ControlStyle {
    public ToolTipStyle() {
        super();
        defineRule(newRule()
            .addSelector (classSelector("tooltip"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.TEXT_FOREGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.TOOLTIP_BORDER)), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CONTROL_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal("0 0 0 0"), false)
            .addDeclaration(FX_PADDING, literal("0.3em 0.6em 0.3em 0.6em"), false)
            .addDeclaration(FX_FONT_WEIGHT, literal(LIGHTER), false)
            .addDeclaration(FX_FONT_SIZE, literal("1.16em"), false)
            .addDeclaration(FX_BORDER_WIDTH, literal("1px"), false)
            // .addDeclaration(FX_EFFECT, literal("dropshadow( three-pass-box , rgba(0,0,0,0.5) , 10, 0.0 , 0 , 3 )"), false)
            .addDeclaration(FX_EFFECT, values(dropshadow(color(ColorID.WIDGET_SHADOW))), false)
            .build()
        );
    }
}
