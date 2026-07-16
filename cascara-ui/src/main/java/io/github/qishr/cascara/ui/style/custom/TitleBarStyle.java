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

public class TitleBarStyle extends ControlStyle {
    public TitleBarStyle() {
        super();
        defineRule(newRule()
            .addSelector (classSelector("title-bar"))
            .addSelector (classSelector("title-bar-button-box"))
            .addSelector (classSelector("title-bar-title-box"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.TITLEBAR_ACTIVE_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("title-bar .line"))
            .addDeclaration(FX_FILL, values(color(ColorID.TITLEBAR_ACTIVE_FOREGROUND)), false)
            .addDeclaration(FX_OPACITY, literal("0.6"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("title-bar-title"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.TITLEBAR_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.TITLEBAR_ACTIVE_FOREGROUND)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .addDeclaration(FX_FONT_SIZE, values(em(1.1)), false)
            .addDeclaration(FX_FONT_FAMILY, literal("Dosis"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("title-bar-button"))
            .addSelector (classSelector("title-bar-button:hover"))
            .addSelector (classSelector("title-bar-button:focused"))
            .addSelector (classSelector("title-bar-button:focused:hover"))
            .addDeclaration(FX_BACKGROUND_INSETS, literal("1"), false)
            .addDeclaration(FX_BACKGROUND_COLOR, literal("linear-gradient(to bottom right, -color-control-foreground-dark, -color-control-foreground)"), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal(ZERO), false)
            .addDeclaration(FX_BORDER_RADIUS, literal("0, 0, 0"), false)
            .addDeclaration(FX_BORDER_INSETS, literal("0, 1, 2"), false)
            .addDeclaration(FX_BORDER_COLOR, literal("-color-control-foreground-dark -color-control-foreground  -color-control-foreground  -color-control-foreground-dark , derive(-color-control-background, -30%), -color-control-foreground -color-control-foreground-dark  -color-control-foreground-dark  -color-control-foreground"), false)
            .addDeclaration(FX_OPACITY, literal("0.6"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("title-bar-button:pressed"))
            .addDeclaration(FX_BACKGROUND_INSETS, literal("1"), false)
            .addDeclaration(FX_BACKGROUND_COLOR, literal("linear-gradient(to top left, -color-control-foreground-dark, -color-control-foreground)"), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal(ZERO), false)
            .addDeclaration(FX_BORDER_RADIUS, literal("0, 0, 0"), false)
            .addDeclaration(FX_BORDER_INSETS, literal("0, 1, 2"), false)
            .addDeclaration(FX_BORDER_COLOR, literal("-color-control-foreground-dark -color-control-foreground  -color-control-foreground  -color-control-foreground-dark, derive(-color-control-background, -30%), -color-control-foreground-dark -color-control-foreground  -color-control-foreground  -color-control-foreground-dark"), false)
            .addDeclaration(FX_OPACITY, literal("0.6"), false)
            .build()
        );
    }
}
