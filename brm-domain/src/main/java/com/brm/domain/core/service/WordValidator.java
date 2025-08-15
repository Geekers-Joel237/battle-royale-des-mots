package com.brm.domain.core.service;

import com.brm.domain.core.ports.DictionaryPort;
import com.brm.domain.core.vo.Word;

public class WordValidator {
    private final DictionaryPort dictionaryPort;

    public WordValidator(DictionaryPort dictionaryPort) {
        this.dictionaryPort = dictionaryPort;
    }

    public boolean isValid(Word word) {
        return this.dictionaryPort.exists(word);
    }
}
