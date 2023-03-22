package com.kafarson.github.api.mapper;

import com.kafarson.github.api.dto.GithubRepoInfoResponseDTO;
import com.kafarson.github.api.dto.RepositoryInfoDTO;
import com.kafarson.utils.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class RepositoryMapperTest {

    @Test
    public void applyShouldPass() {
        //given
        GithubRepoInfoResponseDTO repositoryInfo = createGithubRepoInfoResponseDTO();
        RepositoryInfoDTO expectedRepositoryInfoDTO = createRepositoryInfoDTO();
        //when
        RepositoryInfoDTO actualResult = RepositoryMapper.apply(repositoryInfo, DateUtil.DEFAULT_OUTPUT_DATE_TIME_PATTERN);
        //then
        Assert.assertEquals(expectedRepositoryInfoDTO.getStars(), actualResult.getStars());
        Assert.assertEquals(expectedRepositoryInfoDTO.getDescription(), actualResult.getDescription());
        Assert.assertEquals(expectedRepositoryInfoDTO.getFullName(), actualResult.getFullName());
        Assert.assertEquals(expectedRepositoryInfoDTO.getCreatedAt(), actualResult.getCreatedAt());
        Assert.assertEquals(expectedRepositoryInfoDTO.getCloneUrl(), actualResult.getCloneUrl());
    }

    @Test
    public void applyShouldPassWithDifferentPattern() {
        //given
        GithubRepoInfoResponseDTO repositoryInfo = createGithubRepoInfoResponseDTO();
        RepositoryInfoDTO expectedRepositoryInfoDTO = createRepositoryInfoDTO();
        //when
        RepositoryInfoDTO actualResult = RepositoryMapper.apply(repositoryInfo, "YYYY HH-mm");
        //then
        Assert.assertEquals(expectedRepositoryInfoDTO.getStars(), actualResult.getStars());
        Assert.assertEquals(expectedRepositoryInfoDTO.getDescription(), actualResult.getDescription());
        Assert.assertEquals(expectedRepositoryInfoDTO.getFullName(), actualResult.getFullName());
        Assert.assertEquals("2023 20-49", actualResult.getCreatedAt());
        Assert.assertEquals(expectedRepositoryInfoDTO.getCloneUrl(), actualResult.getCloneUrl());
    }

    private GithubRepoInfoResponseDTO createGithubRepoInfoResponseDTO() {
        return GithubRepoInfoResponseDTO.builder()
                .id(123)
                .name("repo")
                .fullName("testUser/testRepo")
                .description("someDescription")
                .cloneUrl("https//github.com/testUser/testRepo")
                .stargazersCount(12)
                .createdAt("2023-02-03T20:49:35Z")
                .isPrivate(true)
                .build();
    }

    private RepositoryInfoDTO createRepositoryInfoDTO() {
        return RepositoryInfoDTO.builder()
                .fullName("testUser/testRepo")
                .description("someDescription")
                .cloneUrl("https//github.com/testUser/testRepo")
                .stars(12)
                .createdAt("2023-02-03 20:49")
                .build();
    }
}
