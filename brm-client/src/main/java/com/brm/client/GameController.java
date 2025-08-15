package com.brm.client;


import com.brm.domain.application.usecases.SubmitWordCommand;
import com.brm.domain.application.usecases.SubmitWordResult;
import com.brm.domain.application.usecases.SubmitWordUseCase;
import com.brm.domain.core.ports.DictionaryPort;
import com.brm.domain.core.service.ScoringService;
import com.brm.domain.core.service.WordValidator;
import com.brm.domain.core.vo.LettersPool;
import com.brm.domain.core.vo.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Map;
import java.util.stream.Collectors;

public class GameController {
    // --- Connexion aux éléments FXML ---
    @FXML
    private Label lettersPoolLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private TextField wordInputField;
    @FXML
    private Label feedbackLabel;

    // --- Moteur du jeu ---
    private SubmitWordUseCase useCase;
    private LettersPool currentPool;
    private int score = 0;

    // --- Initialisation ---
    @FXML
    public void initialize() {
        // On instancie toute notre logique de jeu ici pour ce prototype solo
        // Pour la v1 du client, on peut utiliser une implémentation simple du dictionnaire

        DictionaryPort dictionaryPort = new ClientDictionary();
        WordValidator wordValidator = new WordValidator(dictionaryPort);
        ScoringService scoringService = new ScoringService();
        this.useCase = new SubmitWordUseCase(wordValidator, scoringService);

        // On crée le premier pool de lettres
        this.currentPool = new LettersPool(Map.of('B', 1, 'T', 1, 'N', 1, 'J', 1, 'O', 1, 'U', 1, 'R', 2));
        updateUI();

    }

    @FXML
    public void handleSubmitWord() {
        String rawWord = this.wordInputField.getText();
        if (rawWord.isBlank()) {
            this.feedbackLabel.setText("Le mot est vide !");
            return;
        }

        var command = new SubmitWordCommand(rawWord, this.currentPool);
        var result = this.useCase.execute(command);

        switch (result) {
            case SubmitWordResult.Success success -> {
                this.score += success.score();
                this.currentPool = success.newPool();
                this.feedbackLabel.setText("Bravo ! +" + success.score() + " points !");
            }
            case SubmitWordResult.Failure failure -> this.feedbackLabel.setText("Mauvais mot ! " + failure.reason());
        }

        updateUI();
        this.wordInputField.clear();
    }

    private void updateUI() {
        this.scoreLabel.setText("Score : " + this.score);
        this.lettersPoolLabel.setText(
                this.currentPool.getCurrentPool().entrySet().stream()
                        .flatMap(entry -> entry.getKey()
                                .toString()
                                .repeat(entry.getValue())
                                .chars()
                                .mapToObj(
                                        c -> String.valueOf((char) c)
                                )).collect(Collectors.joining(" "))
        );
    }

}

class ClientDictionary implements DictionaryPort {
    // Pour le prototype, on met quelques mots en dur.
    // Plus tard, on pourra aussi le charger depuis un fichier.
    private final java.util.Set<String> words = java.util.Set.of("bonjour", "jour", "bon", "joue", "our");

    @Override
    public boolean exists(Word word) {
        return words.contains(word.value());
    }

}