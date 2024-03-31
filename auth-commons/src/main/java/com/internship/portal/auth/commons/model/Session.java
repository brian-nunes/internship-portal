package com.internship.portal.auth.commons.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
public class Session implements Serializable {
    private String name;
    private String documentNumber;
    private String mail;
    private String role;

    @Override
    public int hashCode() {
        return Objects.hash(name, documentNumber, mail, role);
    }

    @Override
    public String toString() {
        return "Session{" +
                "name='" + name + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}