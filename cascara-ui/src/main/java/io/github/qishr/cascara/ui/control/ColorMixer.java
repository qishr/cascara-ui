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

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleablePropertyFactory;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;


public class ColorMixer extends Control {
    private static final StyleablePropertyFactory<ColorMixer> FACTORY = new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final ObjectProperty<Color> leftColor = new SimpleObjectProperty<>(Color.WHITE);
    public final ObjectProperty<Color> leftColorProperty() { return leftColor; }
    public final Color getLeftColor() { return leftColor.get(); }
    public final void setLeftColor(Color c) { leftColor.set(c); }

    private final ObjectProperty<Color> mixedColor = new SimpleObjectProperty<>(Color.WHITE);
    public final ObjectProperty<Color> mixedColorProperty() { return mixedColor; }
    public final Color getMixedColor() { return mixedColor.get(); }

    private final ObjectProperty<Color> rightColor = new SimpleObjectProperty<>(Color.WHITE);
    public final ObjectProperty<Color> rightColorProperty() { return rightColor; }
    public final Color getRightColor() { return rightColor.get(); }
    public final void setRightColor(Color c) { rightColor.set(c); }

    private final DoubleProperty lerp = new SimpleDoubleProperty(0.0);
    public final DoubleProperty lerpProperty() { return lerp; }
    public final double getLerp() { return lerp.get(); }
    public final void setLerp(double c) { lerp.set(c); }


    public ColorMixer() {
        super();
    }

    // Skin

    @Override
    protected Skin<ColorMixer> createDefaultSkin() {
        return new ColorMixerSkin(ColorMixer.this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return "";
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }
}
