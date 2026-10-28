package com.my.answers.web.advice;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("langPath")
    public String pathAfterLang(HttpServletRequest request) {
        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (uri == null) {
            uri = request.getRequestURI();
        }

        String ctx = request.getContextPath();
        if (uri != null && uri.startsWith(ctx)) {
            uri = uri.substring(ctx.length());
        }

        if (uri == null || uri.isBlank()) {
            return "answers";
        }

        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }

        String[] parts = uri.split("/", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            return "answers";
        }

        return parts[1];
    }
}
