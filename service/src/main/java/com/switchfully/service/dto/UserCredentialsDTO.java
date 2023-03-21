package com.switchfully.service.dto;

public class UserCredentialsDTO {

    private final String username;
    private final String password;

    public UserCredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
