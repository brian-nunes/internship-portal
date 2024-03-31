package com.internship.portal.microservices.commons.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private String error;
    private String message;
    private String stack;
}
