package com.switchfully.domain.security;

import com.switchfully.domain.models.Feature;
import com.switchfully.domain.models.Role;

public class UserCredentials {

    private final String username;
    private final String password;
    private Role role;

    public UserCredentials(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
}
