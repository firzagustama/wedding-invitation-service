package jf.service.weddinginvitationservice.model.request;

import com.google.gson.Gson;

public class ApiRequest<T extends BaseRequest> {
    private T data;

    public ApiRequest() {
    }

    public ApiRequest(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
