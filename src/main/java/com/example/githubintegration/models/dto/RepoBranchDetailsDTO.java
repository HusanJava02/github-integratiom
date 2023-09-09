package com.example.githubintegration.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RepoBranchDetailsDTO {
    @JsonProperty(value = "branchName")
    private String branchName;
    @JsonProperty(value = "lastCommitSha")
    private String lastCommitSha;
}
