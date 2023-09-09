package com.example.githubintegration.service;

import com.example.githubintegration.models.dto.RepositoryDetailsDTO;
import reactor.core.publisher.Flux;

public interface GithubPublicRepoService {

    Flux<RepositoryDetailsDTO> retrieve(String username, Integer pageSize, Integer page);

}
