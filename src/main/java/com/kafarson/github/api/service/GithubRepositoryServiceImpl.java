package com.kafarson.github.api.service;

import com.google.common.net.HttpHeaders;
import com.kafarson.github.api.FetchApi;
import com.kafarson.github.api.dto.GithubRepoInfoResponseDTO;
import com.kafarson.github.api.dto.RepositoryInfoDTO;
import com.kafarson.github.api.mapper.RepositoryMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Log4j2
public class GithubRepositoryServiceImpl implements GithubRepositoryService {
    private static final String GITHUB_URL = "https://api.github.com/repos/";
    @Value("${github.authorization.token}")
    private String GITHUB_TOKEN;
    private static final String TOKEN_PREFIX = "token ";
    private static final String ACCEPT_VALUE = "application/vnd.github+json";
    private final FetchApi fetchApi;

    @Autowired
    public GithubRepositoryServiceImpl(FetchApi fetchApi) {
        this.fetchApi = fetchApi;
    }

    @Override
    public RepositoryInfoDTO getRepository(String username, String repoName, String dataTimePattern) throws IOException {
        GithubRepoInfoResponseDTO responseInfo = fetchApi.fetchDataByType(
                GITHUB_URL.concat(username).concat("/").concat(repoName),
                Map.of(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + GITHUB_TOKEN,
                        HttpHeaders.ACCEPT, ACCEPT_VALUE),
                GithubRepoInfoResponseDTO.class);
        return RepositoryMapper.apply(responseInfo, dataTimePattern);
    }
}
