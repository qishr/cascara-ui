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

public class DialogStyle extends ControlStyle {
    public DialogStyle() {
        super();
        defineRule(newRule()
            .addSelector (classSelector("dialog-pane"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.MENU_BORDER)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.CONTROL_FOREGROUND)), false)
            .build()
        );

        // Header Panel

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane .header-panel"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.DOCUMENT_BACKGROUND)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane:header .header-panel"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.DOCUMENT_BACKGROUND)), false)
            .addDeclaration(FX_PADDING, literal("0.833em"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane:header .header-panel .graphic-container"))
            .addDeclaration(FX_PADDING, literal("0 0 0 0.8em"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane:header .header-panel .label"))
            .addDeclaration(FX_FONT_SIZE, values(em(1.1)), false)
            .addDeclaration(FX_TEXT_WRAP, values(TRUE), false) // TODO: wrap-text or text-wrap
            .build()
        );

        // Main area

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane .label.content"))
            .addSelector (classSelector("dialog-pane > .content.label"))
            .addDeclaration(FX_FONT_SIZE, values(em(1.1)), false)
            .addDeclaration(FX_PADDING, literal("10 15 10 15"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane > .content"))
            .addDeclaration(FX_PADDING, values(em(0.8)), false)
            .build()
        );

        // Expandable Area

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane > .expandable-content"))
            .addDeclaration(FX_PADDING, values(em(0.6)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane > .button-bar > .container"))
            .addDeclaration(FX_PADDING, values(em(0.8)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.CONTROL_FOREGROUND)), false)
            .build()
        );

        // Button Area

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane .button"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("dialog-pane > .button-bar > .container > .details-button"))
            .addDeclaration(FX_ALIGNMENT, values(BASELINE_LEFT), false)
            .addDeclaration(FX_FOCUS_TRAVERSABLE, values(FALSE), false)
            .addDeclaration(FX_PADDING, values(em(0.4)), false)
            .addDeclaration(FX_UNDERLINE, values(FALSE), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.CONTROL_FOREGROUND)), false)
            .build()
        );


        // .dialog-pane > .button-bar > .container > .details-button.more {
        //     -fx-graphic: url("dialog-more-details.png");
        // }

        // .dialog-pane > .button-bar > .container > .details-button.less {
        //     -fx-graphic: url("dialog-fewer-details.png");
        // }


        defineRule(newRule()
            .addSelector (classSelector("dialog-pane > .button-bar > .container > .details-button:hover"))
            .addDeclaration(FX_UNDERLINE, values(TRUE), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );
    }
}
