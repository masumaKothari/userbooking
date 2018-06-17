package com.userbooking.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userbooking.service.UserBookingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserBookingControllerTest {
    private static final String MOCK_ERROR_MESSAGE = "mocking failure scenario for 500";
    @Mock
    private UserBookingService userBookingService;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private UserBookingController userBookingController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testNumberOfNightsFail() throws JsonProcessingException {
        mockUserBookingService();
        ResponseEntity<String> response = userBookingController.numberOfBookings("1");
        assertEquals(MOCK_ERROR_MESSAGE, response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void testTotalBookingValueFail() throws JsonProcessingException {
        mockUserBookingService();
        ResponseEntity<String> response = userBookingController.totalBookingValue("1");
        assertEquals(MOCK_ERROR_MESSAGE, response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void testAverageLengthOfStayFail() throws JsonProcessingException {
        mockUserBookingService();
        ResponseEntity<String> response = userBookingController.averageLengthOfStay("1");
        assertEquals(MOCK_ERROR_MESSAGE, response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    public void mockUserBookingService() {
        when(userBookingService.numberOfBookings("1")).thenThrow(new RuntimeException("mocking failure scenario for 500"));
        when(userBookingService.averageLengthOfStay("1")).thenThrow(new RuntimeException("mocking failure scenario for 500"));
        when(userBookingService.totalBookingValue("1")).thenThrow(new RuntimeException("mocking failure scenario for 500"));
    }
}
