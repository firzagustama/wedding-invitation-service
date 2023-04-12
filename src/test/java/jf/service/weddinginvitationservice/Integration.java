package jf.service.weddinginvitationservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jf.service.weddinginvitationservice.model.Users;
import jf.service.weddinginvitationservice.model.request.*;
import jf.service.weddinginvitationservice.model.response.*;
import jf.service.weddinginvitationservice.repository.RsvpRepository;
import jf.service.weddinginvitationservice.repository.UserRepository;
import jf.service.weddinginvitationservice.repository.UserWishRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Integration {
    public Logger logger = Logger.getLogger(Integration.class.getName());
    @Autowired
    public WebApplicationContext webApplicationContext;
    public MockMvc mockMvc;
    public static String userId;

    @BeforeEach
    void setUpEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @BeforeAll
    static void setUpAll(@Autowired UserRepository userRepository, @Autowired UserWishRepository userWishRepository, @Autowired RsvpRepository rsvpRepository) {
        Users user = userRepository.save(new Users("Integration Test", 0, false, "08193847888"));
        userId = user.getId().toString();
    }

    @Test
    @Order(1)
    void getUser_noRsvp_success() throws Exception {
        ApiResponse<UserResponse> response = get("/user/" + userId, UserResponse.class);
        Assertions.assertTrue(response.isSuccess());

        UserResponse data = response.getData();
        Assertions.assertEquals(data.getName(), "Integration Test");
        Assertions.assertEquals(data.getTitle(), "Mr.");
        Assertions.assertNull(data.getAttending());
    }

    @Test
    @Order(2)
    void createRsvp_success() throws Exception {
        CreateRsvpRequest request = new CreateRsvpRequest();
        request.setUserId(userId);
        request.setRsvp(true);
        request.setPax(2);

        ApiResponse<CreateRsvpResponse> response = post("/rsvp/create", request, CreateRsvpResponse.class);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    @Order(3)
    void getUser_repliedRsvp_success() throws Exception {
        ApiResponse<UserResponse> response = get("/user/" + userId, UserResponse.class);
        Assertions.assertTrue(response.isSuccess());

        UserResponse data = response.getData();
        Assertions.assertEquals(data.getName(), "Integration Test");
        Assertions.assertEquals(data.getTitle(), "Mr.");
        Assertions.assertTrue(data.getAttending());
    }

    @Test
    @Order(4)
    public void createWish_success() throws Exception {
        CreateWishRequest request = new CreateWishRequest();
        request.setUserId(userId);
        request.setWish("Wish 1");

        ApiResponse<CreateWishResponse> response = post("/wish/create", request, CreateWishResponse.class);
        Assertions.assertTrue(response.isSuccess());

        var data = response.getData();
        Assertions.assertEquals("Mr.", data.getTitle());
        Assertions.assertEquals("Integration Test", data.getName());
        Assertions.assertEquals("Wish 1", data.getWish());
    }

    @Test
    @Order(5)
    public void paginationWish_valid() throws Exception {
        var request = new CreateWishRequest();
        request.setUserId(userId);

        request.setWish("Wish 2");
        post("/wish/create", request, CreateWishResponse.class);

        request.setWish("Wish 3");
        post("/wish/create", request, CreateWishResponse.class);

        request.setWish("Wish 4");
        post("/wish/create", request, CreateWishResponse.class);

        var pRequest1 = new ListWishPaginationRequest();
        pRequest1.setPageNo(1);
        pRequest1.setPageSize(2);

        var pRequest2 = new ListWishPaginationRequest();
        pRequest2.setPageNo(2);
        pRequest2.setPageSize(2);

        var response1 = post("/wish/getList", pRequest1, ListWishPaginationResponse.class);
        var response2 = post("/wish/getList", pRequest2, ListWishPaginationResponse.class);

        Assertions.assertTrue(response1.isSuccess());
        Assertions.assertTrue(response2.isSuccess());

        var data1 = response1.getData();
        var wish1 = data1.getWishes();

        Assertions.assertEquals("Wish 4", wish1.get(0).getWish());
        Assertions.assertEquals("Wish 3", wish1.get(1).getWish());

        var data2 = response2.getData();
        var wish2 = data2.getWishes();

        Assertions.assertEquals("Wish 2", wish2.get(0).getWish());
        Assertions.assertEquals("Wish 1", wish2.get(1).getWish());
    }

    private <T extends BaseResponse> ApiResponse<T> get(String url, Class<T> responseClass) throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String responseJson = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        logger.log(Level.INFO, "response: " + responseJson);
        return new Gson().fromJson(responseJson, TypeToken.getParameterized(ApiResponse.class, responseClass).getType());
    }

    private <T extends BaseResponse> ApiResponse<T> post(String url, BaseRequest request, Class<T> responseClass) throws Exception {
        logger.log(Level.INFO, "request: " + request.toString());
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        ApiRequest<BaseRequest> requestBody = new ApiRequest<>(request);
        String responseJson = mockMvc.perform(MockMvcRequestBuilders.post(url).content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        logger.log(Level.INFO, "response: " + responseJson);
        return new Gson().fromJson(responseJson, TypeToken.getParameterized(ApiResponse.class, responseClass).getType());
    }
}
