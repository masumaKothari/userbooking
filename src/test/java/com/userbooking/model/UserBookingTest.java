package com.userbooking.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserBookingTest {

    @Test
    public void testUserBookingConstructor() {
        UserBooking userBooking = new UserBooking("1", "2", 5, 3444);
        assertEquals("1", userBooking.getUserId());
        assertEquals("2", userBooking.getHotelId());
        assertEquals(5, userBooking.getNumberOfNights());
        assertEquals(3444, userBooking.getTotalPriceInUSD(), 0);
    }
}
