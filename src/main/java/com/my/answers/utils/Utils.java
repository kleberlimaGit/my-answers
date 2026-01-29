package com.my.answers.utils;

import com.my.answers.entity.Language;

import java.util.Locale;

public class Utils {
    public static Locale toLocale(Language language) {
        return switch (language) {
            case PT_BR -> new Locale("pt", "BR");
            case EN_US -> Locale.US;
        };
    }
}
