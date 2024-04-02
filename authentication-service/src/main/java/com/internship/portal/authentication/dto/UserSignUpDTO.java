package com.internship.portal.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpDTO {


    @JsonProperty(value = "username", required = true)
    private String username;

    @JsonProperty(value = "password", required = true)
    private String password;

    @JsonProperty(value = "mail", required = true)
    private String mail;

    @JsonProperty(value = "documentNumber", required = true)
    private String documentNumber;
}
