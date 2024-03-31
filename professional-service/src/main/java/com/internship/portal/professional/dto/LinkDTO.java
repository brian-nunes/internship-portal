package com.internship.portal.professional.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.professional.model.Link;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinkDTO {
    @JsonProperty(value = "id", required = true)
    private Long id;

    @JsonProperty(value = "id_professional", required = true)
    private String idProfessional;

    @JsonProperty(value = "id_user", required = true)
    private String idUser;

    public LinkDTO(Link link){
        this.id = link.getId();
        this.idProfessional = link.getIdProfessional();
        this.idUser = link.getIdUser();
    }
}
