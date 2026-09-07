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


package io.github.qishr.cascara.ui.color;

import io.github.qishr.cascara.ui.theme.Variation;
import javafx.scene.paint.Color;

public class ColorUtil {

    private ColorUtil() {
        // No public constructor
    }

    public static String toRGBHexCode(Color color) {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }

    public static String toRGBHexCode(Color color, double alpha) {
        return String.format( "#%02X%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ),
            (int)alpha);
    }

    public static Color toColor(String hex) throws ColorException {
        try {
            return Color.valueOf(hex);
        } catch (IllegalArgumentException e) {
            throw new ColorException("Invalid color specification", e);
        }
    }

    public static Color lerp(Color color1, Color color2, double t) {
        t = Math.max(0, Math.min(1, t));

        double red = color1.getRed() + t * (color2.getRed() - color1.getRed());
        double green = color1.getGreen() + t * (color2.getGreen() - color1.getGreen());
        double blue = color1.getBlue() + t * (color2.getBlue() - color1.getBlue());
        double opacity = color1.getOpacity() + t * (color2.getOpacity() - color1.getOpacity());

        return new Color(red, green, blue, opacity);
    }

    public static void processColor(ColorDefinition color, Variation variation) throws ColorException {
        if (color.usesTransform()) {
            applyTransform(color, variation);
        } else if (color.usesPaletteColor()) {
            lookupPaletteColor(color, variation);
        } else if (color.usesBaseColor()) {
            lookupBaseColor(color, variation);
        } else if (color.usesLerp()) {
            applyLerp(color);
        }
    }

    private static void applyTransform(ColorDefinition definition, Variation variation) throws ColorException {
        String transformId = definition.getTransformId();
        if (transformId == null) {
            System.out.println("transformId = null");
            return;
        }

        ColorDefinition transformDefinition = variation.getTransformDefinition(transformId);
        if (transformDefinition == null) {
            System.out.println("transformDefinition = null");
            return;
        }

        String baseColorId = definition.getBaseColorId();
        if (baseColorId == null) {
            System.out.println("baseColorId = null");
            return;
        }

        String transform = transformDefinition.getTransformDefinition();
        if (transform == null) {
            System.out.println("transform = null");
            return;
        }

        ColorDefinition baseColorDef = variation.getBaseColor(baseColorId);
        if (baseColorDef == null) {
            System.out.println("baseColorDef = null");
            return;
        }

        Color baseColor = toColor(baseColorDef.getHexColor());

        int colon = transform.indexOf(":");
        if (colon == -1) {
            return;
        }
        String transformFunction = transform.substring(0, colon).toLowerCase();
        String transformParamString = transform.substring(colon + 1);
        double transformParam = Double.parseDouble(transformParamString);

        Color result;
        if (transformFunction.equals("brightness")) {
            result = brightnessFunc(baseColor, transformParam);
        } else if (transformFunction.equals("saturation")) {
            result = saturationFunc(baseColor, transformParam);
        } else {
            return;
        }
        definition.setHexColor(toRGBHexCode(result));
    }

    /// Gets the theme variation's palette color definition that matches the
    /// specified definition, then processes it and sets the specified
    /// definition's hex color to the result.
    private static void lookupPaletteColor(ColorDefinition definition, Variation variation) throws ColorException {
        ColorDefinition paletteColor = variation.getPaletteColor(definition.getPaletteColorId());
        if (paletteColor != null) {
            processColor(paletteColor, variation);
            definition.setHexColor(paletteColor.getHexColor());
        }
    }

    /// Gets the theme variation's base color definition that matches the
    /// specified definition, then processes it and sets the specified
    /// definition's hex color to the result.
    private static void lookupBaseColor(ColorDefinition definition, Variation variation) throws ColorException {
        ColorDefinition baseColor = variation.getBaseColor(definition.getBaseColorId());
        if (baseColor != null) {
            processColor(baseColor, variation);
            definition.setHexColor(baseColor.getHexColor());
        }
    }

    private static void applyLerp(ColorDefinition definition) throws ColorException {
        double d = Double.parseDouble(definition.getLerp());
        Color left = toColor(definition.getLeftHexColor());
        Color right = toColor(definition.getRightHexColor());
        Color interpolated = lerp(left, right, d);
        definition.setHexColor(toRGBHexCode(interpolated));
    }

    private static Color brightnessFunc(Color c, double value) {
        return c.brighter().deriveColor(0, 1.0, 1.0 + value, 1.0);
    }

    private static Color saturationFunc(Color c, double value) {
        return c.saturate().deriveColor(0, 1.0 + value, 1.0, 1.0);
    }
}
