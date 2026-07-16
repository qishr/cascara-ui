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

public class CodeAreaStyle extends ControlStyle {
    public CodeAreaStyle() {
        super();

        defineRule(newRule()
            .addSelector (classSelector("default-text"))
            .addDeclaration(FX_FILL, values(color(ColorID.TEXT_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("code-area .diag-error"))
            .addDeclaration(RTFX_UNDERLINE_COLOR, values(color(ColorID.EDITOR_ERROR_FOREGROUND)), true)
            .addDeclaration(RTFX_UNDERLINE_WIDTH, values(px(2)), true)
            .addDeclaration(RTFX_UNDERLINE_DASH_ARRAY, literal("2 2"), true)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("code-area .diag-warning"))
            .addDeclaration(RTFX_UNDERLINE_COLOR, values(color(ColorID.EDITOR_WARN_FOREGROUND)), true)
            .addDeclaration(RTFX_UNDERLINE_WIDTH, values(px(2)), true)
            .addDeclaration(RTFX_UNDERLINE_DASH_ARRAY, literal("4 4"), true)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("code-area .diag-info"))
            .addDeclaration(RTFX_UNDERLINE_COLOR, values(color(ColorID.EDITOR_INFO_FOREGROUND)), true)
            .addDeclaration(RTFX_UNDERLINE_WIDTH, values(px(2)), true)
            .addDeclaration(RTFX_UNDERLINE_DASH_ARRAY, literal("1 3"), true)
            .build()
        );
    }
}
