package jf.service.weddinginvitationservice.exception;

import jf.service.weddinginvitationservice.enums.ApiResponseCodes;

public class ApiException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public ApiException(ApiResponseCodes apiResponseCodes) {
        this.errorCode = apiResponseCodes.getResponseCode();
        this.errorMessage = apiResponseCodes.getResponseMessage();
    }

    public ApiException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
