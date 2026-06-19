package io.github.qishr.cascara.ui.demo;

import java.io.InputStream;

import io.github.qishr.cascara.ui.control.OptionChooser;
import io.github.qishr.cascara.ui.language.Localization;
import io.github.qishr.cascara.ui.language.UiLocalizer;
import io.github.qishr.cascara.ui.theme.ThemeEngine;
import io.github.qishr.cascara.ui.vsix.VsixPackageStore;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Launcher extends Application {
    private ThemeEngine themeEngine = ThemeEngine.instance();
    private UiLocalizer localizer;
    private Scene scene;

    public static void main(String[] args) {
        // GlobalReporter.globalInstance().setLevel(Level.DEBUG);
        // Reporter splReporter = new StandardReporter().setLevel(Level.DEBUG);
        // ServiceProviderLayer.getRootLayer(splReporter);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        if (Localization.getLocalizer() instanceof UiLocalizer localiser) {
            this.localizer = localiser;
        }

        registerTranslations();

        Label themeLabel = new Label();
        Localization.bind(themeLabel, "label.theme");

        Label languageLabel = new Label();
        Localization.bind(languageLabel, "label.language");

        OptionChooser themeChooser = new OptionChooser(
            themeEngine.getThemeOptionProvider()
            // themeEngine.getDefaultThemeOption()
        );

        themeChooser.getSelectionModel().selectedItemProperty().addListener((obs, old, theme) -> {
            themeEngine.setTheme(theme);
            themeEngine.applyTheme(scene);
        });

        OptionChooser languageChooser = new OptionChooser(
            localizer.getLanguageOptionProvider()
            // localizer.getActiveLanguageOption()
        );

        languageChooser.getSelectionModel().selectedItemProperty().addListener((obs, old, language) -> {
            localizer.setActiveLanguage(language);
        });

        HBox choserBox = new HBox(
            16,
            themeLabel,
            themeChooser,
            new Rectangle(24, 0),
            languageLabel,
            languageChooser
        );
        choserBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.setSpacing(8);
        layout.setPadding(new Insets(16));
        layout.getChildren().add(choserBox);


        layout.getChildren().add(new Samples().getView());
        // layout.getChildren().add(new SamplesSchemaDriven().getView());


        scene = new Scene(layout, 800, 500);

        themeEngine.applyTheme(scene);

        primaryStage.setScene(scene);
        primaryStage.show();

        String title = String.format(
            "Cascara Theming Demo (%s)",
            ThemeEngine.class.getModule().getDescriptor().toNameAndVersion()
        );

        primaryStage.setTitle(title);
    }

    @Override
    public void stop() throws Exception {
        // When the OptionProvider is used, the theme engine uses a
        // background thread to watch the theme directory for updates.
        // We close it here to allow the app to close cleanly.
        themeEngine.close();
    }

    private void registerTranslations() {
        registerLanguage("ar-AE");
        registerLanguage("en-US");
        registerLanguage("es-ES");
        registerLanguage("fr-FR");
    }

    private void registerLanguage(String languageTag) {
        InputStream masterLang = getClass().getResourceAsStream(languageTag + ".yaml");
        if (masterLang != null) {
            localizer.registerTranslations(masterLang);
        }
    }
}

