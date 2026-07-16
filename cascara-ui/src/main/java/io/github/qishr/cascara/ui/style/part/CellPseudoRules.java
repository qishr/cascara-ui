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


package io.github.qishr.cascara.ui.style.part;

import io.github.qishr.cascara.ui.style.PartStyle;
import io.github.qishr.cascara.ui.theme.ColorID;

public class CellPseudoRules extends PartStyle {
    public CellPseudoRules(String controlClass, String cellClass) {
        super();
        this.controlClass = controlClass;

        // Selected

        defineRule(newRule()
            .addSelector (childSelector(".virtual-flow > .clipped-container > .sheet > ." + cellClass + ":filled:selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.LIST_SELECTION_INACTIVE_FOREGROUND)), false)
            .build()
        );


        // Focused & Selected

        defineRule(newRule()
            .addSelector (pseudoSelector("focused > .virtual-flow > .clipped-container > .sheet > ." + cellClass + ":filled:selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_FOCUS_OUTLINE)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );


        // Hovered

        defineRule(newRule()
            .addSelector (childSelector(".virtual-flow > .clipped-container > .sheet > ." + cellClass + ":filled:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_HOVER_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_HOVER_BACKGROUND)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );


        // Hovered & Focused & Selected

        defineRule(newRule()
            .addSelector (pseudoSelector("focused > .virtual-flow > .clipped-container > .sheet > ." + cellClass + ":filled:selected:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_FOCUS_OUTLINE)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );
    }
}
