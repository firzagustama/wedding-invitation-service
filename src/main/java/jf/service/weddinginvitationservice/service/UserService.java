package jf.service.weddinginvitationservice.service;

import jf.service.weddinginvitationservice.model.response.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse getUser(UUID id);
}
