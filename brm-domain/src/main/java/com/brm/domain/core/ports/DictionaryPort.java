package com.brm.domain.core.ports;

import com.brm.domain.core.vo.Word;

public interface DictionaryPort {
    boolean exists(Word word);
}
