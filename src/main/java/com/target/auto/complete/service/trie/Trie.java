package com.target.auto.complete.service.trie;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Trie {
  private final TrieNode root;

  public Trie() {
    root = new TrieNode(' ');
  }

  public void insert(String sentence) {
    if (search(sentence)) return;

    TrieNode current = root;
    TrieNode pre;
    for (char ch : sentence.toCharArray()) {
      pre = current;
      TrieNode child = current.getChild(ch);
      if (child != null) {
        current = child;
        child.parent = pre;
      } else {
        current.children.add(new TrieNode(ch));
        current = current.getChild(ch);
        current.parent = pre;
      }
    }
    current.isEnd = true;
  }

  public boolean search(String sentence) {
    TrieNode current = root;
    for (char ch : sentence.toCharArray()) {
      if (current.getChild(ch) == null) return false;
      else {
        current = current.getChild(ch);
      }
    }
    return current.isEnd;
  }

  public List<String> autocomplete(String prefix) {
    TrieNode lastNode = root;
    for (int i = 0; i < prefix.length(); i++) {
      lastNode = lastNode.getChild(prefix.charAt(i));
      if (lastNode == null) return new ArrayList<String>();
    }

    return lastNode.getWords();
  }
}
