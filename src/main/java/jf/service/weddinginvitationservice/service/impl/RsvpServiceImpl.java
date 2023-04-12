package jf.service.weddinginvitationservice.service.impl;

import jf.service.weddinginvitationservice.enums.ApiResponseCodes;
import jf.service.weddinginvitationservice.exception.ApiException;
import jf.service.weddinginvitationservice.model.Rsvp;
import jf.service.weddinginvitationservice.model.Users;
import jf.service.weddinginvitationservice.model.request.CreateRsvpRequest;
import jf.service.weddinginvitationservice.model.response.CreateRsvpResponse;
import jf.service.weddinginvitationservice.repository.RsvpRepository;
import jf.service.weddinginvitationservice.repository.UserRepository;
import jf.service.weddinginvitationservice.service.RsvpService;
import jf.service.weddinginvitationservice.util.ParamHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RsvpServiceImpl implements RsvpService {
    @Autowired
    private RsvpRepository rsvpRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateRsvpResponse createRsvp(CreateRsvpRequest request) {
        ParamHelper.paramNotNull(request, "request");
        ParamHelper.paramNotNullOrBlank(request.getUserId(), "userId");
        ParamHelper.paramNotNull(request.isRsvp(), "rsvp");
        if (request.isRsvp()) {
            ParamHelper.paramBetween(request.getPax(), 1, 2, "pax");
        }

        UUID userId = UUID.fromString(request.getUserId());
        Users user = userRepository.findById(userId).orElseThrow(() -> new ApiException(ApiResponseCodes.USER_NOT_FOUND));

        Rsvp rsvp = new Rsvp();
        rsvp.setUser(user);
        rsvp.setAttending(request.isRsvp());
        rsvp.setPax(request.getPax());

        rsvpRepository.save(rsvp);
        return new CreateRsvpResponse();
    }
}
