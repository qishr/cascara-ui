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

import java.util.Map;

import io.github.qishr.cascara.ui.color.ColorDefinition;
import io.github.qishr.cascara.ui.option.Option;

public interface Variation extends Option {
    public String getName();
    public void setName(String name);
    public String getPath();
    public void setPath(String path);
    public Map<String, ColorDefinition> getBaseColors();
    public ColorDefinition getBaseColor(String id);
    public void setBaseColor(String id, String hexColor);
    public Map<String, ColorDefinition> getTransformDefinitions();
    public ColorDefinition getTransformDefinition(String id);
    public void setTransformDefinition(String id, String transform);
    public Map<String, ColorDefinition> getPaletteColors();
    public ColorDefinition getPaletteColor(String id);
    public void setPaletteColor(String id, ColorDefinition definition);
    public void setPaletteColor(String id, String hexColor);
    public Map<String, ColorDefinition> getUiColors();
    public ColorDefinition getUiColor(String id);
    public void setUiColor(String id, String hexColor);
    public Map<String, ColorDefinition> getCodeColors();
    public ColorDefinition getCodeColor(String id);
    public void setCodeColor(String id, String hexColor);
    public Map<String, ColorDefinition> getGroup(String groupId);
}
