package com.brm.domain.core;

/*
Ce test va garantir trois règles métier de notre blueprint :

1. Un mot est normalisé (minuscules, sans accents).

2. Un mot doit respecter une longueur minimale (≥ 2).

3. Un mot ne peut pas être nul ou vide.

4. Il doit pouvoir fournir un décompte précis des lettres qui le composent.

*/

import com.brm.domain.core.vo.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Tests for Word Value Object")
public class WordTest {


    @Test
    @DisplayName("should create a valid word and normalize it")
    void shouldCreateAndNormalizeWord() {
        // GIVEN a raw string with uppercase and accents
        String rawWord = "HÉLICOPTÈRE";

        // WHEN creating a Word object
        Word word = new Word(rawWord);

        // THEN its value should be normalized (lowercase, no accents)
        assertThat(word.value()).isEqualTo("helicoptere");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    @DisplayName("should throw an exception for null or blank input")
    void shouldThrowExceptionForNullOrBlankInput(String invalidInput) {
        // WHEN trying to create a Word with null or blank input
        // THEN an IllegalArgumentException should be thrown
        assertThatThrownBy(() -> new Word(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Word cannot be null or blank.");
    }


    @Test
    @DisplayName("should throw an exception for words shorter than minimum length")
    void shouldThrowExceptionForTooShortWord() {
        // WHEN trying to create a Word with a single letter
        // THEN an IllegalArgumentException should be thrown
        assertThatThrownBy(() -> new Word("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Word must be at least 2 characters long.");
    }

    @Test
    @DisplayName("Should correctly count its letters")
    void shouldCorrectlyCountLetters() {
        //Given a word with repeated letters
        Word word = new Word("BOOkeeper"); // normalized to "bookkeeper"

        //when getting the letter counts
        Map<Character, Integer> counts = word.letterCounts();

        //Then the counts should be accurate
        assertThat(counts).containsExactlyInAnyOrderEntriesOf(Map.of(
                'b', 1,
                'o', 2,
                'k', 1,
                'e', 3,
                'p', 1,
                'r', 1
        ));
    }
}
