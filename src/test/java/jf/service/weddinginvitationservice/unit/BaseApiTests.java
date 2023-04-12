package jf.service.weddinginvitationservice.unit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jf.service.weddinginvitationservice.model.Users;
import jf.service.weddinginvitationservice.model.request.ApiRequest;
import jf.service.weddinginvitationservice.model.request.BaseRequest;
import jf.service.weddinginvitationservice.model.response.ApiResponse;
import jf.service.weddinginvitationservice.model.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootTest(classes = { UsersApiTests.class, UserWishApiTests.class})
@AutoConfigureMockMvc
public class BaseApiTests {
    public Logger logger = Logger.getLogger(BaseApiTests.class.getName());
    public MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    public final String userFound = "10503633-da77-4051-9361-bdc476887f0c";
    public final String userNotFound = "fdf981ae-eb3d-4b31-a13d-6d65ca0f35c8";

    public Users getUser() {
        // user male not married
        Users user = new Users();
        user.setId(UUID.fromString(userFound));
        user.setName("Firza Gustama");
        user.setGender(0);
        user.setMarried(false);
        user.setPhoneNumber("081931066028");

        return user;
    }

    public List<Users> getUsers() {
        List<Users> users = new ArrayList<>();
        users.add(getUser());
        users.add(new Users("Gilang Ramadhan", 0, false, "081937748888"));
        users.add(new Users("Fauzy Iskandar", 0, false, "081937748888"));
        users.add(new Users("Silvi Absharina", 1, true, "081937748888"));
        return users;
    }

    public <T extends BaseResponse> ApiResponse<T> get(String url, Class<T> responseClass) throws Exception {
        logger.log(Level.INFO, "request: " + url);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String responseJson = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        logger.log(Level.INFO, "response: " + responseJson);
        return new Gson().fromJson(responseJson, TypeToken.getParameterized(ApiResponse.class, responseClass).getType());
    }

    public <T extends BaseResponse> ApiResponse<T> post(String url, BaseRequest request, Class<T> responseClass) throws Exception {
        logger.log(Level.INFO, "request: " + request.toString());
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        ApiRequest<BaseRequest> requestBody = new ApiRequest<>(request);
        String responseJson = mockMvc.perform(MockMvcRequestBuilders.post(url).content(requestBody.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        logger.log(Level.INFO, "response: " + responseJson);
        return new Gson().fromJson(responseJson, TypeToken.getParameterized(ApiResponse.class, responseClass).getType());
    }
}
