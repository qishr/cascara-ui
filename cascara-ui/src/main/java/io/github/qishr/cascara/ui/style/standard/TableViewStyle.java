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
import io.github.qishr.cascara.ui.style.part.CellRules;
import io.github.qishr.cascara.ui.style.part.ColumnHeaderRules;
import io.github.qishr.cascara.ui.style.part.ColumnLineRules;
import io.github.qishr.cascara.ui.style.part.RowAndCellPseudoRules;
import io.github.qishr.cascara.ui.style.part.RowRules;
import io.github.qishr.cascara.ui.style.part.TabularRules;
import io.github.qishr.cascara.ui.theme.ColorID;

public class TableViewStyle extends ControlStyle {
    private static final String CONTROL_CLASS = "table-view";
    private static final String ROW_CLASS = "table-row-cell";
    private static final String CELL_CLASS = "table-cell";

    public TableViewStyle() {
        super();

        incorporateRules(new TabularRules(CONTROL_CLASS));
        incorporateRules(new ColumnHeaderRules(CONTROL_CLASS));

        defineRule(newRule()
            .addSelector (classSelector("table-view.no-headers .column-header"))
            .addSelector (classSelector("table-view.no-headers .column-header-background"))
            .addSelector (classSelector("table-view.no-headers .column-header-background .filler "))
            .addDeclaration(FX_SIZE, literal("0.1px"), false)
            .addDeclaration(FX_PREF_HEIGHT, literal(ZERO), false)
            .addDeclaration(FX_PADDING, literal(ZERO), false)
            .addDeclaration(VISIBILITY, literal("collapse"), false)
            .build()
        );

        incorporateRules(new RowRules(ROW_CLASS));
        incorporateRules(new CellRules(CELL_CLASS));
        incorporateRules(new RowAndCellPseudoRules(CONTROL_CLASS, ROW_CLASS, CELL_CLASS));
        incorporateRules(new ColumnLineRules(CONTROL_CLASS));


        // Controls inside cells:
        // Although the following rules used in TreeTableView as well as TableView.
        // the same selectors are used so they only have to be defined once, therefore
        // they are not defined in TreeTableViewStyle,

        defineRule(newRule()
            .addSelector (classSelector("check-box-table-cell > .check-box"))
            .addDeclaration(FX_OPACITY, literal("1"), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("check-box-table-cell > .check-box > .box"))
            .addDeclaration(FX_BACKGROUND_COLOR, literal("transparent"), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.INPUT_BORDER)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("check-box-table-cell > .check-box:selected > .box > .mark"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.SIDEBAR_FOREGROUND)), false)
            .build()
        );
    }
}
