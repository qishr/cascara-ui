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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.qishr.cascara.common.diagnostic.LocalizableRuntimeException;
import io.github.qishr.cascara.common.io.filewatcher.FileWatcher;
import io.github.qishr.cascara.ui.api.UiDiagnosticCode;
import io.github.qishr.cascara.ui.option.AbstractOptionProvider;
import io.github.qishr.cascara.ui.option.Option;
import io.github.qishr.cascara.ui.option.SimpleStringOption;
import io.github.qishr.cascara.ui.option.StringOption;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class ThemeOptionProvider extends AbstractOptionProvider implements AutoCloseable {
    public static final String NAME = "ui-theme";

    private final Path cascaraDir = Paths.get(System.getProperty("user.home")).resolve(".cascara");
    private final Path themesDir = cascaraDir.resolve("themes");
    private final Path packagesDir = cascaraDir.resolve("packages");
    private FileWatcher themesWatcher;

    private final ObservableSet<StringOption> cascThemeSet = FXCollections.observableSet();
    private SetProperty<StringOption> cascThemes = new SimpleSetProperty<>(cascThemeSet);
    private final StringOption defaultTheme = new SimpleStringOption("default", "Default", "title.default");

    private final ObservableSet<StringOption> vsixThemeSet = FXCollections.observableSet();
    private final SetProperty<StringOption> vsixThemes = new SimpleSetProperty<>(vsixThemeSet);

    private ObjectProperty<Option> activeOption;

    public ThemeOptionProvider() {
        super(NAME, null, null, null);
    }

    @Override
    public void initialize() {
        try {
            this.activeOption = ThemeEngine.activeThemeOptionProperty();
            if (!Files.isDirectory(themesDir)) {
                Files.createDirectories(themesDir);
            }
            themesWatcher = new FileWatcher();
            themesWatcher.watchDirectory(themesDir, () -> {
                enumerateThemes();
            });
            themesWatcher.watchDirectory(packagesDir, () -> {
                enumeratePackages();
            });
            cascThemes.addListener((obs, oldSet, newSet) -> {
                listeners.forEach(Runnable::run);
            });
            enumerateThemes();
            enumeratePackages();
        } catch (Exception e) {
            throw new LocalizableRuntimeException(e, UiDiagnosticCode.OPTION_PROVIDER_INIT_ERROR, getName(), e.getLocalizedMessage());
        }
    }

    @Override
    public void close() {
        themesWatcher.clear();
    }

    @Override
    public Option getActiveOption(Map<String,Property<?>> contextData, String parameter) {
        return activeOption.get();
    }

    @Override
    public List<Option> getOptions(Map<String,Property<?>> contextData, String parameter) {
        List<Option> result = new ArrayList<>();
        result.addAll(cascThemes.get());
        result.addAll(vsixThemes.get());
        return result;
    }

    private void enumerateThemes() {
        cascThemes.clear();
        cascThemes.add(defaultTheme);
        File[] files = themesDir.toFile().listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.getName().endsWith(".casc")) {
                    String themeId = f.getName();
                    CascaraTheme theme = ThemeEngine.getTheme(themeId);
                    String themeName = theme.getName();
                    SimpleStringOption option = new SimpleStringOption(themeId, themeName);
                    cascThemes.add(option);
                }
            }
        }
    }

    private void enumeratePackages() {
        vsixThemes.clear();
        File[] files = packagesDir.toFile().listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.getName().endsWith(".vsix")) {
                    String themeId = f.getName();
                    CascaraTheme theme = ThemeEngine.getTheme(themeId);
                    String themeName = theme.getName();
                    SimpleStringOption option = new SimpleStringOption(themeId, themeName);
                    vsixThemes.add(option);
                }
            }
        }
    }
}
