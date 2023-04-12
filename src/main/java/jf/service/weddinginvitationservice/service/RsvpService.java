package jf.service.weddinginvitationservice.service;

import jf.service.weddinginvitationservice.model.request.CreateRsvpRequest;
import jf.service.weddinginvitationservice.model.response.CreateRsvpResponse;

public interface RsvpService {
    CreateRsvpResponse createRsvp(CreateRsvpRequest request);
}
