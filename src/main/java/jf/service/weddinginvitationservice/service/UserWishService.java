package jf.service.weddinginvitationservice.service;

import jf.service.weddinginvitationservice.model.request.CreateWishRequest;
import jf.service.weddinginvitationservice.model.request.ListWishPaginationRequest;
import jf.service.weddinginvitationservice.model.response.ListWishPaginationResponse;
import jf.service.weddinginvitationservice.model.response.CreateWishResponse;

public interface UserWishService {

    CreateWishResponse createUserWish(CreateWishRequest request);

    ListWishPaginationResponse getListUserWish(ListWishPaginationRequest request);
}
