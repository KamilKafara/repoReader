package com.kafarson.github.api.mapper;

import com.kafarson.github.api.dto.GithubRepoInfoResponseDTO;
import com.kafarson.github.api.dto.RepositoryInfoDTO;
import com.kafarson.utils.DateUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryMapper {

    public static RepositoryInfoDTO apply(GithubRepoInfoResponseDTO repoDTO, String dataTimePattern) {
        return RepositoryInfoDTO.builder()
                .fullName(repoDTO.getFullName())
                .description(repoDTO.getDescription())
                .cloneUrl(repoDTO.getCloneUrl())
                .stars(repoDTO.getStargazersCount())
                .createdAt(DateUtil.convertDateTime(repoDTO.getCreatedAt(), dataTimePattern))
                .build();
    }
}
