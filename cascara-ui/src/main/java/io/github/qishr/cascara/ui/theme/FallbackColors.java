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


package io.github.qishr.cascara.ui.theme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.qishr.cascara.common.property.Properties;
import io.github.qishr.cascara.ui.color.ColorException;
import io.github.qishr.cascara.ui.color.ColorUtil;
import javafx.scene.paint.Color;


public class FallbackColors {
    Map<String,List<String>> alternatives = new HashMap<>();

    public FallbackColors() {
        alternatives.put(ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND, List.of(ColorID.ACTIVITYBAR_ACTIVE_BACKGROUND, ColorID.SIDEBAR_TITLE_BACKGROUND, ColorID.STATUSBAR_BACKGROUND, ColorID.TAB_ACTIVE_BACKGROUND));
        alternatives.put(ColorID.ACTIVITYBAR_ACTIVE_BACKGROUND, List.of(ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND));
        alternatives.put(ColorID.ACTIVITYBAR_ACTIVE_FOREGROUND, List.of(ColorID.TAB_ACTIVE_FOREGROUND));
        alternatives.put(ColorID.ACTIVITYBAR_INACTIVE_FOREGROUND, List.of("derive(activitybarActiveForeground,-50%)", ColorID.FOREGROUND));
        alternatives.put(ColorID.ACTIVITYBAR_ACTIVE_BORDER, List.of(ColorID.TAB_ACTIVE_BORDER, ColorID.FOREGROUND));

        alternatives.put(ColorID.BUTTON_HOVER_BACKGROUND, List.of(ColorID.BUTTON_BACKGROUND));
        alternatives.put(ColorID.ACCENT_HOVER_BACKGROUND, List.of(ColorID.ACCENT_BACKGROUND));

        alternatives.put(ColorID.CHECKBOX_SELECTED_BACKGROUND, List.of(ColorID.BUTTON_BACKGROUND));
        alternatives.put(ColorID.CHECKBOX_FOREGROUND, List.of(ColorID.BUTTON_FOREGROUND, ColorID.INPUT_FOREGROUND));
        alternatives.put(ColorID.CHECKBOX_BACKGROUND, List.of(ColorID.INPUT_BACKGROUND));
        alternatives.put(ColorID.CHECKBOX_BORDER, List.of(ColorID.INPUT_BORDER));

        alternatives.put(ColorID.EDITOR_SELECTION_INACTIVE_BACKGROUND, List.of(ColorID.LIST_SELECTION_INACTIVE_BACKGROUND, ColorID.EDITOR_SELECTION_ACTIVE_BACKGROUND));
        alternatives.put(ColorID.EDITOR_SELECTION_HIGHLIGHT_BACKGROUND, List.of(ColorID.LIST_SELECTION_ACTIVE_BACKGROUND, ColorID.EDITOR_SELECTION_ACTIVE_BACKGROUND));
        alternatives.put(ColorID.EDITOR_ERROR_FOREGROUND, List.of(ColorID.BADGE_ERROR_BACKGROUND));
        alternatives.put(ColorID.EDITOR_WARN_FOREGROUND, List.of(ColorID.BADGE_WARN_BACKGROUND));
        alternatives.put(ColorID.EDITOR_INFO_FOREGROUND, List.of(ColorID.BADGE_INFO_BACKGROUND));

        alternatives.put(ColorID.LIST_WARNING_FOREGROUND, List.of(ColorID.BADGE_WARN_FOREGROUND, ColorID.SIDEBAR_FOREGROUND));

        alternatives.put(ColorID.MENU_BACKGROUND, List.of(ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND));
        alternatives.put(ColorID.MENU_FOREGROUND, List.of(ColorID.SIDEBAR_FOREGROUND, ColorID.FOREGROUND));
        alternatives.put(ColorID.MENU_BORDER, List.of(ColorID.WIDGET_BORDER, ColorID.TITLEBAR_ACTIVE_BACKGROUND, ColorID.SIDEBAR_BORDER));
        alternatives.put(ColorID.MENU_SELECTION_BACKGROUND, List.of(ColorID.LIST_HOVER_BACKGROUND, ColorID.BUTTON_HOVER_BACKGROUND));
        alternatives.put(ColorID.MENU_SELECTION_FOREGROUND, List.of(ColorID.BUTTON_FOREGROUND));
        alternatives.put(ColorID.MENU_SELECTION_BORDER, List.of(ColorID.MENU_SELECTION_BACKGROUND));
        alternatives.put(ColorID.MENU_SEPARATOR_BACKGROUND, List.of(ColorID.MENU_BORDER, ColorID.ACTIVITYBAR_INACTIVE_FOREGROUND));

        alternatives.put(ColorID.MENU_BAR_SELECTION_BACKGROUND, List.of(ColorID.MENU_SELECTION_BACKGROUND, ColorID.BUTTON_BACKGROUND, ColorID.ACTIVITYBAR_ACTIVE_BACKGROUND));
        alternatives.put(ColorID.MENU_BAR_SELECTION_FOREGROUND, List.of(ColorID.MENU_SELECTION_FOREGROUND, ColorID.BUTTON_FOREGROUND, ColorID.ACTIVITYBAR_ACTIVE_BACKGROUND));

        alternatives.put(ColorID.PANEL_BACKGROUND, List.of(ColorID.SIDEBAR_BACKGROUND, ColorID.EDITOR_BACKGROUND));

        alternatives.put(ColorID.LINK_ACTIVE_FOREGROUND, List.of(ColorID.BUTTON_FOREGROUND));
        alternatives.put(ColorID.LINK_INACTIVE_FOREGROUND, List.of(ColorID.BUTTON_FOREGROUND));

        alternatives.put(ColorID.NOTIFICATION_BACKGROUND, List.of(ColorID.SIDEBAR_BACKGROUND, ColorID.MENU_BACKGROUND));
        alternatives.put(ColorID.NOTIFICATION_FOREGROUND, List.of(ColorID.FOREGROUND, ColorID.SIDEBAR_FOREGROUND));
        alternatives.put(ColorID.NOTIFICATION_BORDER, List.of(ColorID.SIDEBAR_BORDER, ColorID.MENU_BORDER));
        alternatives.put(ColorID.NOTIFICATION_LINK, List.of(ColorID.LINK_INACTIVE_FOREGROUND, ColorID.LINK_ACTIVE_FOREGROUND, ColorID.SIDEBAR_FOREGROUND, ColorID.FOREGROUND));
        alternatives.put(ColorID.NOTIFICATION_ICON_INFO, List.of(ColorID.ICON_FOREGROUND));
        alternatives.put(ColorID.NOTIFICATION_ICON_ERROR, List.of(ColorID.BADGE_ERROR_BACKGROUND));
        alternatives.put(ColorID.NOTIFICATION_ICON_WARNING, List.of(ColorID.BADGE_WARN_BACKGROUND));

        // alternatives.put(ColorID.SIDEBAR_TITLE_BACKGROUND, List.of(ColorID.SIDEBAR_HEADER_BACKGROUND, ColorID.SIDEBAR_BACKGROUND, ColorID.ACTIVITYBAR_ACTIVE_BACKGROUND, ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND, ColorID.TAB_INACTIVE_BACKGROUND));
        alternatives.put(ColorID.SIDEBAR_TITLE_BACKGROUND, List.of(ColorID.SIDEBAR_BACKGROUND, ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND, ColorID.TAB_INACTIVE_BACKGROUND));
        alternatives.put(ColorID.SIDEBAR_TITLE_FOREGROUND, List.of(ColorID.BUTTON_FOREGROUND, ColorID.ACTIVITYBAR_INACTIVE_FOREGROUND));
        alternatives.put(ColorID.SIDEBAR_HEADER_BORDER, List.of(ColorID.SIDEBAR_HEADER_BACKGROUND));
        alternatives.put(ColorID.SIDEBAR_BORDER, List.of(ColorID.SIDEBAR_TITLE_BACKGROUND));
        alternatives.put(ColorID.SIDEBAR_HEADER_BACKGROUND, List.of(ColorID.SIDEBAR_TITLE_BACKGROUND));

        alternatives.put(ColorID.STATUSBAR_DEBUGGING_BACKGROUND, List.of(ColorID.STATUSBAR_BACKGROUND));
        alternatives.put(ColorID.STATUSBAR_DEBUGGING_FOREGROUND, List.of(ColorID.STATUSBAR_FOREGROUND));

        alternatives.put(ColorID.TAB_ACTIVE_BACKGROUND, List.of(ColorID.TAB_ACTIVE_BACKGROUND));
        alternatives.put(ColorID.TAB_SELECTED_BACKGROUND, List.of(ColorID.TAB_ACTIVE_BACKGROUND));
        alternatives.put(ColorID.TAB_SELECTED_FOREGROUND, List.of(ColorID.TAB_ACTIVE_FOREGROUND));
        alternatives.put(ColorID.TAB_SELECTED_BORDER_TOP, List.of(ColorID.TAB_ACTIVE_BACKGROUND));

        alternatives.put(ColorID.TOOLTIP_BORDER, List.of(ColorID.MENU_BORDER));

        alternatives.put(ColorID.RADIO_ACTIVE_BACKGROUND, List.of(ColorID.BUTTON_BACKGROUND));
        alternatives.put(ColorID.RADIO_ACTIVE_BORDER, List.of(ColorID.INPUT_BORDER));
        alternatives.put(ColorID.RADIO_ACTIVE_FOREGROUND, List.of(ColorID.BUTTON_FOREGROUND, ColorID.INPUT_FOREGROUND));
        alternatives.put(ColorID.RADIO_INACTIVE_HOVER_BACKGROUND, List.of(ColorID.INPUT_BACKGROUND));
        alternatives.put(ColorID.RADIO_INACTIVE_BORDER, List.of(ColorID.INPUT_BORDER));

        alternatives.put(ColorID.DOCAREA_HEADER_TABS_BACKGROUND, List.of(ColorID.SIDEBAR_HEADER_BACKGROUND, ColorID.TAB_INACTIVE_BACKGROUND, ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND));
        alternatives.put(ColorID.DOCAREA_HEADER_NOTABS_BACKGROUND, List.of(ColorID.DOCAREA_HEADER_NOTABS_BACKGROUND, ColorID.SIDEBAR_HEADER_BACKGROUND, ColorID.TAB_INACTIVE_BACKGROUND, ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND));
    }

    public String resolve(String missing, Properties colors) throws ColorException {
        List<String> alts = alternatives.get(missing);
        if (alts != null) {
            for (String alt : alts) {
                if (alt.startsWith("derive(")) {
                    String derived = deriveColor(alt, colors);
                    if (derived != null) {
                        return derived;
                    }
                }
                String v = colors.getString(alt);
                if (v != null) {
                    return v;
                }
            }
        }
        return null;
    }

    private String deriveColor(String expression, Properties colors) throws ColorException {
        int openParenthesis = expression.indexOf('(');
        int closeParenthesis = expression.lastIndexOf(')');
        if (openParenthesis < 0 || closeParenthesis < openParenthesis) {
            return null;
        }
        String params = expression.substring(openParenthesis + 1, closeParenthesis);
        int comma = params.indexOf(',');
        if (comma == -1) {
            return null;
        }
        String name = params.substring(0, comma).trim();
        String valueString = params.substring(comma + 1).trim();
        if (valueString.charAt(valueString.length()-1) == '%') {
            valueString = valueString.substring(0, valueString.length()-1);
        }
        int value = Integer.parseInt(valueString);
        return deriveColor(name, value, colors);
    }

    private String deriveColor(String name, int percentage, Properties colors) throws ColorException {
        String hexColor = colors.getString(name);
        if (hexColor != null && !hexColor.isBlank()) {
            Color baseColor = ColorUtil.toColor(hexColor);
            double brightness = 1 + (percentage / 100f);
            Color dereived = baseColor.deriveColor(0, 1, brightness, 1);
            return ColorUtil.toRGBHexCode(dereived);
        }
        return null;
    }
}
