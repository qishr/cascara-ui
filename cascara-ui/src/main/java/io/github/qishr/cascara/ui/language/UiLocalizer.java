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


package io.github.qishr.cascara.ui.language;

import java.io.InputStream;
import java.text.Bidi;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.github.qishr.cascara.common.diagnostic.DiagnosticLocalizer;
import io.github.qishr.cascara.common.diagnostic.GlobalReporter;
import io.github.qishr.cascara.common.diagnostic.Reporter;
import io.github.qishr.cascara.common.diagnostic.code.DiagnosticCode;
import io.github.qishr.cascara.common.diagnostic.code.GenericDiagnosticCode;
import io.github.qishr.cascara.lang.yaml.processor.YamlSerializer;
import io.github.qishr.cascara.ui.language.detect.HybridLocaleDetector;
import io.github.qishr.cascara.ui.option.Option;
import io.github.qishr.cascara.ui.option.OptionProvider;
import io.github.qishr.cascara.ui.option.OptionProviderRegistry;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;

public class UiLocalizer implements ObservableLocalizer {
    private static final Reporter REPORTER = GlobalReporter.forClass(UiLocalizer.class);

    private static final String AUTOMATIC = "System";


    // TODO: Called should be able to set these...
    private static final String fallbackLanguageTag = "en-US";
    private static final Locale fallbackLocale = Locale.forLanguageTag(fallbackLanguageTag);


    private static final LanguageOption systemLanguageOption = new LanguageOption(AUTOMATIC);

    private final LanguageOptionProvider languageOptionProvider;

    private final ObservableList<Option> languageOptions = FXCollections.observableArrayList();

    private final Map<String,LanguageOption> languageOptionsMap = new HashMap<>();

    private final ReadOnlyObjectWrapper<LanguageOption> activeLanguageOption = new ReadOnlyObjectWrapper<>();


    private final ReadOnlyObjectWrapper<Locale> activeLocale = new ReadOnlyObjectWrapper<>();

    private final Map<String, Map<String, Object>> translationsByLanguage = new HashMap<>();

    private String activeLanguageTag;

    private boolean usingAutoLocale;



    public UiLocalizer() {
        languageOptionProvider = new LanguageOptionProvider();
        OptionProviderRegistry.register(languageOptionProvider);
        languageOptions.add(systemLanguageOption);
        setActiveLanguage(AUTOMATIC);
        Platform.runLater(() -> {
            Localization.bind(systemLanguageOption.optionTextProperty(), "title.system");
        });
    }

    public OptionProvider getLanguageOptionProvider() {
        return languageOptionProvider;
    }

    /// {@inheritDoc}
    @Override
    public ObservableList<Option> getLanguageOptions() {
        return languageOptions;
    }

    public boolean hasLanguage(String languageTag) {
        return translationsByLanguage.containsKey(languageTag);
    }

    // @Override
    // public boolean hasKey(String key) {
    //     return null == get(key);
    // }

    public ReadOnlyObjectWrapper<LanguageOption> activeLanguageOptionProperty() {
        return activeLanguageOption;
    }

    public NodeOrientation getDirection() {
        return isRTL() ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT;
    }

    public boolean isRTL() {
        Locale locale = activeLocale.get();
        String displayLanguage = locale.getDisplayLanguage(locale);
        Bidi bidi = new Bidi(displayLanguage, Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        return !bidi.isLeftToRight();
    }

    public LanguageOption getActiveLanguageOption() {
        return activeLanguageOption.get();
    }

    public LanguageOption getAutoLanguageOption() {
        return systemLanguageOption;
    }

    public void setActiveLanguage(Option option) {
        if ( option instanceof LanguageOption languageOption) {
            setActiveLocale(languageOption.getLocale());
        }
    }

    public void setActiveLanguage(String languageTag) {
        boolean automatic = shouldUseAutoLocale(languageTag);
        if (automatic) {
            if (!usingAutoLocale) {
                Locale locale = getSystemLocale();
                String systemLanguageTag = locale.toLanguageTag();
                if (translationsByLanguage.containsKey(systemLanguageTag)) {
                    activeLanguageTag = systemLanguageTag;
                    activeLocale.setValue(locale);
                } else {
                    activeLanguageTag = fallbackLanguageTag;
                    activeLocale.setValue(fallbackLocale);
                }
                activeLanguageOption.set(systemLanguageOption);
            }
        } else {
            if (!languageTag.equals(activeLanguageTag)) {
                Locale locale = Locale.forLanguageTag(languageTag);

                // activeLanguageTag must be set before the activeLocale
                activeLanguageTag = languageTag;
                activeLocale.setValue(locale);
                activeLanguageOption.set(languageOptionsMap.get(languageTag));
            }
        }
        usingAutoLocale = automatic;
    }

    /// {@inheritDoc}
    @Override
    public ReadOnlyObjectProperty<Locale> activeLocaleProperty() {
        return activeLocale.getReadOnlyProperty();
    }

    public Locale getActiveLocale() {
        return activeLocale.get();
    }

    public void setActiveLocale(Locale locale) {
        String languageTag = locale == null ? null : locale.toLanguageTag();
        setActiveLanguage(languageTag);
    }

    public Locale getSystemLocale() {
        return HybridLocaleDetector.detectOSLocale();
    }

    /// {@inheritDoc}
    @Override
    public boolean registerTranslations(InputStream yamlStream) {
        try {
            YamlSerializer serializer = new YamlSerializer();
            // serializer.setReporter(new StandardReporter().setLevel(Level.TRACE).setDisableFlush(false));

            Translation translation = serializer.fromStream(yamlStream, Translation.class);
            String languageTag = translation.getLanguageTag();
            if (languageTag == null) {
                REPORTER.error(GenericDiagnosticCode.ERROR, "No `lang` key specified in translations file");
                return false;
            }
            Map<String, Object> translations = translationsByLanguage.get(languageTag);
            if (translations == null) {
                translations = addLanguage(translation);
            }
            mergeMaps(translations, translation.getTranslations());

            if (usingAutoLocale) {
                setActiveLocale(HybridLocaleDetector.detectOSLocale());
            }

            return true;

        } catch (Exception e) {
            REPORTER.error(e, GenericDiagnosticCode.ERROR, "Failed to load translations: " + e.getMessage());
            return false;
        }
    }

    /// {@inheritDoc}
    @Override
    public String formatWithDefault(String defaultText, String key, Object... args) {
        String pattern = getPattern(key);
        if (pattern == null) {
            return defaultText == null ? key : defaultText;
        }
        try {
            return MessageFormat.format(pattern, args);
        } catch (IllegalArgumentException e) {
            REPORTER.error(e, GenericDiagnosticCode.MESSAGE_FORMATTING_ERROR, key, pattern);
            return "!!" + defaultText == null ? key : defaultText;
        }
    }

    /// {@inheritDoc}
    @Override
    public String format(String key, Object... args) {
        String pattern = getPattern(key);
        if (pattern == null) {
            return key;
        }
        try {
            return MessageFormat.format(pattern, args);
        } catch (IllegalArgumentException e) {
            REPORTER.error(e, GenericDiagnosticCode.MESSAGE_FORMATTING_ERROR, key, pattern);
            return "!!" + key;
        }
    }

    /// {@inheritDoc}
    @Override
    public String format(DiagnosticCode diagnosticCode, Object... args) {
        if (diagnosticCode.getCode() == null || diagnosticCode.getCode().isEmpty()) {
            return String.format(DiagnosticLocalizer.FORMATTING_ERROR, diagnosticCode.getCode(), diagnosticCode.getMessage());
        }
        String pattern = getPattern("diagnostic/" + diagnosticCode.getCode());
        if (pattern == null) {
            try {
                return MessageFormat.format(diagnosticCode.getMessage(), args);
            } catch (IllegalArgumentException e) {
                REPORTER.error(e, GenericDiagnosticCode.DIAGNOSTIC_FORMATTING_ERROR, diagnosticCode.getCode(), diagnosticCode.getMessage());
                return "!!" + diagnosticCode.getCode();
                // return String.format(DiagnosticLocalizer.FORMATTING_ERROR, diagnosticCode.getCode(), diagnosticCode.getMessage());
            }
        }
        try {
            return MessageFormat.format(pattern, args);
        } catch (IllegalArgumentException e) {
            REPORTER.error(e, GenericDiagnosticCode.DIAGNOSTIC_FORMATTING_ERROR, diagnosticCode.getCode(), pattern);
            return "!!" + diagnosticCode.getCode();
            // return String.format(DiagnosticLocalizer.FORMATTING_ERROR, diagnosticCode.getCode(), pattern);
        }
    }

    //
    // Retrieval
    //

    private boolean shouldUseAutoLocale(String languageTag) {
        // If languageTag is null or "System", detect the system one
        if (languageTag == null || languageTag.toLowerCase().equals(AUTOMATIC)) {
            return true;
        } else {
            // Otherwise, check if the language is registered
            return !translationsByLanguage.containsKey(languageTag);
        }
    }

    private String get(String key) {
        Object value = resolveKey(key, translationsByLanguage.get(activeLanguageTag));
        if (value == null) {
            value = resolveKey(key, translationsByLanguage.get(fallbackLanguageTag));
        }
        return value == null ? null : value.toString();
    }

    private String getPattern(String key) {
        String pattern = get(key);
        if (pattern == null) {
            return null;
        }
        return pattern.replace("'", "''");
    }

    private Object resolveKey(String key, Map<String,Object> languageTree) {
        if (key.startsWith("#/")) {
            key = key.substring(2);
        } else if (key.startsWith("/")) {
            key = key.substring(1);
        }

        String[] parts;
        if (key.contains(".")) {
            parts = key.split("\\.");
        } else {
            parts = key.split("/");
        }

        Object current = languageTree;
        for (String part : parts) {
            if (current instanceof Map<?, ?> map) {
                current = map.get(part);
            } else {
                return null;
            }
        }
        return current;
    }

    /// Merges a new set of translations into the existing pool.
    /// If a key already exists, the new value overwrites the old one.
    /// This allows modules to "patch" or add to the global dictionary.
    @SuppressWarnings("unchecked")
    private void mergeMaps(Map<String, Object> target, Map<String, Object> source) {
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey();
            Object sourceValue = entry.getValue();

            if (sourceValue instanceof Map && target.get(key) instanceof Map) {
                // Both are maps, so we need to dive deeper and merge them
                mergeMaps((Map<String, Object>) target.get(key), (Map<String, Object>) sourceValue);
            } else {
                // It's a leaf node (String/List) or the target doesn't have this branch yet.
                // Just put the source value in.
                target.put(key, sourceValue);
            }
        }
    }

    //
    // Registration
    //

    /// @param localeTag IETF BCP 47 language tag string
    private Map<String, Object> addLanguage(Translation translation) {
        String languageTag = translation.getLanguageTag();
        String title = translation.getTitle();
        // In Locale:
        //
        // Language is an ISO 639 alpha-2 or alpha-3 code or registered language subtag.
        // Region is an ISO 3166 alpha-2 country code or UN numeric-3 area code.
        // Variant is a case-sensitive value or set of values specifying a variation of a Locale.
        // Script must be a valid ISO 15924 alpha-4 code.
        // Extensions is a map which consists of single character keys and String values.
        //
        // IANA Language Subtag Registry: Language, Region, Variant, Script
        // https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
        Locale locale = Locale.forLanguageTag(languageTag);
        LanguageOption option = new LanguageOption(locale, title);
        Map<String, Object> translations = new HashMap<>();
        languageOptions.add(option);
        languageOptionsMap.put(languageTag, option);
        translationsByLanguage.put(languageTag, translations);
        return translations;
    }



}
