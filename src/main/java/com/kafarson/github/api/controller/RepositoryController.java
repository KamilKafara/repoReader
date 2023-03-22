package com.kafarson.github.api.controller;

import com.kafarson.github.api.dto.RepositoryInfoDTO;
import com.kafarson.github.api.service.GithubRepositoryServiceImpl;
import com.kafarson.utils.DateUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RepositoryController {
    private final GithubRepositoryServiceImpl githubRepositoryService;

    @Autowired
    public RepositoryController(GithubRepositoryServiceImpl githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }

    @GetMapping(value = "/{username}/{repoName}")
    public RepositoryInfoDTO getRepositoryInfo(@PathVariable("username") @NonNull String username,
                                               @PathVariable("repoName") @NonNull String repoName,
                                               @RequestParam(defaultValue = DateUtil.DEFAULT_OUTPUT_DATE_TIME_PATTERN) String dataTimePattern) throws IOException {
        return githubRepositoryService.getRepository(username, repoName, dataTimePattern);
    }
}
