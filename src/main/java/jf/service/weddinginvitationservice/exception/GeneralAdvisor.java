package jf.service.weddinginvitationservice.exception;

import jf.service.weddinginvitationservice.enums.ApiResponseCodes;
import jf.service.weddinginvitationservice.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralAdvisor {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        e.printStackTrace();

        ApiResponse response = new ApiResponse(ApiResponseCodes.INTERNAL_ERROR);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiResponse> handleApiException(ApiException e) {
        ApiResponse response = new ApiResponse(e);
        return ResponseEntity.ok(response);
    }
}
