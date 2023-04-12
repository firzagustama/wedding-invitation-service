package jf.service.weddinginvitationservice.unit;

import jf.service.weddinginvitationservice.controller.UserController;
import jf.service.weddinginvitationservice.enums.ApiResponseCodes;
import jf.service.weddinginvitationservice.model.response.ApiResponse;
import jf.service.weddinginvitationservice.model.response.UserResponse;
import jf.service.weddinginvitationservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsersApiTests extends BaseApiTests {
    @MockBean
    private UserRepository userRepository;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        when(userRepository.findById(any(UUID.class))).thenAnswer(a -> {
            String userId = a.getArgument(0).toString();
            if (userId.equals(userFound)) {
                return Optional.of(getUser());
            } else {
                return Optional.empty();
            }
        });
    }

    @Test
    public void getUserById_notFound() throws Exception {
        ApiResponse<UserResponse> response = get("/user/" + userNotFound, UserResponse.class);

        assertFalse(response.isSuccess());
        assertEquals(response.getResponseCode(), ApiResponseCodes.USER_NOT_FOUND.getResponseCode());
    }

    @Test
    public void getUserById_found() throws Exception {
        ApiResponse<UserResponse> response = get("/user/" + userFound, UserResponse.class);
        UserResponse data = response.getData();

        assertTrue(response.isSuccess());
        assertNotNull(data);
        assertEquals("Firza Gustama", data.getName());
        assertEquals("Mr.", data.getTitle());
    }
}
