package com.target.auto.complete.service;

import com.target.auto.complete.domain.Sentences;
import com.target.auto.complete.domain.PossibleSentences;

public interface RequestSentence {
  PossibleSentences allPossibleSentences(String word);

  Sentences insertWord(Sentences sentences);
}
