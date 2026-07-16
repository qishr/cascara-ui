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


package io.github.qishr.cascara.ui.window;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;

/**
 * Defines the structural configuration of a custom title bar.
 * The visual "look" is handled via the styleClass in CSS.
 */
public record TitleBarTheme(
    String id,
    String styleClass,
    double height,
    Pos titleAlignment,
    List<TitleButtonType> leftButtons,
    List<TitleButtonType> rightButtons,
    boolean useStretchingLines,
    ShadowProfile activeProfile,
    ShadowProfile inactiveProfile
) {
    public enum TitleButtonType {
        CLOSE_BUTTON, MINIMIZE_BUTTON, MAXIMIZE_BUTTON, MENU_BUTTON
    }

    // Factory method for your current System 8 look
    public static TitleBarTheme macSystem8() {
        return new TitleBarTheme(
            "sys8",
            "theme-sys8",
            24.0,
            Pos.CENTER,
            List.of(TitleButtonType.CLOSE_BUTTON),
            List.of(TitleButtonType.MAXIMIZE_BUTTON), // System 8 "Zoom" box
            true,
            // In your Designer State
            new ShadowProfile(15, Color.rgb(0,0,0,0.5), 2),
            new ShadowProfile(8, Color.rgb(0,0,0,0.3), 1)
        );
    }

    // Factory method for a modern Windows look
    public static TitleBarTheme modern() {
        return new TitleBarTheme(
            "modern",
            "theme-modern",
            32.0,
            Pos.CENTER_LEFT,
            List.of(),
            List.of(TitleButtonType.MINIMIZE_BUTTON, TitleButtonType.MAXIMIZE_BUTTON, TitleButtonType.CLOSE_BUTTON),
            false,
            new ShadowProfile(15, Color.rgb(0,0,0,0.5), 1),
            new ShadowProfile(8, Color.rgb(0,0,0,0.3), 1)
        );
    }

    public static TitleBarTheme windows7() {
        return new TitleBarTheme(
            "win7",
            "theme-win7",
            30.0,
            Pos.CENTER_LEFT,
            List.of(),
            List.of(TitleButtonType.MINIMIZE_BUTTON, TitleButtonType.MAXIMIZE_BUTTON, TitleButtonType.CLOSE_BUTTON),
            false,
            new ShadowProfile(15, Color.rgb(0,0,0,0.5), 2),
            new ShadowProfile(8, Color.rgb(0,0,0,0.3), 1)
        );
    }


        // public static TitleBarTheme windows7() {
        //     return new TitleBarTheme(
        //         "win7",
        //         "theme-win7",
        //         30.0,
        //         Pos.CENTER_LEFT,
        //         List.of(), // No buttons on left
        //         List.of(TitleButtonType.MINIMIZE_BUTTON,
        //                 TitleButtonType.MAXIMIZE_BUTTON,
        //                 TitleButtonType.CLOSE_BUTTON),
        //         false,
        //         new ShadowProfile(15, Color.rgb(0,0,0,0.5), 2),
        //         new ShadowProfile(8, Color.rgb(0,0,0,0.3), 1)
        //     );
        // }


    // Factory method for a modern Windows look
    public static TitleBarTheme windows10() {
        return new TitleBarTheme(
            "win10",
            "theme-win10",
            32.0,
            Pos.CENTER_LEFT,
            List.of(),
            List.of(TitleButtonType.MINIMIZE_BUTTON, TitleButtonType.MAXIMIZE_BUTTON, TitleButtonType.CLOSE_BUTTON),
            false,
            new ShadowProfile(15, Color.rgb(0,0,0,0.5), 2),
            new ShadowProfile(8, Color.rgb(0,0,0,0.3), 1)
        );
    }
}
