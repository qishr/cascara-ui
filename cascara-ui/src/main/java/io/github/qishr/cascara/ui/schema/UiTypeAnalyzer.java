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


package io.github.qishr.cascara.ui.schema;

import java.lang.reflect.Field;

import io.github.qishr.cascara.common.lang.plain.PlainMapNode;
import io.github.qishr.cascara.common.lang.plain.PlainScalarNode;
import io.github.qishr.cascara.common.lang.plain.PlainSequenceNode;
import io.github.qishr.cascara.schema.util.TypeAnalyzer;

public class UiTypeAnalyzer implements TypeAnalyzer {
    private static final String ABSOLUTE = "absolute";
    private static final String EXTENSIONS = "extensions";
    private static final String FORMAT = "format";
    // private static final String NAME = "name";


    @Override
    public void analyze(Field field, PlainMapNode node) {

        if (field.isAnnotationPresent(FileConstraint.class)) {
            FileConstraint anno = field.getAnnotation(FileConstraint.class);

            // TODO: java.nio.Path? Where is this used?
            // Do we differentiate between paths and URIs here?
            node.put(FORMAT, scalar("path"));

            node.put(ABSOLUTE, scalar(anno.absolute()));

            // cascara://organizer/CASC-00028C57
            // TODO: initialDirectory, mustExist

            if (anno.extensions().length > 0) {
                PlainSequenceNode extNode = new PlainSequenceNode();
                for (String ext : anno.extensions()) extNode.add(scalar(ext));
                node.put(EXTENSIONS, extNode);
            }
        }

        if (field.isAnnotationPresent(OptionConstraint.class)) {
            OptionConstraint anno = field.getAnnotation(OptionConstraint.class);
            PlainMapNode optionMeta = new PlainMapNode();
            optionMeta.put(OptionConstraint.NAME, scalar(anno.provider()));
            optionMeta.put(OptionConstraint.PARAMETER, scalar(anno.parameter()));
            node.put(OptionConstraint.UI_OPTION_PROVIDER, optionMeta);
        }

        if (field.isAnnotationPresent(DisplayToggle.class)) {
            DisplayToggle anno = field.getAnnotation(DisplayToggle.class);
            node.put(DisplayToggle.UI_DISPLAY_TOGGLE, scalar(anno.value()));

        }

        if (field.isAnnotationPresent(Hidden.class)) {
            node.put(Hidden.UI_HIDDEN, scalar(true));
        }
    }

    @Override
    public void analyze(Class<?> clazz, PlainMapNode node) {
    }

    private PlainScalarNode scalar(Object value) {
        return new PlainScalarNode(value);
    }
}