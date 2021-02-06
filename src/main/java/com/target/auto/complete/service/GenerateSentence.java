package com.target.auto.complete.service;

import com.target.auto.complete.domain.Sentences;
import com.target.auto.complete.domain.PossibleSentences;
import com.target.auto.complete.service.trie.Trie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GenerateSentence implements RequestSentence {
  private final Trie trie;

  public GenerateSentence(Trie trie) {
    this.trie = trie;
  }

  @Override
  public PossibleSentences allPossibleSentences(String word) {
    List<String> stringList = trie.autocomplete(word);
    if (stringList.isEmpty()) trie.insert(word);
    log.info("All possible suggestion : " + stringList);
    return PossibleSentences.builder().searchedString(word).sentencesList(stringList).build();
  }

  @Override
  public Sentences insertWord(Sentences sentences) {
    log.info("Inserting all the sentences into data structure : " + sentences);
    sentences.getSentencesList().forEach(trie::insert);
    return sentences;
  }
}
