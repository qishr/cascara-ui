package io.github.qishr.cascara.ui.control;

import io.github.qishr.cascara.schema.structure.SchemaNode;
import io.github.qishr.cascara.ui.option.EnumOptionProvider;
import io.github.qishr.cascara.ui.option.Option;
import io.github.qishr.cascara.ui.option.StringOption;

public class EnumOptionChooser extends OptionChooser {
    private Enum<?> initialValue;

    public EnumOptionChooser(SchemaNode schema, Enum<?> initialValue) {
        super(new EnumOptionProvider(schema, initialValue));
        this.initialValue = initialValue;
    }

    public EnumOptionChooser(SchemaNode schema, String initialStringValue) {
        super(new EnumOptionProvider(schema, initialStringValue));
    }

    public void setEnumValue(Enum<?> enumValue) {
        if (getOptionProvider() instanceof EnumOptionProvider enumProvider) {
            Option option = enumProvider.getOption(enumValue);
            setValue(option);
        }
    }

    public Enum<?> getEnumValue() {
        if (initialValue != null) {
            if (getValue() instanceof StringOption option) {
                @SuppressWarnings("unchecked")
				Enum<?> e = Enum.valueOf(initialValue.getClass(), option.getOptionId());
                return e;
            }
        }
        return null;
    }
}
