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

public class ScrollBarStyle extends ControlStyle {
    public ScrollBarStyle() {
        super();
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            // .addDeclaration(FX_OPACITY, literal("0.4"), false)
            .addDeclaration(FX_OPACITY, literal("1"), false)
            .addDeclaration(FX_PREF_WIDTH, literal("8px"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar:hover"))
            .addSelector (classSelector("scroll-bar:pressed"))
            .addSelector (classSelector("scroll-bar:focused"))
            .addDeclaration(FX_OPACITY, literal("1"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar > .thumb"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SCROLL_THUMB_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal("4px"), false)
            .addDeclaration(FX_OPACITY, literal("1"), false)
            // .addDeclaration(FX_OPACITY, literal("0.7"), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar > .track"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .addDeclaration(FX_BORDER_RADIUS, literal(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar > .increment-button"))
            .addSelector (classSelector("scroll-bar > .decrement-button"))
            .addDeclaration(VISIBILITY, literal("hidden"), false)
            .addDeclaration(FX_MANAGED, literal(FALSE), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar:disabled"))
            .addDeclaration(VISIBILITY, literal("hidden"), false)
            .addDeclaration(FX_MANAGED, literal(FALSE), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar:horizontal > .increment-button > .increment-arrow"))
            .addDeclaration(FX_SHAPE, shape(" "), false)
            .addDeclaration(FX_PADDING, literal(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar:horizontal > .decrement-button > .decrement-arrow"))
            .addDeclaration(FX_SHAPE, shape(" "), false)
            .addDeclaration(FX_PADDING, literal(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar:vertical > .increment-button > .increment-arrow"))
            .addDeclaration(FX_SHAPE, shape(" "), false)
            .addDeclaration(FX_PADDING, literal(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("scroll-bar:vertical > .decrement-button > .decrement-arrow"))
            .addDeclaration(FX_SHAPE, shape(" "), false)
            .addDeclaration(FX_PADDING, literal(ZERO), false)
            .build()
        );
    }
}
