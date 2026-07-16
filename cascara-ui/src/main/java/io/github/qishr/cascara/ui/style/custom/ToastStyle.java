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

public class ToastStyle extends ControlStyle {
    public ToastStyle() {
        super();
        defineRule(newRule()
            .addSelector (classSelector("toast-root"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.NOTIFICATION_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.NOTIFICATION_BORDER)), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, values(px(4)), false)
            .addDeclaration(FX_BORDER_RADIUS, values(px(4)), false)
            .addDeclaration(FX_PADDING, values(px(12)), false)
            .addDeclaration(FX_EFFECT, values(dropshadow(color(ColorID.WIDGET_SHADOW))), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("toast-title"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.NOTIFICATION_LINK)), false)
            .addDeclaration(FX_FONT_WEIGHT, values(BOLD), false)
            .addDeclaration(FX_FONT_SIZE, values(px(13)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("toast-message"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.NOTIFICATION_FOREGROUND)), false)
            .addDeclaration(FX_FONT_SIZE, values(px(12)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("toast-root.info"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.NOTIFICATION_ICON_INFO)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("toast-root.error"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.NOTIFICATION_ICON_ERROR)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("toast-root.warning"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.NOTIFICATION_ICON_WARNING)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("toast-root.success"))
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.NOTIFICATION_ICON_INFO)), false)
            .build()
        );
    }
}