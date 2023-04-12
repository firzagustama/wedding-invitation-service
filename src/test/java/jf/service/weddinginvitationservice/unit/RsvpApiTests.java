package jf.service.weddinginvitationservice.unit;

import jf.service.weddinginvitationservice.controller.RsvpController;
import jf.service.weddinginvitationservice.enums.ApiResponseCodes;
import jf.service.weddinginvitationservice.model.Users;
import jf.service.weddinginvitationservice.model.request.CreateRsvpRequest;
import jf.service.weddinginvitationservice.model.response.ApiResponse;
import jf.service.weddinginvitationservice.model.response.CreateRsvpResponse;
import jf.service.weddinginvitationservice.repository.RsvpRepository;
import jf.service.weddinginvitationservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RsvpApiTests extends BaseApiTests {
    @MockBean
    private RsvpRepository rsvpRepository;
    @MockBean
    private UserRepository userRepository;
    @InjectMocks
    private RsvpController rsvpController;

    @BeforeEach
    void setUp() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(getUser()));
    }

    @Test
    public void createRsvp_param_illegal() throws Exception {
        Users user = getUser();

        CreateRsvpRequest request = new CreateRsvpRequest();
        request.setUserId(user.getId().toString());
        request.setRsvp(true);
        request.setPax(0);

        ApiResponse<CreateRsvpResponse> response = post("/rsvp/create", request, CreateRsvpResponse.class);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(response.getResponseCode(), ApiResponseCodes.PARAM_ILLEGAL.getResponseCode());
        Assertions.assertEquals(response.getResponseMessage(), "pax must be between 1 to 2");
    }

    @Test
    public void createRsvp_attend_success() throws Exception {
        Users user = getUser();
        CreateRsvpRequest request = new CreateRsvpRequest();
        request.setUserId(user.getId().toString());
        request.setRsvp(true);
        request.setPax(2);

        ApiResponse<CreateRsvpResponse> response = post("/rsvp/create", request, CreateRsvpResponse.class);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void createRsvp_notAttend_success() throws Exception {
        Users user = getUser();
        CreateRsvpRequest request = new CreateRsvpRequest();
        request.setUserId(user.getId().toString());
        request.setRsvp(false);

        ApiResponse<CreateRsvpResponse> response = post("/rsvp/create", request, CreateRsvpResponse.class);
        Assertions.assertTrue(response.isSuccess());
    }
}
