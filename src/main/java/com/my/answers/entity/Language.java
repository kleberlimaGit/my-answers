package com.my.answers.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Language {
    PT_BR("pt"),
    EN_US("en");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public static Language getInstance(String code){
        return Arrays.stream(Language.values()).filter(n -> n.getCode().equals(code)).findAny()
                .orElse(PT_BR);
    }
}
