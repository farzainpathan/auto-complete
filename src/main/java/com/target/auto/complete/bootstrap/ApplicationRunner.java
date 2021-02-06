package com.target.auto.complete.bootstrap;

import com.target.auto.complete.service.trie.Trie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
  private final Trie trie;

  public ApplicationRunner(Trie trie) {
    this.trie = trie;
  }

  @Override
  public void run(String... args) throws Exception {
    seedData();
  }

  private void seedData() {
    trie.insert("amazon");
    trie.insert("amazon prime");
    trie.insert("amazing");
    trie.insert("amazing spider man");
    trie.insert("amazed");
    trie.insert("alibaba");
    trie.insert("ali express");
    trie.insert("ebay");
    trie.insert("walmart");
    trie.insert("What is the weather today");
    trie.insert("What is the temperature today");
    trie.insert("What is the air pressure today");
  }
}
