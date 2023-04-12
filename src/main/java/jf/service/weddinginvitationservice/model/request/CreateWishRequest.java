package jf.service.weddinginvitationservice.model.request;

public class CreateWishRequest extends BaseRequest {
    private String userId;
    private String wish;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }
}
