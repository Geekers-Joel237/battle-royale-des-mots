package com.brm.domain.application.usecases;

import com.brm.domain.core.vo.LettersPool;

public record SubmitWordCommand(String rawWord, LettersPool currentPool) {
}
