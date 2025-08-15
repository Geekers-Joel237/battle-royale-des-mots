package com.brm.domain.core.vo;

import java.text.Normalizer;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Word {
    public static final int MIN_LENGTH = 2;
    private final String value;

    public Word(String rawWord) {
        this.value = this.normalized(rawWord);
    }

    private String normalized(String rawWord) {
        if (rawWord == null || rawWord.isBlank()) {
            throw new IllegalArgumentException("Word cannot be null or blank.");
        }

        if (rawWord.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("Word must be at least 2 characters long.");
        }

        return Normalizer.normalize(rawWord.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    public String value() {
        return value;
    }

    /**
     * Calculates the count of each character in the word.
     *
     * @return An unmodifiable Map with characters as keys and their counts as values.
     */
    public Map<Character, Integer> letterCounts() {
        return this.value.chars().mapToObj(c -> (char) c)
                .collect(Collectors.toUnmodifiableMap(
                        Function.identity(),
                        c -> 1,
                        Integer::sum
                ));
    }
}
