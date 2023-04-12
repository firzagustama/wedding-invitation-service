package jf.service.weddinginvitationservice.enums;

public enum ApiResponseCodes {
    SUCCESS("1000", "Success"),
    PARAM_ILLEGAL("2000", "Parameter Illegal"),
    USER_NOT_FOUND("3000", "User not found"),
    INTERNAL_ERROR("5000", "Internal Error");
    private String responseCode;
    private String responseMessage;
    ApiResponseCodes(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
    public String getResponseCode() {
        return responseCode;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
}
