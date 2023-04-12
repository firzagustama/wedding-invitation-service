package jf.service.weddinginvitationservice.model.request;

import com.google.gson.Gson;

public class BaseRequest {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
