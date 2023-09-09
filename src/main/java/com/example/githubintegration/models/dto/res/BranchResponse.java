package com.example.githubintegration.models.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchResponse {
    @JsonProperty(value = "name")
    private String branchName;
    @JsonProperty(value = "commit")
    private BranchCommit branchCommit;
}
