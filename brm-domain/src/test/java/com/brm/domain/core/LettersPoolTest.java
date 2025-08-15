package com.brm.domain.core;

import com.brm.domain.core.vo.LettersPool;
import com.brm.domain.core.vo.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


/*
 * =================================================================================
 * RÈGLES DU BLUEPRINT VALIDÉES PAR CES TESTS :
 * =================================================================================
 * Section 2) Règles détaillées (v1)
 * ---------------------------------
 * Règle 2.2 : Un mot valide [...] est construit uniquement avec les lettres
 * actuellement disponibles (respect du compte par lettre).
 * =================================================================================
 */

@DisplayName("Tests for LettersPool")
public class LettersPoolTest {

    @Test
    @DisplayName("Should confirm a word can be formed if all letters are present")
    void shouldConfirmWordCanBeFormed() {
        var pool = new LettersPool(Map.of('H', 1, 'E', 1, 'L', 2, 'O', 1, 'W', 1, 'R', 1, 'D', 1));
        assertThat(pool.canForm(new Word("Hello"))).isTrue();
    }

    @Test
    @DisplayName("Should deny a word if a letter is missing")
    void shouldDenyWordIfLetterIsMissing() {
        // Règle 2.2
        var pool = new LettersPool(Map.of('H', 1, 'E', 1, 'L', 2, 'O', 1));
        assertThat(pool.canForm(new Word("World"))).isFalse();
    }

    @Test
    @DisplayName("Should deny a word if there are not enough of a specific letter")
    void shouldDenyWordIfNotEnoughLetters() {
        var pool = new LettersPool(Map.of('H', 1, 'E', 1, 'L', 1, 'O', 1)); // Only one 'L'
        assertThat(pool.canForm(new Word("Hello"))).isFalse();
    }

    @Test
    @DisplayName("Should return a new pool with letters removed after forming a word")
    void shouldReturnNewPoolAfterRemovingLetters() {
        var initialPool = new LettersPool(Map.of('B', 1, 'O', 2, 'N', 1, 'J', 1, 'U', 1, 'R', 1));
        var newPool = initialPool.remove(new Word("jour"));

        assertThat(newPool.getCurrentPool()).containsExactlyInAnyOrderEntriesOf(Map.of('b', 1, 'o', 1, 'n', 1));
        assertThat(initialPool.getCurrentPool()).containsKey('j'); // The original pool is unchanged
    }

    @Test
    @DisplayName("Should return the same pool with letters cannot be removed after forming a word")
    void shouldReturnTheSamePoolAfterCannotRemovingLetters() {
        var initialPool = new LettersPool(Map.of('B', 1, 'O', 2, 'N', 1, 'J', 1, 'U', 1, 'R', 1));
        var newPool = initialPool.remove(new Word("journee"));

        assertThat(newPool.getCurrentPool()).containsExactlyInAnyOrderEntriesOf(initialPool.getCurrentPool());
    }
}
