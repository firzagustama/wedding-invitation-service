package jf.service.weddinginvitationservice.model.response;

import java.util.List;

public class ListWishPaginationResponse extends BaseResponse {
    private PaginationResponse info;
    private List<CreateWishResponse> wishes;

    public PaginationResponse getInfo() {
        return info;
    }

    public void setInfo(PaginationResponse info) {
        this.info = info;
    }

    public List<CreateWishResponse> getWishes() {
        return wishes;
    }

    public void setWishes(List<CreateWishResponse> wishes) {
        this.wishes = wishes;
    }
}

