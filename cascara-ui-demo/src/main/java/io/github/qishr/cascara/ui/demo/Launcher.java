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


package io.github.qishr.cascara.ui.demo;

import java.io.InputStream;

import io.github.qishr.cascara.common.io.filewatcher.FileWatcher;
import io.github.qishr.cascara.ui.control.OptionChooser;
import io.github.qishr.cascara.ui.language.Localization;
import io.github.qishr.cascara.ui.theme.ThemeEngine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Launcher extends Application {
    private Scene scene;

    public static void main(String[] args) {
        // GlobalReporter.globalInstance().setPrintStackTrace(true);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        registerTranslations();

        Label themeLabel = new Label();
        Localization.bind(themeLabel, "label.theme");

        Label languageLabel = new Label();
        Localization.bind(languageLabel, "label.language");

        OptionChooser themeChooser = new OptionChooser(
            ThemeEngine.getThemeOptionProvider()
        );

        themeChooser.getSelectionModel().selectedItemProperty().addListener((obs, old, theme) -> {
            ThemeEngine.setTheme(theme);
        });

        OptionChooser languageChooser = new OptionChooser(
            Localization.getLanguageOptionProvider()
        );

        languageChooser.getSelectionModel().selectedItemProperty().addListener((obs, old, language) -> {
            Localization.setActiveLanguage(language);
        });

        HBox choserBox = new HBox(
            8,
            themeLabel,
            themeChooser,
            new Rectangle(24, 0),
            languageLabel,
            languageChooser
        );
        choserBox.setAlignment(Pos.CENTER);
        Localization.bindDirection(choserBox);

        VBox layout = new VBox();
        layout.setSpacing(8);
        layout.setPadding(new Insets(16));
        layout.getChildren().add(choserBox);

        layout.getChildren().add(new Samples().getView());

        scene = new Scene(layout, 800, 500);

        ThemeEngine.bind(scene);
        Localization.bindDirection(scene);

        primaryStage.setScene(scene);
        primaryStage.show();

        String version = ThemeEngine.class.getModule().getDescriptor() == null
                ? ""
                : ThemeEngine.class.getModule().getDescriptor().toNameAndVersion();
        Localization.bind(
            primaryStage, "app.window-title",
            version
        );
    }

    @Override
    public void stop() throws Exception {
        // When the OptionProvider is used, the theme engine uses a
        // background thread to watch the theme directory for updates.
        // We close it here to allow the app to close cleanly.
        FileWatcher.clearAll();
    }

    private void registerTranslations() {
        registerLanguage("ar-AE");
        registerLanguage("en-US");
        registerLanguage("es-ES");
        registerLanguage("fr-FR");
    }

    private void registerLanguage(String languageTag) {
        InputStream translations = getClass().getResourceAsStream(languageTag + ".yaml");
        if (translations != null) {
            if (!Localization.registerTranslations(translations)) {
                System.exit(0);
            }
        }
    }
}

