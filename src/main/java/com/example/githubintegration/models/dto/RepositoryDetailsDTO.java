package com.example.githubintegration.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonPropertyOrder(value = {"repoName","ownerLogin","repoBranchDetails"})
public class RepositoryDetailsDTO {
    @JsonProperty(value = "repoName")
    private String repoName;

    @JsonProperty(value = "ownerLogin")
    private String ownerLogin;

    private List<RepoBranchDetailsDTO> repoBranchDetails;

}
