package com.internship.portal.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDTO {
    @JsonProperty(value = "username", required = true)
    private String username;

    @JsonProperty(value = "password", required = true)
    private String password;
}
