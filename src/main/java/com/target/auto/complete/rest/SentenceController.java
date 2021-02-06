package com.target.auto.complete.rest;

import com.target.auto.complete.domain.PossibleSentences;
import com.target.auto.complete.domain.Sentences;
import com.target.auto.complete.service.RequestSentence;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
@Api(tags = "Auto Complete Commands", value = "AllCommands")
public class SentenceController {
  private final RequestSentence requestSentence;

  public SentenceController(RequestSentence requestSentence) {
    this.requestSentence = requestSentence;
  }

  @GetMapping("/complete/sentences/{sentence}")
  @ApiOperation(value = "This endpoint fetches all possible sentences")
  public PossibleSentences getAllSentences(@PathVariable String sentence) {
    return requestSentence.allPossibleSentences(sentence);
  }

  @PostMapping("/insert/sentences")
  @ApiOperation(value = "This endpoint fetches all possible sentences")
  public Sentences insertSentences(@RequestBody Sentences sentences) {
    return requestSentence.insertWord(sentences);
  }
}
