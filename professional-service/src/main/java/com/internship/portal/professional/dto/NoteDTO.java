package com.internship.portal.professional.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.professional.model.Nota;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "id_user")
    private String idUser;

    @JsonProperty(value = "id_professional")
    private String idProfessional;

    @JsonProperty(value = "nota")
    private String nota;

    public NoteDTO(Nota nota) {
        this.id = nota.getId();
        this.idUser = nota.getIdUser();
        this.nota = nota.getNota();
        this.idProfessional = nota.getIdProfessional();
    }
}
