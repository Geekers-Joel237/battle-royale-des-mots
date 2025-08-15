package com.brm.domain.core;

import com.brm.domain.application.usecases.SubmitWordCommand;
import com.brm.domain.application.usecases.SubmitWordResult;
import com.brm.domain.application.usecases.SubmitWordUseCase;
import com.brm.domain.core.ports.DictionaryPort;
import com.brm.domain.core.service.ScoringService;
import com.brm.domain.core.service.WordValidator;
import com.brm.domain.core.vo.LettersPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * =================================================================================
 * RÈGLES DU BLUEPRINT VALIDÉES PAR CES TESTS :
 * =================================================================================
 * Cet UseCase orchestre la validation complète d'un mot soumis, en appliquant
 * les règles 2.1, 2.2, 2.3 et en utilisant la logique de scoring de la section 3.
 * =================================================================================
 */
@DisplayName("Tests for SubmitWordUseCase")
public class SubmitWordUseCaseTest {

    private SubmitWordUseCase useCase;

    @BeforeEach
    void setup() {
        DictionaryPort dictionaryPort = new InMemoryFakeDictionary(
                Set.of("bonjour", "mot")
        );
        ScoringService scoringService = new ScoringService();
        WordValidator wordValidator = new WordValidator(dictionaryPort);
        this.useCase = new SubmitWordUseCase(
                wordValidator,
                scoringService
        );
    }

    @Test
    @DisplayName("Should return Success when the word is valid and formable")
    void shouldSucceedForValidWord() {
        // GIVEN un pool de lettres et un mot valide
        var pool = new LettersPool(Map.of('b', 1, 'o', 2, 'n', 1, 'j', 1, 'u', 1, 'r', 1));
        var command = new SubmitWordCommand("bonjour", pool);

        // WHEN on exécute le cas d'usage
        var result = useCase.execute(command);

        // THEN le résultat est un succès
        assertThat(result).isInstanceOf(SubmitWordResult.Success.class);
        var success = (SubmitWordResult.Success) result;
        assertThat(success.score()).isGreaterThan(0);
        assertThat(success.newPool().getCurrentPool()).isEmpty(); // toutes les lettres ont été utilisées
    }

    @Test
    @DisplayName("Should return Failure when the word is not in the dictionary")
    void shouldFailForNonExistingWord() {
        var pool = new LettersPool(Map.of('t', 1, 'e', 1, 's', 1, 'j', 1));
        var command = new SubmitWordCommand("test", pool); // "test" n'est pas dans notre faux dictionnaire
        var result = useCase.execute(command);
        assertThat(result).isEqualTo(new SubmitWordResult.Failure(SubmitWordResult.Reason.NOT_IN_DICTIONARY));
    }

    @Test
    @DisplayName("Should return Failure when letters are not available")
    void shouldFailWhenLettersNotAvailable() {
        var pool = new LettersPool(Map.of('m', 1, 'o', 1)); // il manque un 't'
        var command = new SubmitWordCommand("mot", pool);
        var result = useCase.execute(command);
        assertThat(result).isEqualTo(new SubmitWordResult.Failure(SubmitWordResult.Reason.LETTERS_NOT_AVAILABLE));
    }

}
