package com.internship.portal.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class LoginResponseDTO {
    @JsonProperty(value = "accessToken", required = true)
    private String accessToken;

    @JsonProperty(value = "sessionData", required = true)
    private String sessionData;
}
