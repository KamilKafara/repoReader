package com.kafarson.github.api.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GithubRepoInfoResponseDTO {
    private int id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private String description;
    @JsonProperty("html_url")

    private String htmlUrl;
    @JsonProperty("clone_url")

    private String cloneUrl;
    @JsonProperty("stargazers_count")

    private int stargazersCount;
    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("private")
    private boolean isPrivate;

}
