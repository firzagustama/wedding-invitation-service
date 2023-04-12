package jf.service.weddinginvitationservice.model.response;

import java.util.Date;

public class CreateWishResponse extends BaseResponse {
    private String title;
    private String name;
    private String wish;

    private Date createdDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }
}
