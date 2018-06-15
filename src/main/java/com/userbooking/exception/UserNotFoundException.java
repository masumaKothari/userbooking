package com.userbooking.exception;


import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserBookingException {

    public UserNotFoundException(String userId) {
        super(String.format("User not found.", userId));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
