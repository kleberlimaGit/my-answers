package com.my.answers.entity;

public enum Language {
    PT_BR("pr-BR"),
    EN_US("en-US");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public static Language getInstance(String code){
        return Language.valueOf(code);
    }
}
