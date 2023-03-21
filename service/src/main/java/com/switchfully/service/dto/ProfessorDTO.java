package com.switchfully.service.dto;

public class ProfessorDTO {
    private String id;
    private String firstName;
    private String lastName;

    public ProfessorDTO(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
