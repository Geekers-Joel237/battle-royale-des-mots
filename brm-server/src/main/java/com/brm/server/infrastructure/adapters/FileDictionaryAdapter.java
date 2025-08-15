package com.brm.server.infrastructure.adapters;

import com.brm.domain.core.ports.DictionaryPort;
import com.brm.domain.core.vo.Word;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileDictionaryAdapter implements DictionaryPort {
    private Set<Word> words = new HashSet<>();

    @Override
    public boolean exists(Word word) {

        return this.words.contains(word);
    }

    @PostConstruct
    public void initialize() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("dictionary.txt");
            if (is == null) {
                throw new RuntimeException("Cannot find dictionary.txt");
            }

            this.words = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .map(Word::new)
                    .collect(HashSet::new, HashSet::add, HashSet::addAll);

            System.out.println("Dictionary loaded with " + words.size() + " words.");
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load dictionary", e);
        }
    }
}
