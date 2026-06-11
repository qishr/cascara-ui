package io.github.qishr.cascara.ui.l10n;

import java.util.Locale;

import javafx.beans.property.ObjectProperty;

// @FunctionalInterface
public interface Localizer {

    /// Formats the code with dynamic arguments using the environment's current language bundle.
    String format(String code, Object... details);
    ObjectProperty<Locale> activeLocaleProperty();

}