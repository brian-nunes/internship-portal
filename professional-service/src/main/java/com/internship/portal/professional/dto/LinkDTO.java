package com.internship.portal.professional.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.professional.model.Nota;
import com.internship.portal.professional.model.Token;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenDTO {
    @JsonProperty(value = "token")
    private String token;

    public TokenDTO(Token token){
        this.token = token.getToken();
    }
}
