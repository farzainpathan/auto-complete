package com.target.auto.complete.service;

import com.target.auto.complete.domain.PossibleSentences;
import com.target.auto.complete.domain.Sentences;

public interface RequestSentence {
  PossibleSentences allPossibleSentences(String sentence);

  Sentences insertWord(Sentences sentences);
}
