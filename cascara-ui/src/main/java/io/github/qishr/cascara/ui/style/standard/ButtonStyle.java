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

public class ButtonStyle extends ControlStyle {
    public ButtonStyle() {
        super();

        defineRule(newRule()
            .addSelector (classSelector("button"))
            .addSelector (classSelector("toggle-button"))
            .addDeclaration(SHRINK_ANIMATE_ON_PRESS, literal(TRUE), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values("2"), false)
            .addDeclaration(FX_BORDER_COLOR, values(TRANSPARENT), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .addDeclaration(FX_FONT_SIZE, literal("12"), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("button:hover"))
            .addSelector (classSelector("toggle-button:hover"))
            .addSelector (classSelector("button:focused:hover"))
            .addSelector (classSelector("toggle-button:focused:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_HOVER_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("button:pressed"))
            .addSelector (classSelector("toggle-button:pressed"))
            // .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("button:disabled"))
            .addSelector (classSelector("button:default:disabled"))
            .addSelector (classSelector("toggle-button:disabled"))
            .addDeclaration(FX_OPACITY, literal("0.4"), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("button:default"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("button:default:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.BUTTON_BACKGROUND), -40)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("button:default:pressed "))
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.BUTTON_BACKGROUND), -40)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("toggle-button:selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("toggle-button:selected:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.BUTTON_BACKGROUND), -40)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("toggle-button:selected:pressed"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.BUTTON_BACKGROUND), -40)), false)
            .build()
        );
    }
}
