package com.userbooking.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userbooking.service.UserBookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class UserBookingControllerTest {

    @Value("${local.server.port}")
    protected int port;
    protected final static String USER_BOOKING_MAPPING = "/userBooking";
    protected final static RestTemplate REST_TEMPLATE = new RestTemplate();

    @Autowired
    UserBookingService userBookingService;

    @Autowired
    ObjectMapper objectMapper;

    protected String uriBuilder(String... paths) {
        String uri = "http://localhost:" + port + USER_BOOKING_MAPPING;
        for (String path : paths) {
            uri += "/" + path;
        }
        return uri;
    }

    @Test
    public void testGetNumberOfBookings() throws IOException {
        ResponseEntity<String> result = REST_TEMPLATE.getForEntity(uriBuilder("numberOfBookings", "5"), String.class);
        System.out.println(result.getBody());
        Map<String,Object> responseMap = objectMapper.readValue(result.getBody(), new TypeReference<Map< String, Object >> () {});
        assertEquals("5", responseMap.get("userId"));
        assertEquals(8, responseMap.get("numberOfBookings"));
    }

    @Test
    public void testAverageLengthOfState() throws IOException {
        ResponseEntity<String> result = REST_TEMPLATE.getForEntity(uriBuilder("averageLengthOfStay", "5"), String.class);
        System.out.println(result.getBody());
        Map<String,Object> responseMap = objectMapper.readValue(result.getBody(), new TypeReference<Map< String, Object >> () {});
        assertEquals("5", responseMap.get("userId"));
        assertEquals(7.25, responseMap.get("averageLengthOfStay"));
    }
    @Test
    public void testTotalBookingValue() throws IOException {
        ResponseEntity<String> result = REST_TEMPLATE.getForEntity(uriBuilder("totalBookingValue", "5"), String.class);
        System.out.println(result.getBody());
        Map<String,Object> responseMap = objectMapper.readValue(result.getBody(), new TypeReference<Map< String, Object >> () {});
        assertEquals("5", responseMap.get("userId"));
        assertEquals(4102, responseMap.get("totalBookingValue"));
    }

    @Test
    public void testUserNotFound() throws IOException {
        try {
            REST_TEMPLATE.getForEntity(uriBuilder("averageLengthOfStay", "789"), String.class);
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            Map<String, Object> responseMap = objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Map<String, Object>>() {});
            System.out.println(ex.getResponseBodyAsString());
            assertEquals("789", responseMap.get("userId"));
            assertEquals("User not found.", responseMap.get("detail"));
        }

    }

}
