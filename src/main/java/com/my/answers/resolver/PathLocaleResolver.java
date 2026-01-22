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

        // 1) Se for erro, pega a URI original que falhou
        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        // 2) Fallback para requests normais
        if (uri == null || uri.isBlank()) {
            uri = request.getRequestURI();
        }

        // 3) Remove o context-path (/myanswers)
        String ctx = request.getContextPath(); // "/myanswers"
        if (ctx != null && !ctx.isBlank() && uri.startsWith(ctx)) {
            uri = uri.substring(ctx.length()); // vira "/pt/..." ou "/error"
        }

        // 4) Agora sim pega o primeiro segmento real
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }

        String[] parts = uri.split("/");
        String langSegment = (parts.length > 0) ? parts[0] : "";

        return switch (langSegment) {
            case "pt" -> Locale.forLanguageTag("pt-BR");
            case "en" -> Locale.forLanguageTag("en-US");
            default -> Locale.forLanguageTag("pt-BR");
        };
    }

    @Override
    public void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale) {

    }
}
