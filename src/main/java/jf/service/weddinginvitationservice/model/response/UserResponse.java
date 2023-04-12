package jf.service.weddinginvitationservice.model.response;

public class UserResponse extends BaseResponse {
    private String title;
    private String name;
    private Boolean attending;

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

    public Boolean getAttending() {
        return attending;
    }

    public void setAttending(Boolean attending) {
        this.attending = attending;
    }
}
