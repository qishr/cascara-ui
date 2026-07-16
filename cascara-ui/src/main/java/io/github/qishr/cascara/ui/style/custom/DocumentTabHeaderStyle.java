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

public class DocumentTabHeaderStyle extends ControlStyle {
    public static final String DOCUMENT_TAB_HEADER = "doc-tab-header";
    public static final String DOCUMENT_TAB_PANE = "doc-tab-pane";

    public DocumentTabHeaderStyle() {
        super();

        defineRule(newRule()
            .addSelector (classSelector("doc-tab-pane"))
            .addDeclaration(FX_TAB_MIN_HEIGHT, values(em(1.5)), false)
            .addDeclaration(FX_TAB_MAX_HEIGHT, values(em(3)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("doc-tab-pane > .tab-header-area"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.DOCAREA_HEADER_TABS_BACKGROUND)), false)
            .addDeclaration(FX_PADDING, literal(ZERO), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("doc-tab-pane > .tab-header-area .headers-region"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.DOCAREA_HEADER_TABS_BACKGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("doc-tab-pane > .tab-header-area .tab-header-background"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.DOCAREA_HEADER_TABS_BACKGROUND)), false)
            .build()
        );

        // Tabs

        defineRule(newRule()
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab"))
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:top"))
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:right"))
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:bottom"))
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:left"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:top"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:right"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:bottom"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:right"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.TAB_INACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_COLOR, values(sides(color(ColorID.TAB_INACTIVE_BACKGROUND), color(ColorID.TAB_INACTIVE_BACKGROUND), color(ColorID.TAB_INACTIVE_BACKGROUND), color(ColorID.TAB_INACTIVE_BACKGROUND))), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.TAB_INACTIVE_FOREGROUND)), false)
            // .addDeclaration(FX_PADDING, sides(0,8,0,8), false)
            .addDeclaration(FX_PADDING, sides(0,0,0,0), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:selected"))
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:top:selected"))
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:right:selected"))
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:left:selected"))
            .addSelector (classSelector("doc-tab-pane > .tab-header-area > .headers-region > .tab:bottom:selected"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:selected"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:top:selected"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:right:selected"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:left:selected"))
            .addSelector (classSelector("doc-tab-pane:focused > .tab-header-area > .headers-region > .tab:bottom:selected"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.TAB_ACTIVE_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_INSETS, values(ZERO), false)
            .addDeclaration(FX_BORDER_COLOR, values(sides(color(ColorID.TAB_SELECTED_BORDER_TOP), color(ColorID.TAB_ACTIVE_BACKGROUND), color(ColorID.TAB_ACTIVE_BORDER), color(ColorID.TAB_ACTIVE_BACKGROUND))), false)
            .addDeclaration(FX_BORDER_WIDTH, values(px(1)), false)
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.TAB_ACTIVE_FOREGROUND)), false)
            .addDeclaration(FX_PADDING, sides(0,0,0,0), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER))
            .addDeclaration(FX_PADDING, sides(6,10,6,6), false)
            .build()
        );

        //
        // Button
        //

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + " .tab-button"))
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + " .tab-button:pressed"))
            .addDeclaration(FX_MAX_HEIGHT, values(px(15)), false)
            .addDeclaration(FX_MAX_WIDTH, values(px(15)), false)
            .addDeclaration(FX_MIN_HEIGHT, values(px(15)), false)
            .addDeclaration(FX_MIN_WIDTH, values(px(15)), false)
            .addDeclaration(FX_PADDING, values(px(2)), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), true)
            .addDeclaration(FX_BORDER_WIDTH, values(ZERO), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + " .cascara-svg-icon .cascara-svg-stroke"))
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + " .cascara-svg-icon .cascara-svg-fill"))
            .addDeclaration(FX_STROKE, values(color(ColorID.CONTROL_FOREGROUND)), true)
            .addDeclaration(FX_FILL, values(color(ColorID.CONTROL_FOREGROUND)), true)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + " .cascara-svg-icon:hover .cascara-svg-stroke"))
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + " .cascara-svg-icon:hover .cascara-svg-fill"))
            .addDeclaration(FX_STROKE, values(color(ColorID.BUTTON_FOREGROUND)), true)
            .addDeclaration(FX_FILL, values(color(ColorID.BUTTON_FOREGROUND)), true)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + " .tab-button:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_HOVER_BACKGROUND)), true)
            .build()
        );

        //
        // Label
        //

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + " .tab-label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.TAB_INACTIVE_FOREGROUND)), true)
            .addDeclaration(FX_PADDING, sides(2, 0, 2, 0), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + ":active .tab-label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.TAB_ACTIVE_FOREGROUND)), true)
            .addDeclaration(FX_PADDING, sides(2, 0, 2, 0), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + ":transient .tab-label"))
            .addDeclaration(FX_FONT_STYLE, values(ITALIC), true)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + ":gitmodified .tab-label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.GIT_MODIFIED_FOREGROUND)), true)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector(DOCUMENT_TAB_HEADER + ":gituntracked .tab-label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.GIT_UNTRACKED_FOREGROUND)), true)
            .build()
        );

    }
}
