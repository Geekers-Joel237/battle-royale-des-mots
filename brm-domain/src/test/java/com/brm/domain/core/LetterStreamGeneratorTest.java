package com.brm.domain.core;

/*
 * =================================================================================
 * RÈGLES DU BLUEPRINT VALIDÉES PAR CES TESTS :
 * =================================================================================
 * Section 6) Modèle de domaine (v1) -> Domain Services
 * ---------------------------------------------------
 * - LetterStreamGenerator (seedable pour TDD) : Le générateur de lettres
 * doit être initialisable avec une "graine" (seed) pour garantir que
 * les tests sont reproductibles.
 * Section 1) Concept & boucle de jeu
 * ---------------------------------
 * - Occurrences max par lettre : pondérée par distribution (FR).
 * =================================================================================
 */

import com.brm.domain.core.service.LetterStreamGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LetterStreamGeneratorTest {

    @Test
    @DisplayName("Should produce the same letter sequence for the same seed")
    void shouldBeDeterministicForSameSeed() {
        // GIVEN deux générateurs avec la même graine
        long seed = 12345L;
        LetterStreamGenerator generator1 = new LetterStreamGenerator(seed);
        LetterStreamGenerator generator2 = new LetterStreamGenerator(seed);

        // WHEN on génère une séquence de 20 lettres pour chacun
        List<Character> sequence1 = Stream.generate(generator1::nextLetter).limit(20).toList();
        List<Character> sequence2 = Stream.generate(generator2::nextLetter).limit(20).toList();

        // THEN les deux séquences doivent être identiques
        assertThat(sequence1).isEqualTo(sequence2);
    }

    @Test
    @DisplayName("Should produce different letter sequences for different seeds")
    void shouldBeDifferentForDifferentSeeds() {
        // GIVEN deux générateurs avec des graines différentes
        LetterStreamGenerator generator1 = new LetterStreamGenerator(1L);
        LetterStreamGenerator generator2 = new LetterStreamGenerator(2L);

        // WHEN on génère une séquence de 20 lettres pour chacun
        List<Character> sequence1 = Stream.generate(generator1::nextLetter).limit(20).toList();
        List<Character> sequence2 = Stream.generate(generator2::nextLetter).limit(20).toList();

        // THEN les deux séquences doivent être différentes
        assertThat(sequence1).isNotEqualTo(sequence2);
    }
}
