package com.brm.domain.core;

import com.brm.domain.core.ports.DictionaryPort;
import com.brm.domain.core.service.WordValidator;
import com.brm.domain.core.vo.Word;
import com.brm.infrastructure.adapters.InMemoryFakeDictionary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * =================================================================================
 * RÈGLES DU BLUEPRINT VALIDÉES PAR CES TESTS :
 * =================================================================================
 * Section 2) Règles détaillées (v1)
 * ---------------------------------
 * Règle 2.1 : Un mot valide [...] existe dans le dictionnaire v1.
 * =================================================================================
 */
@DisplayName("Tests for WordValidator")
public class WordValidatorTest {


    @Test
    @DisplayName("Should return true for a word that exists in the dictionary")
    void shouldReturnTrueForExistingWord() {
        // Règle 2.1
        // GIVEN un dictionnaire qui contient le mot "test"
        DictionaryPort fakeDictionary = new InMemoryFakeDictionary(Set.of("test", "existant"));
        WordValidator validator = new WordValidator(fakeDictionary);

        // WHEN on valide un mot qui existe
        boolean isValid = validator.isValid(new Word("test"));

        // THEN le résultat est true
        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("Should return false for a word that does not exist in the dictionary")
    void shouldReturnFalseForNonExistingWord() {
        // Règle 2.1
        // GIVEN un dictionnaire
        DictionaryPort fakeDictionary = new InMemoryFakeDictionary(Set.of("un", "deux"));
        WordValidator validator = new WordValidator(fakeDictionary);

        // WHEN on valide un mot qui n'existe pas
        boolean isValid = validator.isValid(new Word("trois"));

        // THEN le résultat est false
        assertThat(isValid).isFalse();
    }
}
