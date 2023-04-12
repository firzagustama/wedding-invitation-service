package jf.service.weddinginvitationservice.model.request;

public class CreateRsvpRequest extends BaseRequest {
    private String userId;
    private Boolean rsvp;
    private int pax;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean isRsvp() {
        return rsvp;
    }

    public void setRsvp(Boolean rsvp) {
        this.rsvp = rsvp;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }
}
