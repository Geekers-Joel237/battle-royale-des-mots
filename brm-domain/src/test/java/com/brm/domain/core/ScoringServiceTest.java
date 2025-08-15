package com.brm.domain.core;

import com.brm.domain.core.service.ScoringService;
import com.brm.domain.core.vo.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * =================================================================================
 * RÈGLES DU BLUEPRINT VALIDÉES PAR CES TESTS :
 * =================================================================================
 * Section 3) Scoring & dégâts (v1)
 * ---------------------------------
 * Règle 3.1 : Pondération des lettres (FR — base inspirée Scrabble).
 * Règle 3.2 : Application de multiplicateurs basés sur la longueur du mot.
 * - 2–3 lettres ×1.0
 * - 4–5 lettres ×1.3
 * - 6–7 lettres ×1.7
 * - ≥8 lettres ×2.4
 * =================================================================================
 */
@DisplayName("Tests for ScoringService")
public class ScoringServiceTest {

    private final ScoringService scoringService = new ScoringService();

    @Test
    @DisplayName("Should calculate score for a simple word with base letter scores")
    void shouldCalculateScoreForSimpleWord() {
        var word = new Word("mot");
        // Règle 3.1 & 3.2 (longueur 3 → x1.0)
        // MOT = 2 + 1 + 1 = 4. Score = 4
        assertThat(scoringService.calculateScore(word)).isEqualTo(4);
    }

    @Test
    @DisplayName("Should apply multipliers for rare letters and word length")
    void shouldCalculateScoreWithMultipliers() {
        // Règle 3.1 & 3.2 (longueur 4 → x1.3)
        // JAZZ = 8 + 1 + 10 + 10 = 29. 29 * 1.3 = 37.7 → 37
        assertThat(scoringService.calculateScore(new Word("jazz"))).isEqualTo(37);
    }

}
