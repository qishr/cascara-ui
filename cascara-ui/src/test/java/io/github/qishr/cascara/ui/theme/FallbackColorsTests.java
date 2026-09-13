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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import io.github.qishr.cascara.common.property.Properties;
import io.github.qishr.cascara.ui.color.ColorException;

public class FallbackColorsTests extends ThemeTestsBase {
    static Properties colors;

    static String hexValue01 = "#EEEEEE";
    static String hexValue02 = "#EEEEEE";

    @BeforeAll
    static void init() {
        colors = new Properties();
        colors.set(ColorID.ACTIVITYBAR_ACTIVE_BACKGROUND, hexValue01);
        colors.set(ColorID.ACTIVITYBAR_ACTIVE_FOREGROUND, hexValue02);
    }

    @Test
    void test_fallback() {

    }

    @ParameterizedTest
    @MethodSource("stringArraysProvider")
    void test_fallbackSucceeds(String input, String expected) throws IOException, ColorException {
        FallbackColors fbc = new FallbackColors();
        String color = fbc.resolve(input, colors);
        assertEquals(expected, color);
    }

    static String[][] stringArraysProvider() {
        return new String[][]{
            {ColorID.ACTIVITYBAR_INACTIVE_BACKGROUND, hexValue01},
            {ColorID.ACTIVITYBAR_INACTIVE_FOREGROUND, "#777777"}
        };
    }
}
