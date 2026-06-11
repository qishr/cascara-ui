package io.github.qishr.cascara.ui.l10n;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.function.Consumer;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.util.StringConverter;

public class Localization {
    private static volatile Localizer localizer = new DummyLocalizer();

    public static void setLocalizer(Localizer customLocalizer) {
        localizer = customLocalizer != null ? customLocalizer : new DummyLocalizer();
    }

    public static ObjectProperty<Locale> localeProperty() {
        return localizer.activeLocaleProperty();
    }

    public static Locale getLocale() {
        return localeProperty().get();
    }

    public static void bind(Labeled node, String key, Object... args) {
        InvalidationListener listener = o -> node.setText(localizer.format(key, args));
        listener.invalidated(null);
        if (localizer.activeLocaleProperty() != null) {
            localizer.activeLocaleProperty().addListener(listener);
        }
    }

    @SuppressWarnings("unchecked")
    public static void bind(Property<?> prop, String key, Object... args) {
        InvalidationListener listener = null;

        if (prop instanceof ObjectProperty objProp) {
            listener = o -> objProp.set(localizer.format(key, args));
        } else if (prop instanceof StringProperty stringProp) {
            listener = o -> stringProp.setValue(localizer.format(key, args));
        }

        if (listener != null) {
            listener.invalidated(null); // Run immediate initial evaluation pass
            if (localizer.activeLocaleProperty() != null) {
                localizer.activeLocaleProperty().addListener(listener);
            }
        }
    }

    public static void bindLocale(DatePicker picker) {
        // 1. Define the localized configuration engine
        Consumer<Locale> configurePicker = (locale) -> {
            if (locale == null) return;

            DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(locale);

            picker.setConverter(new StringConverter<>() {
                @Override public String toString(LocalDate d) { return d != null ? formatter.format(d) : ""; }
                @Override public LocalDate fromString(String s) { return (s != null && !s.isBlank()) ? LocalDate.parse(s, formatter) : null; }
            });
        };

        // 2. Bootstrap immediately with the current state
        configurePicker.accept(getLocale());

        // 3. Listen for dynamic hot-swaps down the line
        if (localeProperty() != null) {
            localeProperty().addListener((obs, old, newLocale) -> configurePicker.accept(newLocale));
        }
    }

    private static class DummyLocalizer implements Localizer {
        private ObjectProperty<Locale> locale = new SimpleObjectProperty<>();
        public DummyLocalizer() { locale.set(Locale.getDefault()); }
        public String format(String code, Object... details) { return code; }
        public ObjectProperty<Locale> activeLocaleProperty() { return locale; }
    }
}
