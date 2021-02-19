package com.target.auto.complete.service;

import com.target.auto.complete.domain.PossibleSentences;
import com.target.auto.complete.domain.Sentences;
import com.target.auto.complete.service.trie.Trie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class GenerateSentence implements RequestSentence {
  private final Trie trie;
  private final Map<String, Integer> sentencesOccurrence = new HashMap<>();

  public GenerateSentence(Trie trie) {
    this.trie = trie;
  }

  @Override
  public PossibleSentences allPossibleSentences(String sentence) {
    var stringList = trie.autocomplete(sentence);

    if (stringList.isEmpty()) trie.insert(sentence);
    else updateTheOccurrence(stringList);

    log.info("All possible suggestion from data structure: " + stringList);
    sortTheMapInDescendingOrder();

    return PossibleSentences.builder()
        .searchedString(sentence)
        .sentencesList(filterMapAsPerRequest(stringList))
        .build();
  }

  @Override
  public Sentences insertWord(Sentences sentences) {
    log.info("Inserting all the sentences into data structure : " + sentences);
    sentences.getSentencesList().forEach(trie::insert);
    return sentences;
  }

  private Map<String, Integer> filterMapAsPerRequest(List<String> stringList) {
    Map<String, Integer> filteredOutput = new HashMap<>();
    stringList.forEach(
        s -> {
          if (sentencesOccurrence.containsKey(s)) filteredOutput.put(s, sentencesOccurrence.get(s));
        });
    log.info("Filtered map  : " + filteredOutput);
    return filteredOutput;
  }

  private void sortTheMapInDescendingOrder() {
    Map<String, Integer> outputMap =
        sentencesOccurrence.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    log.info("Sorted map  : " + outputMap);
  }

  private void updateTheOccurrence(List<String> stringList) {
    stringList.forEach(
        sentence -> {
          if (sentencesOccurrence.containsKey(sentence)) {
            sentencesOccurrence.replace(sentence, sentencesOccurrence.get(sentence) + 1);
          } else {
            sentencesOccurrence.put(sentence, 1);
          }
        });
  }
}
