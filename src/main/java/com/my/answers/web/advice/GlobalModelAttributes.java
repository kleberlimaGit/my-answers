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
        String ctx = request.getContextPath();

        if (uri.startsWith(ctx)) uri = uri.substring(ctx.length());

        if (uri.startsWith("/")) uri = uri.substring(1);

        String[] parts = uri.split("/", 2);
        if (parts.length == 0) return "";

        return (parts.length > 1) ? parts[1] : "";
    }
}
