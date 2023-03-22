package com.switchfully.service;

import com.switchfully.domain.models.Feature;
import com.switchfully.domain.repositories.UserCredentialsRepository;
import com.switchfully.domain.security.UserCredentials;
import com.switchfully.service.dto.UserCredentialsDTO;
import com.switchfully.exception.exceptions.UnauthorizatedException;
import com.switchfully.exception.exceptions.UnknownUserException;
import com.switchfully.exception.exceptions.WrongPasswordException;
import com.switchfully.service.mappers.UserCredentialsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static java.lang.String.format;

@Service
public class SecurityService {

    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final UserCredentialsRepository userCredentialsRepository;
    private final UserCredentialsMapper userCredentialsMapper;

    public SecurityService(UserCredentialsRepository userCredentialsRepository, UserCredentialsMapper userCredentialsMapper) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.userCredentialsMapper = userCredentialsMapper;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UserCredentialsDTO usernamePassword = getUsernamePassword(authorization);
        UserCredentials user = userCredentialsRepository.getUser(usernamePassword.getUsername());

        if (user == null) {
            logger.error(format("Unknown user %s", usernamePassword.getUsername()));
            throw new UnknownUserException();
        }
        if (!user.doesPasswordMatch(usernamePassword.getPassword())) {
            logger.error(format("Password does not match for user %s", usernamePassword.getUsername()));
            throw new WrongPasswordException();
        }
        if (!user.canHaveAccessTo(feature)) {
            logger.error(format("User %s does not have access to %s", usernamePassword.getUsername(), feature));
            throw new UnauthorizatedException();
        }
    }

    private UserCredentialsDTO getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UserCredentialsDTO(username, password);
    }
}