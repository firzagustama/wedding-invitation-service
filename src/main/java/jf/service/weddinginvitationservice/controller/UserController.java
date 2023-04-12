package jf.service.weddinginvitationservice.controller;

import jf.service.weddinginvitationservice.model.response.ApiResponse;
import jf.service.weddinginvitationservice.model.response.UserResponse;
import jf.service.weddinginvitationservice.service.UserService;
import jf.service.weddinginvitationservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ApiResponse<UserResponse> getUser(@PathVariable("id") String id) {
        UserResponse response = userService.getUser(UUID.fromString(id));
        return new ApiResponse<>(response);
    }
}
