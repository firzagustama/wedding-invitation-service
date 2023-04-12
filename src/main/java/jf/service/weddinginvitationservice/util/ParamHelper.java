package jf.service.weddinginvitationservice.util;

import jf.service.weddinginvitationservice.enums.ApiResponseCodes;
import jf.service.weddinginvitationservice.exception.ApiException;

import java.util.UUID;

public class ParamHelper {
    public static void paramNotNull(UUID field, String fieldName) {
        if (field == null) {
            throw new ApiException(ApiResponseCodes.PARAM_ILLEGAL.getResponseCode(), fieldName + " can't be null");
        }
    }

    public static void paramNotNull(Object field, String fieldName) {
        if (field == null) {
            throw new ApiException(ApiResponseCodes.PARAM_ILLEGAL.getResponseCode(), fieldName + " can't be null");
        }
    }

    public static void paramNotNullOrBlank(String field, String fieldName) {
        if (field == null || field.isEmpty() || field.isBlank()) {
            throw new ApiException(ApiResponseCodes.PARAM_ILLEGAL.getResponseCode(), fieldName + " can't be null or blank");
        }
    }

    public static void paramLength(String field, int min, int max, String fieldName) {
        if (field.length() < min || field.length() > max) {
            throw new ApiException(ApiResponseCodes.PARAM_ILLEGAL.getResponseCode(), fieldName + " length must between " + min + " to " + max);
        }
    }

    public static void paramBetween(int field, int min, int max, String fieldName) {
        if (field < min || field > max) {
            throw new ApiException(ApiResponseCodes.PARAM_ILLEGAL.getResponseCode(), fieldName + " must be between " + min + " to " + max);
        }
    }
}
