package com.brm.domain.application.usecases;

import com.brm.domain.core.service.ScoringService;
import com.brm.domain.core.service.WordValidator;
import com.brm.domain.core.vo.Word;

public class SubmitWordUseCase {
    private final WordValidator validator;
    private final ScoringService scoringService;

    public SubmitWordUseCase(
            WordValidator validator,
            ScoringService scoringService
    ) {

        this.validator = validator;
        this.scoringService = scoringService;
    }

    public SubmitWordResult execute(SubmitWordCommand command) {
        Word word;
        try {
            // Étape 1: Valider le format du mot (longueur, etc.)
            word = new Word(command.rawWord());
        } catch (IllegalArgumentException e) {
            return new SubmitWordResult.Failure(SubmitWordResult.Reason.TOO_SHORT);
        }

        // Étape 2: Valider que le mot existe dans le dictionnaire
        if (!this.validator.isValid(word)) {
            return new SubmitWordResult.Failure(SubmitWordResult.Reason.NOT_IN_DICTIONARY);
        }

        // Étape 3: Valider que les lettres sont disponibles dans le pool
        if (!command.currentPool().canForm(word)) {
            return new SubmitWordResult.Failure(SubmitWordResult.Reason.LETTERS_NOT_AVAILABLE);
        }

        // Étape 4: Si tout est valide, calculer le score et le nouvel état du pool
        // Étape 5: Retourner le résultat de succès
        int score = this.scoringService.calculateScore(word);
        return new SubmitWordResult.Success(score, command.currentPool().remove(word));
    }
}
