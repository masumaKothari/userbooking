package com.userbooking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserBooking {
    private final String userId;
    private final String hotelId;
    private final int numberOfNights;
    private final double totalPriceInUSD;

    @JsonCreator
    public UserBooking(@JsonProperty("userId") String userId,
                       @JsonProperty("hotelId") String hotelId,
                       @JsonProperty("numberOfNights") int numberOfNights,
                       @JsonProperty("totalPriceInUSD") double totalPriceInUSD) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.numberOfNights = numberOfNights;
        this.totalPriceInUSD = totalPriceInUSD;
    }
}
