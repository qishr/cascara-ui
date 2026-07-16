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


package io.github.qishr.cascara.ui.render.control;

import io.github.qishr.cascara.ui.api.data.DataProvider;
import io.github.qishr.cascara.ui.api.render.ScalarRenderer;
import io.github.qishr.cascara.ui.form.FieldMetadata;
import io.github.qishr.cascara.ui.option.TagOption;
import io.github.qishr.cascara.ui.render.AbstractScalarRenderer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;

public class TagRenderer extends AbstractScalarRenderer implements ScalarRenderer {

    public TagRenderer() {
        super("cascara/tag", null, null);
    }

    @Override
    public Node render(Labeled cell, Object data, DataProvider dataProvider, FieldMetadata meta) {
        if (data instanceof TagOption tag) {
            String bgColor = tag.getColor();
            String textColor = getContrastColor(bgColor);

            // Shadow for "White" text to prevent washing out
            String textShadow = textColor.equalsIgnoreCase("white")
                ? "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 2, 0, 0, 1);"
                : "";

            Label pillText = new Label(tag.getOptionText());

            // Use !important-style logic by ensuring we set the text fill
            // and opacity explicitly on the label itself.
            pillText.setStyle(String.format(
                "-fx-text-fill: %s !important;" +      // Force the color here
                // "-fx-font-weight: bold; " +
                "-fx-opacity: 1.0; " +        // Ensure no "ghosting"
                "%s",
                textColor, textShadow
            ));

            // Create the container for the background
            javafx.scene.layout.StackPane container = new javafx.scene.layout.StackPane(pillText);
            container.setStyle(String.format(
                "-fx-background-color: %s; " +
                "-fx-background-radius: 10; " +
                "-fx-padding: 2 8 2 8;",
                bgColor
            ));

            cell.setGraphic(container);
            cell.setText(null);
            cell.setContentDisplay(javafx.scene.control.ContentDisplay.GRAPHIC_ONLY);
            return pillText;
        }
        return null;
    }

    public static String getContrastColor(String colorStr) {
        try {
            if (colorStr != null && colorStr.startsWith("#")) {
                int r, g, b;
                if (colorStr.length() == 7) {
                    r = Integer.parseInt(colorStr.substring(1, 3), 16);
                    g = Integer.parseInt(colorStr.substring(3, 5), 16);
                    b = Integer.parseInt(colorStr.substring(5, 7), 16);
                } else {
                    r = Integer.parseInt(colorStr.substring(1, 2), 16) * 17;
                    g = Integer.parseInt(colorStr.substring(2, 3), 16) * 17;
                    b = Integer.parseInt(colorStr.substring(3, 4), 16) * 17;
                }

                // Perceived brightness formula
                double luminance = (0.299 * r + 0.587 * g + 0.114 * b) / 255;

                // 0.5 is the standard threshold, but 0.6 is often safer for "Tag" aesthetics
                return (luminance > 0.6) ? "black" : "white";
            }
        } catch (Exception ignored) {}
        return "white"; // Default to white
    }
}
