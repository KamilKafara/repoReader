package com.kafarson.github.api.service;

import com.kafarson.github.api.dto.RepositoryInfoDTO;

import java.io.IOException;

public interface GithubRepositoryService {
    RepositoryInfoDTO getRepository(String username, String repoName, String dataTimePattern) throws IOException;
}
