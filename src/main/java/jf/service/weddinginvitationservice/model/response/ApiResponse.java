package jf.service.weddinginvitationservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jf.service.weddinginvitationservice.enums.ApiResponseCodes;
import jf.service.weddinginvitationservice.exception.ApiException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T extends BaseResponse>{
    private boolean success;
    private String responseCode;
    private String responseMessage;
    private T data;

    public ApiResponse() {}

    public ApiResponse(ApiResponseCodes apiResponseCodes) {
        this.success = false;
        this.responseCode = apiResponseCodes.getResponseCode();
        this.responseMessage = apiResponseCodes.getResponseMessage();
    }

    public ApiResponse(ApiException e) {
        this.success = false;
        this.responseCode = e.getErrorCode();
        this.responseMessage = e.getErrorMessage();
    }

    public ApiResponse(T data) {
        this.success = true;
        this.responseCode = ApiResponseCodes.SUCCESS.getResponseCode();
        this.responseMessage = ApiResponseCodes.SUCCESS.getResponseMessage();
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
