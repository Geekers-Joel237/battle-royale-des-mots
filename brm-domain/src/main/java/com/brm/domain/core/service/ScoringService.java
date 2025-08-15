package com.brm.domain.core.service;

import com.brm.domain.core.vo.Word;

import java.util.Map;

public class ScoringService {

    public static final double DEFAULT_MULTIPLIER = 1.0;
    public static final double GREATER_OR_EQUALS_THAN_8_MULTIPLIER = 2.4;
    public static final double FOUR_TO_FIVE_INTERVAL_MULTIPLIER = 1.3;
    private static final Map<Character, Integer> LETTER_SCORES = Map.ofEntries(
            Map.entry('a', 1), Map.entry('e', 1), Map.entry('i', 1), Map.entry('n', 1), Map.entry('o', 1),
            Map.entry('r', 1), Map.entry('s', 1), Map.entry('t', 1), Map.entry('u', 1), Map.entry('l', 1),
            Map.entry('d', 2), Map.entry('g', 2), Map.entry('m', 2),
            Map.entry('b', 3), Map.entry('c', 3), Map.entry('p', 3),
            Map.entry('f', 4), Map.entry('h', 4), Map.entry('v', 4),
            Map.entry('j', 8), Map.entry('q', 8),
            Map.entry('k', 10), Map.entry('w', 10), Map.entry('x', 10), Map.entry('y', 10), Map.entry('z', 10)
    );
    private static final double SIX_TO_SEVEN_INTERVAL_MULTIPLIER = 1.7;

    public int calculateScore(Word word) {
        var baseScore = word.value().chars().map(c -> {
            var letter = (char) c;
            return LETTER_SCORES.getOrDefault(letter, 0);
        }).sum();
        var multiplier = this.getCorrespondingMultiplier(word.value().length());
        return (int) Math.floor(baseScore * multiplier);
    }

    private double getCorrespondingMultiplier(int length) {
        if (length >= 8) return GREATER_OR_EQUALS_THAN_8_MULTIPLIER;
        if (length >= 6) return SIX_TO_SEVEN_INTERVAL_MULTIPLIER;
        if (length >= 4) return FOUR_TO_FIVE_INTERVAL_MULTIPLIER;
        return DEFAULT_MULTIPLIER;
    }
}
