package com.target.auto.complete.bootstrap;

import com.target.auto.complete.service.trie.Trie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationRunner implements CommandLineRunner {
  private final Trie trie;

  public ApplicationRunner(Trie trie) {
    this.trie = trie;
  }

  @Override
  public void run(String... args) throws Exception {
    seedInitialDataIntoSystem();
  }

  private void seedInitialDataIntoSystem() {
    var feedData =
        Arrays.asList(
            "baby yoda toy",
            "baby yogurt",
            "baby yoda chia pet",
            "star wars baby yoda",
            "what is ride by wire",
            "how many states in india",
            "where is the wall street exchange",
            "where is the heart located",
            "where is the black sheep",
            "What is the weather today",
            "What is the temperature today",
            "What is the air pressure today");

    feedData.forEach(trie::insert);
  }
}
