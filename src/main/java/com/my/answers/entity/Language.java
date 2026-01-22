package com.my.answers.entity;

public enum Language {
    PT_BR("pt"),
    EN_US("en");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public static Language getInstance(String code){
        return Language.valueOf(code);
    }
}
