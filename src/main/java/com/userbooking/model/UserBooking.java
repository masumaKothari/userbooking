package com.userbooking.model;

import lombok.Getter;

@Getter
public class UserBooking {
    private String userId;
    private String hotelId;
    private int numberOfNights;
    private int totalPriceInUSD;
}
