package jf.service.weddinginvitationservice.service.impl;

import jf.service.weddinginvitationservice.enums.ApiResponseCodes;
import jf.service.weddinginvitationservice.exception.ApiException;
import jf.service.weddinginvitationservice.model.Users;
import jf.service.weddinginvitationservice.model.response.UserResponse;
import jf.service.weddinginvitationservice.repository.UserRepository;
import jf.service.weddinginvitationservice.service.UserService;
import jf.service.weddinginvitationservice.util.ParamHelper;
import jf.service.weddinginvitationservice.util.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse getUser(UUID id) {
        ParamHelper.paramNotNull(id, "id");

        Users user = userRepository.findById(id).orElseThrow(() -> new ApiException(ApiResponseCodes.USER_NOT_FOUND));

        String title = UserHelper.getTitle(user.getGender(), user.isMarried());

        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setTitle(title);
        response.setAttending(user.getRsvp() == null ? null : user.getRsvp().isAttending());
        return response;
    }
}
