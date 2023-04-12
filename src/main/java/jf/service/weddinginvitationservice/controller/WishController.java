package jf.service.weddinginvitationservice.controller;

import jf.service.weddinginvitationservice.model.request.ApiRequest;
import jf.service.weddinginvitationservice.model.request.CreateWishRequest;
import jf.service.weddinginvitationservice.model.request.ListWishPaginationRequest;
import jf.service.weddinginvitationservice.model.response.ApiResponse;
import jf.service.weddinginvitationservice.model.response.ListWishPaginationResponse;
import jf.service.weddinginvitationservice.model.response.CreateWishResponse;
import jf.service.weddinginvitationservice.service.UserWishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wish")
public class WishController {
    @Autowired
    private UserWishService userWishService;
    @PostMapping("/create")
    public ApiResponse<CreateWishResponse> createUserWish(@RequestBody ApiRequest<CreateWishRequest> request) {
        CreateWishResponse response = userWishService.createUserWish(request.getData());
        return new ApiResponse<>(response);
    }

    @PostMapping("/getList")
    public ApiResponse<ListWishPaginationResponse> listUserWish(@RequestBody ApiRequest<ListWishPaginationRequest> request) {
        ListWishPaginationResponse response = userWishService.getListUserWish(request.getData());
        return new ApiResponse<>(response);
    }

}
