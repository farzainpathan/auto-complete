package com.target.auto.complete.service;

import com.target.auto.complete.domain.PossibleSentences;
import com.target.auto.complete.domain.Sentences;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutoCompleteApplicationServiceTests {

  @Captor ArgumentCaptor<String> searchCaptor;

  // ****************************************
  // ************** START-UP ****************
  // ****************************************
  @Test
  @Order(1)
  @DisplayName("should start the application")
  public void shouldStart(@Mock RequestSentence requestSentence) {
    assertThat(requestSentence).isNotNull();
  }

  // ****************************************
  // ***************  GET  ******************
  // ****************************************
  @Test
  @Order(2)
  @DisplayName("Should get all possible complete sentence for the given sentence")
  public void shouldGetAllSentences(@Mock RequestSentence requestSentence) {
    // Given
    String search = "What is the";
    when(requestSentence.allPossibleSentences(search)).thenReturn(mockMethodResponse(search));
    // When
    PossibleSentences sentences = requestSentence.allPossibleSentences(search);
    // Then
    assertThat(sentences)
        .isNotNull()
        .extracting("searchedString", "sentencesList")
        .contains("What is the", Collections.emptyMap());
    verify(requestSentence, times(1)).allPossibleSentences(search);
  }

  @Test
  @Order(3)
  @DisplayName("Should captor arguments for all possible search")
  public void shouldGetAllSentences_Captor(@Mock RequestSentence requestSentence) {
    // Given
    String search = "What is the";
    when(requestSentence.allPossibleSentences(search)).thenReturn(mockMethodResponse(search));
    // When
    PossibleSentences sentences = requestSentence.allPossibleSentences(search);
    // Then
    verify(requestSentence).allPossibleSentences(searchCaptor.capture());
    assertThat(searchCaptor.getValue()).isEqualTo(search);
  }

  // ****************************************
  // ***************  SAVE  *****************
  // ****************************************
  @Test
  @Order(4)
  @DisplayName("Should Insert the given list into data structure")
  public void shouldInsert(@Mock RequestSentence requestSentence) {
    // Given
    when(requestSentence.insertWord(mockMethodRequest())).thenReturn(mockMethodRequest());
    // When
    Sentences sentences = requestSentence.insertWord(mockMethodRequest());
    // Then
    assertThat(sentences).isNotNull().isEqualTo(mockMethodRequest());
    verify(requestSentence, times(1)).insertWord(mockMethodRequest());
  }

  // ****************************************
  // ***************  HELPER  ***************
  // ****************************************
  private PossibleSentences mockMethodResponse(String search) {
    return PossibleSentences.builder()
        .searchedString(search)
        .sentencesList(Collections.emptyMap())
        .build();
  }

  private Sentences mockMethodRequest() {
    return Sentences.builder()
        .sentencesList(List.of("Where is bangalore", "Where is farzain pathan"))
        .build();
  }
}
