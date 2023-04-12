package jf.service.weddinginvitationservice.unit;

import jf.service.weddinginvitationservice.controller.WishController;
import jf.service.weddinginvitationservice.model.UserWishes;
import jf.service.weddinginvitationservice.model.Users;
import jf.service.weddinginvitationservice.model.request.CreateWishRequest;
import jf.service.weddinginvitationservice.model.request.ListWishPaginationRequest;
import jf.service.weddinginvitationservice.model.response.ApiResponse;
import jf.service.weddinginvitationservice.model.response.CreateWishResponse;
import jf.service.weddinginvitationservice.model.response.ListWishPaginationResponse;
import jf.service.weddinginvitationservice.model.response.PaginationResponse;
import jf.service.weddinginvitationservice.repository.UserRepository;
import jf.service.weddinginvitationservice.repository.UserWishRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserWishApiTests extends BaseApiTests {
    @MockBean
    private UserWishRepository userWishRepository;
    @MockBean
    private UserRepository userRepository;
    @InjectMocks
    private WishController wishController;

    @BeforeEach
    public void setup() {
        List<Users> users = getUsers();

        Date now = new Date();

        List<UserWishes> data = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            data.add(new UserWishes(i + 1, "Wish " + i, now, users.get(i)));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            now = calendar.getTime();
        }

        when(userWishRepository.findAll(any(Pageable.class))).thenAnswer(a -> {
            PageRequest p = a.getArgument(0);

            int start = (int) p.getOffset();
            int end = Math.min(start + p.getPageSize(), data.size());
            return new PageImpl<>(data.subList(start, end), p, data.size());
        });
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(getUser()));
    }

    @Test
    public void getUserWishes_success() throws Exception {
        ListWishPaginationRequest request = new ListWishPaginationRequest();
        request.setPageNo(1);
        request.setPageSize(10);

        ApiResponse<ListWishPaginationResponse> response = post("/wish/getList", request, ListWishPaginationResponse.class);
        Assertions.assertTrue(response.isSuccess());

        ListWishPaginationResponse data = response.getData();
        Assertions.assertEquals(data.getInfo().getTotalData(), 4);
        Assertions.assertEquals(data.getWishes().size(), 4);
    }

    @Test
    public void getUserWishses_withPagination_success() throws Exception {
        ListWishPaginationRequest request1 = new ListWishPaginationRequest();
        request1.setPageSize(2);
        request1.setPageNo(1);

        ApiResponse<ListWishPaginationResponse> response1 = post("/wish/getList", request1, ListWishPaginationResponse.class);
        Assertions.assertTrue(response1.isSuccess());

        ListWishPaginationResponse data1 = response1.getData();

        PaginationResponse info1 = data1.getInfo();
        Assertions.assertEquals(2, info1.getNextPageNo());
        Assertions.assertEquals(4, info1.getTotalData());
        Assertions.assertEquals(2, info1.getTotalPage());

        List<CreateWishResponse> wishes1 = data1.getWishes();
        Assertions.assertEquals(2, wishes1.size());
        Assertions.assertEquals("Firza Gustama", wishes1.get(0).getName());
        Assertions.assertEquals("Gilang Ramadhan", wishes1.get(1).getName());

        ListWishPaginationRequest request2 = new ListWishPaginationRequest();
        request2.setPageSize(2);
        request2.setPageNo(2);

        ApiResponse<ListWishPaginationResponse> response2 = post("/wish/getList", request2, ListWishPaginationResponse.class);
        Assertions.assertTrue(response2.isSuccess());

        ListWishPaginationResponse data2 = response2.getData();

        PaginationResponse info2 = data2.getInfo();
        Assertions.assertEquals(1, info2.getPrevPageNo());
        Assertions.assertEquals(4, info2.getTotalData());
        Assertions.assertEquals(2, info2.getTotalPage());

        List<CreateWishResponse> wishes2 = data2.getWishes();
        Assertions.assertEquals(2, wishes2.size());
        Assertions.assertEquals("Fauzy Iskandar", wishes2.get(0).getName());
        Assertions.assertEquals("Silvi Absharina", wishes2.get(1).getName());
    }

    @Test
    public void createUserWish_success() throws Exception {
        Users user = getUser();

        CreateWishRequest request = new CreateWishRequest();
        request.setUserId(user.getId().toString());
        request.setWish("Hope you well");

        ApiResponse<CreateWishResponse> response = post("/wish/create", request, CreateWishResponse.class);
        CreateWishResponse data = response.getData();

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(data.getTitle(), "Mr.");
        Assertions.assertEquals(data.getName(), "Firza Gustama");
        Assertions.assertEquals(data.getWish(), "Hope you well");
    }
}
