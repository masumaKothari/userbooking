package com.userbooking.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.fail;

public class UserBookingServiceTest {

    UserBookingService userBookingService = new UserBookingService(new ObjectMapper());


    @Test
    public void testInvalidUserFeatures() {
        ReflectionTestUtils.setField(userBookingService, "userDataFileLocation", "classpath:data/invalidUserFeatures.txt");
        try {
            userBookingService.loadUserBookingData();
            fail("Expected to fail loading data");
        } catch (Exception ex) {
            //expected
        }

    }
}
