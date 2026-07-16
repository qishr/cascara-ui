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

import io.github.qishr.cascara.common.lang.exception.SerializerException;
import io.github.qishr.cascara.lang.yaml.processor.YamlSerializer;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ColorClipboard {
    private static YamlSerializer serializer = new YamlSerializer();

    private ColorClipboard() {
        // No public constructor
    }

    private static YamlSerializer getSerializer() {
        if (serializer == null) {
            serializer = new YamlSerializer();
        }
        return serializer;
    }

    public static void put(ColorDefinition definition) {
        // YamlSerializerImpl serializer = new YamlSerializerImpl();
        String yaml;
        try {
            yaml = getSerializer().toText(definition);
        } catch (SerializerException e) {
            System.err.println("SerializerException: " + e.getMessage());
            return;
        }
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.put(ColorDefinition.DATA_FORMAT, yaml);
        content.putString(definition.getHexColor().toUpperCase());
        clipboard.setContent(content);
    }

    public static ColorDefinition get() {
        // YamlSerializerImpl serializer = new YamlSerializerImpl();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (clipboard.hasContent(ColorDefinition.DATA_FORMAT)) {
            String yaml = (String) clipboard.getContent(ColorDefinition.DATA_FORMAT);
            try {
                return getSerializer().fromText(yaml, ColorDefinition.class);
            } catch (SerializerException e) {
                System.err.println("SerializerException: " + e.getMessage());
                return null;
            }
        } else if (clipboard.hasString()) {
            String clipText = clipboard.getString().trim().toUpperCase();
            if (clipText.matches("#?[0-9A-F]{6}")) {
                try {
                    String hex = clipText.startsWith("#") ? clipText.substring(1) : clipText;
                    return new ColorDefinition(hex);
                } catch (IllegalArgumentException e) {
                    System.err.println("IllegalArgumentException: " + e.getMessage());
                    return null;
                }
            }
        }
        return null;
    }
}
