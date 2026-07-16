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

public class TabPaneStyle extends ControlStyle {
    public TabPaneStyle() {
        super();

        //
        // Tab Pane
        //

        defineRule(newRule()
            .addSelector (classSelector("tab-pane"))
            .addSelector (classSelector("tab-pane > .tab-header-area"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .tab-header-background"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area .tab-header-background"))
            .addDeclaration(FX_BORDER_COLOR, values(TRANSPARENT, TRANSPARENT, derive(color(ColorID.FOREGROUND), -35),  TRANSPARENT), false)
            .addDeclaration(FX_BORDER_INSETS, literal("0 0 0 0"), false)
            .addDeclaration(FX_BORDER_WIDTH, literal("0 0 1 0"), false)
            .build()
        );

        //
        // Tab
        //

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:top"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:right"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:bottom"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:left"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:top"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:right"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:bottom"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:right"))

            .addDeclaration(FX_BACKGROUND_INSETS, literal("0"), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal("0"), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)

            .addDeclaration(FX_BORDER_COLOR, values(TRANSPARENT, TRANSPARENT, derive(color(ColorID.FOREGROUND), -35), TRANSPARENT), false)
            .addDeclaration(FX_BORDER_WIDTH, literal("0 0 1 0"), false)
            .addDeclaration(FX_BORDER_RADIUS, literal("0 0 0 0"), false)

            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .addDeclaration(FX_PADDING, sides(0,8,0,8), false)

            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:selected"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:top:selected"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:right:selected"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:left:selected"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:bottom:selected"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:selected"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:top:selected"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:right:selected"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:left:selected"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:bottom:selected"))

            .addDeclaration(FX_BACKGROUND_RADIUS, values(ZERO), false)
            .addDeclaration(FX_BACKGROUND_INSETS, sides(ZERO, ZERO, ZERO, ZERO), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)

            .addDeclaration(FX_BORDER_WIDTH, literal("0 0 1 0"), false)
            .addDeclaration(FX_BORDER_INSETS, sides(ZERO, ZERO, ZERO, ZERO), false)
            .addDeclaration(FX_BORDER_RADIUS, values(ZERO), false)
            .addDeclaration(FX_BORDER_COLOR, values(color(ColorID.FOREGROUND)), false)

            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .addDeclaration(FX_PADDING, sides(0,8,0,8), false)

            .build()
        );


        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:top:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:right:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:left:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:bottom:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:top:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:right:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:left:selected .focus-indicator"))
            .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab:bottom:selected .focus-indicator"))
            .addDeclaration(FX_BORDER_WIDTH, literal("0 0 0 0"), false)
            .addDeclaration(FX_BORDER_INSETS, sides(ZERO, ZERO, ZERO, ZERO), false)
            .addDeclaration(FX_BORDER_RADIUS, values(ZERO), false)
            // .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.DOCUMENT_BACKGROUND)), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)

            .build()
        );

        //
        // Label
        //

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab > .tab-container > .tab-label"))
            .addDeclaration(FX_TEXT_FILL, values(derive(color(ColorID.FOREGROUND), -35)), false)
            .addDeclaration(FX_FONT_SIZE, values(em(0.8)), false)
            .addDeclaration(TEXT_TRANSFORM, values(UPPERCASE), false)
            .addDeclaration(FX_PADDING, values(px(3)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:hover > .tab-container > .tab-label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:selected > .tab-container > .tab-label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .build()
        );
        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab:selected:hover > .tab-container > .tab-label"))
            .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
            .build()
        );

        //
        // Content
        //

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-content-area"))
            // .addDeclaration(FX_PADDING, values(sides(px(16), ZERO, ZERO, ZERO)), false)
            .addDeclaration(FX_PADDING, values(ZERO), false)
            .build()
        );

        //
        // More tabs button
        //

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .control-buttons-tab > .container > .tab-down-button"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal("0 0 4 0"), false)
            .addDeclaration(FX_PADDING, literal("4 8 9 8"), false)
            .addDeclaration(FX_BACKGROUND_RADIUS, literal(px(2)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .control-buttons-tab > .container > .tab-down-button:hover"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .control-buttons-tab > .container > .tab-down-button:pressed"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_BACKGROUND)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .control-buttons-tab > .container > .tab-down-button > .arrow"))
            .addDeclaration(FX_PADDING, literal("0.08em"), false)
            .addDeclaration(FX_SCALE_SHAPE, literal(FALSE), false)
            .addDeclaration(FX_SHAPE, shape("M 0 0 h 7 l -3.5 4 z"), false)
            .addDeclaration(FX_BACKGROUND_INSETS, literal(ZERO), false)
            .addDeclaration(FX_BACKGROUND_COLOR, values(derive(color(ColorID.FOREGROUND), -35)), false)
            .build()
        );

        defineRule(newRule()
            .addSelector (classSelector("tab-pane > .tab-header-area > .control-buttons-tab > .container > .tab-down-button:hover > .arrow"))
            .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.BUTTON_FOREGROUND)), false)
            .build()
        );







        // // Tab pane inside tab pane

        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane"))
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-header-area"))
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-header-area > .headers-region"))
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-header-area > .tab-header-background"))
        //     .addDeclaration(FX_BACKGROUND_COLOR, values(TRANSPARENT), false)
        //     .build()
        // );
        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-header-area .tab-header-background"))
        //     .addDeclaration(FX_BORDER_COLOR, values(TRANSPARENT, TRANSPARENT, derive(color(ColorID.FOREGROUND), -50),  TRANSPARENT), false)
        //     .addDeclaration(FX_BORDER_INSETS, literal("0 0 0 0"), false)
        //     .addDeclaration(FX_BORDER_WIDTH, literal("0 0 1 0"), false)
        //     .build()
        // );
        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane .tab"))
        //     .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.DOCUMENT_BACKGROUND)), false)
        //     .addDeclaration(FX_BACKGROUND_INSETS, literal("0"), false)
        //     .addDeclaration(FX_BACKGROUND_RADIUS, literal("0"), false)
        //     .addDeclaration(FX_BORDER_COLOR, values(TRANSPARENT, TRANSPARENT, derive(color(ColorID.FOREGROUND), -35),  TRANSPARENT), false)
        //     .addDeclaration(FX_BORDER_WIDTH, literal("0 0 1 0"), false)
        //     .addDeclaration(FX_BORDER_RADIUS, literal("0 0 0 0"), false)
        //     .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
        //     .build()
        // );
        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane .tab:selected"))
        //     .addDeclaration(FX_BACKGROUND_COLOR, values(color(ColorID.DOCUMENT_BACKGROUND)), false)
        //     .addDeclaration(FX_BACKGROUND_INSETS, literal("0 0 0 0"), false)
        //     .addDeclaration(FX_BORDER_COLOR, literal("transparent transparent -color-foreground transparent"), false)
        //     .addDeclaration(FX_BORDER_WIDTH, literal("0 0 1 0"), false)
        //     .build()
        // );

        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-header-area > .headers-region > .tab > .tab-container > .tab-label"))
        //     .addDeclaration(FX_TEXT_FILL, values(derive(color(ColorID.FOREGROUND), -35)), false)
        //     .addDeclaration(FX_FONT_SIZE, values(em(0.8)), false)
        //     .addDeclaration(TEXT_TRANSFORM, values(UPPERCASE), false)
        //     .addDeclaration(FX_PADDING, values(px(3)), false)
        //     .build()
        // );
        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-header-area > .headers-region > .tab:hover > .tab-container > .tab-label"))
        //     .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
        //     .build()
        // );
        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-header-area > .headers-region > .tab:selected > .tab-container > .tab-label"))
        //     .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
        //     .build()
        // );
        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-header-area > .headers-region > .tab:selected:hover > .tab-container > .tab-label"))
        //     .addDeclaration(FX_TEXT_FILL, values(color(ColorID.FOREGROUND)), false)
        //     .build()
        // );

        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-content-area .tab-pane > .tab-content-area"))
        //     .addDeclaration(FX_PADDING, values(sides(px(16), ZERO, ZERO, ZERO)), false)
        //     .build()
        // );












        // defineRule(newRule()
        //     .addSelector (classSelector("tab-pane > .tab-header-area > .headers-region > .tab"))
        //     .addSelector (classSelector("tab-pane:focused > .tab-header-area > .headers-region > .tab"))
        //     .build()
        // );

    }
}
