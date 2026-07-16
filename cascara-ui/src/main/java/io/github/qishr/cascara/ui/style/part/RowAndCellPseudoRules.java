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

public class RowAndCellPseudoRules extends PartStyle {
    public RowAndCellPseudoRules(String controlClass, String rowClass, String cellClass) {
        super();
        this.controlClass = controlClass;

        // Selected

        defineRule(newRule() // Row
            .addSelector (pseudoSelector("row-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_INACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // Cell inside a selected row
            .addSelector (pseudoSelector("row-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected ." + cellClass+ ""))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_INACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // selected Cell
            .addSelector (pseudoSelector("cell-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ " ." + cellClass+ ":selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_INACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // Things inside it
            .addSelector (childSelector(" .virtual-flow > .clipped-container > .sheet > ." + rowClass+ " ." + cellClass+ ":selected .text"))
            .addSelector (childSelector(" .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected ." + cellClass+ " .text"))
            .addSelector (childSelector(" .virtual-flow > .clipped-container > .sheet > ." + rowClass+ " ." + cellClass+ ":selected > .text"))
            .addSelector (childSelector(" .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected ." + cellClass+ " > .text"))
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_INACTIVE_FOREGROUND)), false)
            .build()
        );


        // Focused & Selected

        defineRule(newRule() // Selected Row
            .addSelector (pseudoSelector("focused:row-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_FOCUS_OUTLINE)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // Cell inside a focused & selected row
            .addSelector (pseudoSelector("focused:row-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected ." + cellClass+ ""))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // focused & selected Cell
            .addSelector (pseudoSelector("focused:cell-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ " ." + cellClass+ ":selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_FOCUS_OUTLINE)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // Things inside it
            .addSelector (pseudoSelector("focused > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ " ." + cellClass+ ":selected > .text"))
            .addSelector (pseudoSelector("focused > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected ." + cellClass+ " > .text"))
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );


        // Hovered

        defineRule(newRule() // Hovered Row
            .addSelector (pseudoSelector("row-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":filled:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_HOVER_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_HOVER_BACKGROUND)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // Hovered Cell inside a hovered row
            .addSelector (pseudoSelector("row-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":filled:hover ." + cellClass+ ""))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_HOVER_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_HOVER_BACKGROUND)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // Hovered Cell
            .addSelector (pseudoSelector("cell-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ " ." + cellClass+ ":filled:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_HOVER_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_FOCUS_OUTLINE)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );


        // Hovered & Selected & Focused

        defineRule(newRule() // Hovered & Selected Row
            .addSelector (pseudoSelector("focused:row-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_FOCUS_OUTLINE)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // Hovered & Selected Cell inside a selected row
            .addSelector (pseudoSelector("focused:row-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ ":selected:hover ." + cellClass+ ""))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );

        defineRule(newRule() // Hovered & Selected Cell
            .addSelector (pseudoSelector("focused:cell-selection > .virtual-flow > .clipped-container > .sheet > ." + rowClass+ " ." + cellClass+ ":selected:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.LIST_SELECTION_FOCUS_OUTLINE)), false)
            .addDeclaration(FX_FILL, values(color(ColorID.LIST_SELECTION_ACTIVE_FOREGROUND)), false)
            .build()
        );
    }
}
