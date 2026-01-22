package com.my.answers.web.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.webmvc.autoconfigure.error.ErrorViewResolver;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.Map;

@Component
public class ErrorView implements ErrorViewResolver {

    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public ErrorView(MessageSource messageSource, LocaleResolver localeResolver) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
                                         Map<String, Object> map) {

        Locale locale = localeResolver.resolveLocale(request);

        ModelAndView model = new ModelAndView("error");
        model.addObject("status", status.value());

        String path = map.getOrDefault("path", "").toString();

        switch (status.value()) {
            case 404 -> {
                String errorTitle = messageSource.getMessage(
                        "error.404.title", null, locale);
                String errorMessage = messageSource.getMessage(
                        "error.404.message", new Object[]{path}, locale);

                model.addObject("error", errorTitle);
                model.addObject("message", errorMessage);
            }
            case 500 -> {
                String errorTitle = messageSource.getMessage(
                        "error.500.title", null, locale);
                String errorMessage = messageSource.getMessage(
                        "error.500.message", null, locale);

                model.addObject("error", errorTitle);
                model.addObject("message", errorMessage);
            }
            default -> {
                String errorTitle = messageSource.getMessage(
                        "error.default.title", null, locale);
                String errorMessage = messageSource.getMessage(
                        "error.default.message", null, locale);

                model.addObject("error", errorTitle);
                model.addObject("message", errorMessage);
            }
        }
        return model;
    }
}