package com.example.githubintegration.service;

import com.example.githubintegration.models.dto.RepoBranchDetailsDTO;
import com.example.githubintegration.models.dto.RepositoryDetailsDTO;
import com.example.githubintegration.models.dto.res.BranchResponse;
import com.example.githubintegration.models.dto.res.GithubRepoResponse;
import com.example.githubintegration.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static com.example.githubintegration.appconfig.Constants.ApiParamKeys.*;

@Service
@RequiredArgsConstructor
public class GithubPublicRepoServiceImpl implements GithubPublicRepoService {

    private final WebClient webClient;

    @Value(value = "${github.url.repositories}")
    private String GITHUB_REPO_URL;

    @Value(value = "${github.url.branches}")
    private String GITHUB_BRANCH_URL;

    @Override
    public Flux<RepositoryDetailsDTO> retrieve(String username, Integer pageSize, Integer page) {

        Flux<GithubRepoResponse> githubRepoResponseFlux = webClient.get()
                .uri(Utils.replaceURLParameters(GITHUB_REPO_URL, username), Map.of(TYPE, "all", PER_PAGE, pageSize, PAGE, page))
                .retrieve()
                .bodyToFlux(GithubRepoResponse.class);

        return githubRepoResponseFlux
                .map(GithubPublicRepoServiceImpl::getBuild)
                .flatMap(repositoryDetailsDTO -> retrieveBranches(Utils.replaceURLParameters(GITHUB_BRANCH_URL, username, repositoryDetailsDTO.getRepoName()))
                        .map(branches -> setBranchesToRepoDetailsDTO(repositoryDetailsDTO, branches)));

    }

    private static RepositoryDetailsDTO setBranchesToRepoDetailsDTO(RepositoryDetailsDTO repositoryDetailsDTO, List<BranchResponse> branches) {
        var branchDetails = mapToRepoBranchDetailsDTO(branches);
        repositoryDetailsDTO.setRepoBranchDetails(branchDetails);
        return repositoryDetailsDTO;
    }

    private static List<RepoBranchDetailsDTO> mapToRepoBranchDetailsDTO(List<BranchResponse> branches) {
        return branches.stream().map(branchResponse -> RepoBranchDetailsDTO
                        .builder()
                        .branchName(branchResponse.getBranchName())
                        .lastCommitSha(branchResponse.getBranchCommit().getSha())
                        .build())
                .toList();
    }

    private static RepositoryDetailsDTO getBuild(GithubRepoResponse githubRepoResponse) {
        return RepositoryDetailsDTO
                .builder()
                .repoName(githubRepoResponse.getRepoName())
                .ownerLogin(githubRepoResponse.getOwnerContainer().getOwnerUsername())
                .build();
    }

    private Mono<List<BranchResponse>> retrieveBranches(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(BranchResponse.class)
                .collectList();
    }
}
