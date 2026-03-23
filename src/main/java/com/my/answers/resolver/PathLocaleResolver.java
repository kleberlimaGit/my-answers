package com.my.answers.resolver;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

public class PathLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        // Se for erro, pega a URI original que falhou
        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        // Fallback para requests normais
        if (uri == null || uri.isBlank()) {
            uri = request.getRequestURI();
        }

        // Remove o context-path (/myanswers)
        String ctx = request.getContextPath();
        if (ctx != null && !ctx.isBlank() && uri.startsWith(ctx)) {
            uri = uri.substring(ctx.length());
        }

        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }

        String[] parts = uri.split("/");
        String langSegment = (parts.length > 0) ? parts[0] : "";

        return switch (langSegment) {
            case "pt" -> Locale.forLanguageTag("pt-BR");
            case "en" -> Locale.forLanguageTag("en-US");
            default -> Locale.forLanguageTag("en-US");
        };
    }

    @Override
    public void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale) {

    }
}
