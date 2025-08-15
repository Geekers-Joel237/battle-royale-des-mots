package com.brm.domain.core;

import com.brm.domain.core.ports.DictionaryPort;
import com.brm.domain.core.vo.Word;

import java.util.Set;

public class InMemoryFakeDictionary implements DictionaryPort {
    public final Set<String> words;

    public InMemoryFakeDictionary(Set<String> words) {
        this.words = words;
    }

    @Override
    public boolean exists(Word word) {
        return this.words.contains(word.value().toLowerCase());
    }
}
