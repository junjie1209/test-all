
package com.test.annotation.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;


/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/8/15
 */

@Configuration
@ControllerAdvice
public class WebBindingInitializerConfiguration {
    @Bean
    public ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        FormattingConversionService conversionService = new DefaultFormattingConversionService();
        initializer.setConversionService(conversionService);
        initializer.setPropertyEditorRegistrar(propertyEditorRegistry -> {
            propertyEditorRegistry.registerCustomEditor(String.class,
                    new StringEscapeEditor(true,true));
        });
        return initializer;
    }
}

