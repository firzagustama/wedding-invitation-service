package jf.service.weddinginvitationservice.controller;

import jf.service.weddinginvitationservice.model.request.ApiRequest;
import jf.service.weddinginvitationservice.model.request.CreateRsvpRequest;
import jf.service.weddinginvitationservice.model.response.ApiResponse;
import jf.service.weddinginvitationservice.model.response.CreateRsvpResponse;
import jf.service.weddinginvitationservice.service.RsvpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rsvp")
public class RsvpController {
    @Autowired
    private RsvpService rsvpService;

    @PostMapping(value = "create")
    public ApiResponse<CreateRsvpResponse> createRsvp(@RequestBody ApiRequest<CreateRsvpRequest> request) {
        CreateRsvpResponse response = rsvpService.createRsvp(request.getData());
        return new ApiResponse<>(response);
    }
}
