package com.kafarson.github.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepositoryInfoDTO {
    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;
}
