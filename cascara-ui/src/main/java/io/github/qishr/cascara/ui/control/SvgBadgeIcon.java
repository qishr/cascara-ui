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


package io.github.qishr.cascara.ui.control;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class SvgBadgeIcon extends StackPane {
    private final SvgIcon baseIcon;
    private Node badge;
    // private RotateTransition spinTransition;

    public SvgBadgeIcon(SvgIcon icon) {
        this.baseIcon = icon;
        this.getStyleClass().add("svg-badge-icon");

        // Ensure the container doesn't block layout
        this.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);

        // Add the base icon as the bottom layer
        getChildren().add(baseIcon);
    }

    /**
     * Attaches a badge to the icon.
     * @param badgeNode The node to overlay (Circle, Label, another SvgIcon, etc.)
     * @param alignment Where to place the badge (e.g., Pos.BOTTOM_RIGHT)
     */
    public void setBadge(Node badgeNode, Pos alignment) {
        if (this.badge != null) {
            getChildren().remove(this.badge);
        }
        this.badge = badgeNode;
        if (badgeNode != null) {
            getChildren().add(badgeNode);
            StackPane.setAlignment(badgeNode, alignment);
            // Prevent the badge from growing the container if not desired
            badgeNode.setMouseTransparent(true);
        }
    }

    // public void setSpinning(boolean spinning) {
    //     if (spinning) {
    //         if (spinTransition == null) {
    //             spinTransition = new RotateTransition(Duration.seconds(2), baseIcon);
    //             spinTransition.setByAngle(360);
    //             spinTransition.setCycleCount(Animation.INDEFINITE);
    //             spinTransition.setInterpolator(Interpolator.LINEAR);
    //         }
    //         spinTransition.play();
    //     } else if (spinTransition != null) {
    //         spinTransition.stop();
    //         baseIcon.setRotate(0);
    //     }
    // }

    public SvgIcon getBaseIcon() {
        return baseIcon;
    }
}