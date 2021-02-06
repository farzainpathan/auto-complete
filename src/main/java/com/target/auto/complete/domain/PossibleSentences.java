package com.target.auto.complete.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "List of all possible sentences")
public class PossibleSentences {

    @ApiModelProperty(notes = "Sentence searched")
    @JsonProperty("stringSearched")
    private String searchedString;

    @ApiModelProperty(notes = "All possible sentences")
    @JsonProperty("possibleSentences")
    private List<String> sentencesList;
}
