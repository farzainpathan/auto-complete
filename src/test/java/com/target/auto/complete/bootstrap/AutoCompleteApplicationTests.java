package com.target.auto.complete.bootstrap;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = AutoCompleteApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AutoCompleteApplicationTests {

  MockMvc mockMvc;

  @Autowired
  public AutoCompleteApplicationTests(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  // ****************************************
  // ************** START-UP ****************
  // ****************************************
  @Test
  @Order(1)
  @DisplayName("Should start the application")
  public void contextLoads() {
    assertThat(mockMvc).isNotNull();
  }

  // ****************************************
  // ***************  GET  ******************
  // ****************************************
  @Test
  @Order(2)
  @DisplayName("Should get all the possible sentences for a incomplete sentence")
  public void shouldFetchAllPossibleSentences() throws Exception {
    // Given data from ApplicationRunner
    String search = "What is the";
    // When & Then
    MvcResult mvcResult =
        mockMvc
            .perform(get("/v1/complete/sentences/" + search).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.stringSearched", is("What is the")))
            .andExpect(jsonPath("$.possibleSentences[0]", is("What is the weather today")))
            .andExpect(jsonPath("$.possibleSentences[1]", is("What is the temperature today")))
            .andExpect(jsonPath("$.possibleSentences[2]", is("What is the air pressure today")))
            .andExpect(jsonPath("$.possibleSentences.*", hasSize(3)))
            .andReturn();
  }

  @Test
  @Order(3)
  @DisplayName("Should store the sentence if nothing is present")
  public void shouldStoreSentenceIfNotPresent() throws Exception {
    // Given data from ApplicationRunner
    String searchSentence1 = "Fubu shoes ";
    String searchSentence2 = "Fubu";
    // When & Then
    MvcResult mvcResult1 =
        mockMvc
            .perform(
                get("/v1/complete/sentences/" + searchSentence1).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.possibleSentences.*", hasSize(0)))
            .andReturn();

    MvcResult mvcResult2 =
        mockMvc
            .perform(
                get("/v1/complete/sentences/" + searchSentence2).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.stringSearched", is("Fubu")))
            .andExpect(jsonPath("$.possibleSentences[0]", is("Fubu shoes ")))
            .andExpect(jsonPath("$.possibleSentences.*", hasSize(1)))
            .andReturn();
  }

  // ****************************************
  // ***************  POST  *****************
  // ****************************************
  @Test
  @Order(4)
  @DisplayName("Should insert all the sentences")
  public void shouldInsertAllSentences() throws Exception {
    // Given data from ApplicationRunner
    String json = "{\"sentences\":[\"Where is my order\",\"My order status\"]}";
    // When & Then
    mockMvc
        .perform(post("/v1/insert/sentences").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sentences[0]", is("Where is my order")))
        .andExpect(jsonPath("$.sentences[1]", is("My order status")))
        .andExpect(jsonPath("$.sentences.*", hasSize(2)));
  }
}
