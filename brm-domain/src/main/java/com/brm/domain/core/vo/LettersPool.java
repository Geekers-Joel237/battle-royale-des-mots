package com.brm.domain.core.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class LettersPool {
    private final Map<Character, Integer> currentPool;

    public LettersPool(Map<Character, Integer> pool) {
        Map<Character, Integer> normalizedMap = pool.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> Character.toLowerCase(entry.getKey()),
                        Map.Entry::getValue,
                        Integer::sum
                ));
        this.currentPool = Map.copyOf(normalizedMap);
    }

    public boolean canForm(Word word) {
        for (var letter : word.letterCounts().entrySet()) {
            if (this.currentPool.getOrDefault(letter.getKey(), 0) < letter.getValue()) {
                return false;
            }
        }
        return true;
    }

    public LettersPool remove(Word word) {
        if (!this.canForm(word)) {
            return this;
        }

        Map<Character, Integer> newPool = new HashMap<>(this.currentPool);
        for (char letter : word.value().toCharArray()) {
            newPool.computeIfPresent(letter, (key, count) -> count > 1 ? count - 1 : null);
        }
        return new LettersPool(newPool);
    }

    public Map<Character, Integer> getCurrentPool() {
        return this.currentPool;
    }

    public LettersPool addLetter(char letter) {
        Map<Character, Integer> newPool = new HashMap<>(this.currentPool);
        newPool.merge(Character.toLowerCase(letter), 1, Integer::sum);
        return new LettersPool(newPool);

    }


}
