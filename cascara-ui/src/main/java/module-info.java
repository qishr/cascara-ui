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


module cascara.ui {
    requires transitive cascara.common;
    requires transitive cascara.common.io;
    requires cascara.lang.json;
    requires cascara.lang.xml;
    requires transitive cascara.lang.yaml;
    requires transitive cascara.schema;

    requires javafx.base;
    requires transitive javafx.controls;
    requires javafx.graphics;

    exports io.github.qishr.cascara.ui.api;
    exports io.github.qishr.cascara.ui.api.data;
    exports io.github.qishr.cascara.ui.option;
    exports io.github.qishr.cascara.ui.api.render;
    exports io.github.qishr.cascara.ui.color;
    exports io.github.qishr.cascara.ui.control;
    exports io.github.qishr.cascara.ui.data;
    exports io.github.qishr.cascara.ui.form;
    exports io.github.qishr.cascara.ui.language;
    exports io.github.qishr.cascara.ui.language.binding;
    exports io.github.qishr.cascara.ui.menu;
    exports io.github.qishr.cascara.ui.render;
    exports io.github.qishr.cascara.ui.render.control;
    exports io.github.qishr.cascara.ui.render.standard;
    exports io.github.qishr.cascara.ui.schema;
    exports io.github.qishr.cascara.ui.theme;
    exports io.github.qishr.cascara.ui.style;
    exports io.github.qishr.cascara.ui.style.custom;
    exports io.github.qishr.cascara.ui.style.standard;
    exports io.github.qishr.cascara.ui.vsix;
    exports io.github.qishr.cascara.ui.widget;
    exports io.github.qishr.cascara.ui.window;

    opens io.github.qishr.cascara.ui.color;
    opens io.github.qishr.cascara.ui.control;
    opens io.github.qishr.cascara.ui.render;
    opens io.github.qishr.cascara.ui.style;
    opens io.github.qishr.cascara.ui.style.custom;
    opens io.github.qishr.cascara.ui.style.part;
    opens io.github.qishr.cascara.ui.style.standard;
    opens io.github.qishr.cascara.ui.theme;
    opens io.github.qishr.cascara.ui.window;

    // For deserializing translation files
    opens io.github.qishr.cascara.ui.language to cascara.common, cascara.schema;

    // For ObservableObject
    opens io.github.qishr.cascara.ui.data to cascara.common, cascara.schema;
    opens io.github.qishr.cascara.ui.menu to cascara.schema;

    // SPI

    uses io.github.qishr.cascara.common.service.ServiceProvider;

    uses io.github.qishr.cascara.ui.api.OptionListEditor;
    uses io.github.qishr.cascara.ui.api.render.ArrayEditorRenderer;
    uses io.github.qishr.cascara.ui.api.render.ScalarEditorRenderer;
    uses io.github.qishr.cascara.ui.api.render.ScalarRenderer;

    uses io.github.qishr.cascara.ui.menu.SystemMenusService;

    uses io.github.qishr.cascara.ui.option.OptionProvider;

    provides io.github.qishr.cascara.ui.api.render.ArrayEditorRenderer
        with io.github.qishr.cascara.ui.render.control.TableRenderer,
             io.github.qishr.cascara.ui.render.control.TagChooserRenderer;

    provides io.github.qishr.cascara.ui.api.render.ScalarEditorRenderer
        with io.github.qishr.cascara.ui.render.standard.StandardBooleanEditorRenderer,
             io.github.qishr.cascara.ui.render.standard.StandardIntegerEditorRenderer,
             io.github.qishr.cascara.ui.render.standard.StandardNumberEditorRenderer,
             io.github.qishr.cascara.ui.render.standard.StandardStringEditorRenderer,
             io.github.qishr.cascara.ui.render.control.ColorChooserRenderer,
             io.github.qishr.cascara.ui.render.control.DateChooserRenderer,
             io.github.qishr.cascara.ui.render.control.FileChooserRenderer,
             io.github.qishr.cascara.ui.render.control.OptionChooserRenderer;

    provides io.github.qishr.cascara.ui.api.render.ScalarRenderer
        with io.github.qishr.cascara.ui.render.standard.StandardBooleanRenderer,
             io.github.qishr.cascara.ui.render.standard.StandardIntegerRenderer,
             io.github.qishr.cascara.ui.render.standard.StandardNumberRenderer,
             io.github.qishr.cascara.ui.render.standard.StandardStringRenderer,
             io.github.qishr.cascara.ui.render.control.DiagnosticRenderer,
             io.github.qishr.cascara.ui.render.control.SingleLineCellRenderer,
             io.github.qishr.cascara.ui.render.control.TagRenderer,
             io.github.qishr.cascara.ui.render.control.TimeRenderer,
             io.github.qishr.cascara.ui.render.control.UriRenderer;

    provides io.github.qishr.cascara.ui.option.OptionProvider
        with io.github.qishr.cascara.ui.language.LanguageOptionProvider,
             io.github.qishr.cascara.ui.theme.ThemeOptionProvider,
             io.github.qishr.cascara.ui.theme.VariationOptionProvider;
}
