package com.userbooking.exception;


import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public abstract class UserBookingException extends RuntimeException {

    public UserBookingException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();

    public Map<String, Object> getResponseBody(String userId) {
        Map<String, Object> response = Maps.newLinkedHashMap();
        response.put("userId", userId);
        response.put("detail", detail());
        return response;
    }

    protected String detail() {
        return getMessage();
    }

    public ResponseEntity toResponseEntity(String userId) {
        ResponseEntity response = new ResponseEntity(getResponseBody(userId), getHttpStatus());
        return response;
    }
}
