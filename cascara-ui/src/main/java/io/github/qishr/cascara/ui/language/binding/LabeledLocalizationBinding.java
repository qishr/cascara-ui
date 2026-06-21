package io.github.qishr.cascara.ui.language.binding;

import javafx.scene.control.Labeled;
import io.github.qishr.cascara.ui.language.ObservableLocalizer;

public class LabeledLocalizationBinding extends LocalizationBinding {
    @Override
    protected LabeledLocalizationBinding self() { return this; }

    public LabeledLocalizationBinding(ObservableLocalizer localizer, Labeled target, String key, Object... args) {
        super(localizer, key, args);
        listener = o -> {
            target.setText(localizer.formatWithDefault(defaultText, key, args));
        };
        startListening();
    }
}
