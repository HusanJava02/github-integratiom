package com.example.githubintegration.controller;

import com.example.githubintegration.service.GithubPublicRepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/github")
public class GithubPublicRepoController {

    private final GithubPublicRepoService githubPublicRepoService;

    @GetMapping(value = "/{username}/repos")
    public Flux<?> getRepos(@PathVariable String username, @RequestParam(defaultValue = "20", required = false) Integer pageSize,@RequestParam(defaultValue = "1", required = false) Integer page) {
        return githubPublicRepoService.retrieve(username, pageSize,page);
    }
}
