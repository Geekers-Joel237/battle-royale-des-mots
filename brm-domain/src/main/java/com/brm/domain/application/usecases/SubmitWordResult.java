package com.brm.domain.application.usecases;

import com.brm.domain.core.vo.LettersPool;

public sealed interface SubmitWordResult {
    enum Reason {
        TOO_SHORT,
        NOT_IN_DICTIONARY,
        LETTERS_NOT_AVAILABLE,
        ALREADY_SUBMITTED
    }

    record Success(int score, LettersPool newPool) implements SubmitWordResult {
    }

    record Failure(Reason reason) implements SubmitWordResult {
    }
}
