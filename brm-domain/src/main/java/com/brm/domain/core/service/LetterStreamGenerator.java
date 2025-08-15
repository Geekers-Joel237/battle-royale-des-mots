package com.brm.domain.core.service;

import java.util.Random;

public class LetterStreamGenerator {
    // Une représentation simple de la fréquence des lettres en français.
    // 'E' est la plus commune, donc elle apparaît plus souvent, etc.
    private static final String FRENCH_LETTER_DISTRIBUTION =
            "EEEEEEEEEEEEEEEAAAAAAAAAIIIIIIIIIINNNNNNNNOOOOOORRRRRRSSSSSSTTTTTTUUUUUULLLLLDDD" +
                    "MMMBBCPPFGHVJQQKYWXZ";
    private final Random random;

    /**
     * Crée un générateur de lettres basé sur une graine.
     * @param seed La graine pour initialiser la séquence aléatoire.
     */
    public LetterStreamGenerator(long seed) {
        this.random = new Random(seed);

    }

    /**
     * Génère la prochaine lettre de la séquence.
     * @return Un caractère aléatoire basé sur la distribution des lettres.
     */
    public char nextLetter() {
        return FRENCH_LETTER_DISTRIBUTION.charAt(random.nextInt(FRENCH_LETTER_DISTRIBUTION.length()));
    }
}
