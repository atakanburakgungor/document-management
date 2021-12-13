package com.example.documentmanagement.config;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;

@Configuration
public class LocalizationConfiguration extends AcceptHeaderLocaleContextResolver implements WebMvcConfigurer {

    private static final String LANGUAGE_HEADER_NAME = "lang";
    private static final String DEFAULT_LANGUAGE_NAME = "tr";
    private static final List<String> SUPPORTED_LANGUAGE = Arrays.asList("tr", "en");

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/document");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Override
    public LocaleContext resolveLocaleContext(@NonNull ServerWebExchange exchange) {
        List<String> languages = Optional.ofNullable(exchange.getRequest().getHeaders().get(LANGUAGE_HEADER_NAME))
                .orElse(Collections.singletonList(DEFAULT_LANGUAGE_NAME));

        String language = languages.stream()
                .filter(SUPPORTED_LANGUAGE::contains)
                .findFirst()
                .orElse(DEFAULT_LANGUAGE_NAME);

        return new SimpleLocaleContext(new Locale(language));
    }

    @Override
    public void setLocaleContext(ServerWebExchange exchange, @NonNull LocaleContext localeContext) {
        LocaleContextHolder.setLocale(localeContext.getLocale());
    }
}
