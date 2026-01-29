package com.my.answers.service;

import com.my.answers.entity.Language;
import com.my.answers.utils.Utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import static com.my.answers.utils.Utils.toLocale;

@Component
@RequiredArgsConstructor
public class MessageProvider {

    private final MessageSource messageSource;

    public String get(String key, Language language) {
        return messageSource.getMessage(key, null, toLocale(language));
    }
}
