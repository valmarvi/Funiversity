package com.switchfully.domain.repositories;

import com.switchfully.domain.models.Role;
import com.switchfully.domain.security.UserCredentials;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCredentialsRepository {

    protected final List<UserCredentials> userCredentialsDatabase = new ArrayList<>(
            List.of(new UserCredentials("aschmitt","pwd", Role.ADMIN),
                    new UserCredentials("rkean", "pwd", Role.USER),
                    new UserCredentials("sbernard", "pwd", Role.USER)));

    public UserCredentials getUser(String username) {
        return userCredentialsDatabase.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

}
