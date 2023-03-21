package com.switchfully.service.mappers;

import com.switchfully.domain.models.Professor;
import com.switchfully.domain.models.Role;
import com.switchfully.domain.security.UserCredentials;
import com.switchfully.service.dto.UserCredentialsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCredentialsMapper {

    //UserCredentials -> UserCredentialsDTO
    public UserCredentialsDTO toDTO(UserCredentials userCredentials) {
        return new UserCredentialsDTO(userCredentials.getUsername(), userCredentials.getPassword());
    }

    //List<UserCredentials> -> List<UserCredentialsDTO>
    public List<UserCredentialsDTO> toDTO(List<UserCredentials> userCredentialsList) {
        return userCredentialsList.stream()
                .map(this::toDTO)
                .toList();
    }
}
