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

public class ToolBarStyle extends ControlStyle {
    public ToolBarStyle() {
        super();
        defineRule(newRule()
            .addSelector (classSelector("tool-bar"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.CONTROL_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("toolbar-separator"))
            .addDeclaration(FX_SEPARATOR_STROKE, literal("red"), false)
            .build()
        );

        // Buttons

        defineRule(newRule()
            .addSelector (classSelector("tool-bar .button"))
            .addSelector (classSelector("tool-bar .toggle-button"))
            .addSelector (classSelector("tool-bar .menu-button"))
            .addSelector (classSelector("tool-bar .split-menu-button, .tool-bar .split-menu-button > .label, .tool-bar .split-menu-button > .arrow-button"))
            .addDeclaration(FX_BACKGROUND_COLOR, literal(TRANSPARENT), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.ACTIVITYBAR_INACTIVE_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .button:hover"))
            .addSelector (classSelector("tool-bar .toggle-button:hover"))
            .addSelector (classSelector("tool-bar .menu-button:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .button:hover"))
            .addSelector (classSelector("tool-bar .toggle-button:hover"))
            .addDeclaration(FX_BORDER_WIDTH, literal(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .split-menu-button:hover > .label"))
            .addSelector (classSelector("tool-bar .split-menu-button:hover > .arrow-button"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .split-menu-button > .label:hover"))
            .addDeclaration(FX_BORDER_COLOR, literal(TRANSPARENT), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .split-menu-button > .arrow-button:hover"))
            .addDeclaration(FX_BORDER_COLOR, literal(TRANSPARENT), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .button:pressed"))
            .addSelector (classSelector("tool-bar .toggle-button:pressed"))
            .addSelector (classSelector("tool-bar .menu-button:pressed"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );

        // Toggle Buttons

        defineRule(newRule()
            .addSelector (classSelector("tool-bar .toggle-button:selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, literal(TRANSPARENT), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.ACTIVITYBAR_INACTIVE_FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .toggle-button:selected:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.BUTTON_BACKGROUND), 40)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .toggle-button:selected:pressed"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.BUTTON_BACKGROUND), 40)), false)
            .build()
        );

        // Menu Buttons

        defineRule(newRule()
            .addSelector (classSelector("tool-bar .menu-button"))
            .addDeclaration(FX_BORDER_COLOR, literal(TRANSPARENT), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .menu-button:showing"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .split-menu-button > .label"))
            .addDeclaration(FX_PADDING, literal("0.333333em 0.333333em 0.333333em 0.5em"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .split-menu-button > .arrow-button"))
            .addDeclaration(FX_PADDING, literal("0.5em 0.583333em 0.5em 0.333333em"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .split-menu-button:showing > .arrow-button"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tool-bar .split-menu-button:showing > .label"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );
    }
}
