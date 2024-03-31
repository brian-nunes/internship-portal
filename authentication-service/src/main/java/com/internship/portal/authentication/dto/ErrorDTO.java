package com.internship.portal.authentication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private String error;
    private String message;
    private String stack;
}
