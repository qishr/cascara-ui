package io.github.qishr.cascara.ui.option;

import java.util.List;
import java.util.Map;

import io.github.qishr.cascara.common.service.ServiceProvider;
import io.github.qishr.cascara.ui.api.render.ScalarRenderer;
import javafx.beans.property.Property;

public interface OptionProvider extends ServiceProvider {
    void initialize();
    String getName();
    String getContentType();
    String getSchemaType();
    String getSchemaFormat();
    ScalarRenderer getRenderer();
    Option getActiveOption();
    List<Option> getOptions(Map<String,Property<?>> contextData, String parameter);
    void addListener(Runnable listener);
    void removeListener(Runnable listener);
}