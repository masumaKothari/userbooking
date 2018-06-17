package com.userbooking.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.userbooking.exception.UserBookingException;
import com.userbooking.service.UserBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/userBooking")
@Slf4j
public class UserBookingController {

    private UserBookingService userBookingService;
    private ObjectMapper objectMapper;
    @Autowired
    public UserBookingController(UserBookingService service, ObjectMapper objectMapper) {
        this.userBookingService = service;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value="/{userId}/numberOfBookings",
            method = RequestMethod.GET,
            produces ="application/json")
    ResponseEntity<String> numberOfBookings(@PathVariable String userId) {
        try {
            log.info(String.format("Number of bookings request for userId:%s ",userId));
            Map<String,Object> answer = ImmutableMap.of("userId",userId, "numberOfBookings",userBookingService.numberOfBookings(userId));
            String stringifiedAnswer = objectMapper.writeValueAsString(answer);
            log.info(String.format("Number of bookings response for userId:%s is \n %s", userId, stringifiedAnswer));
            return ResponseEntity.ok(stringifiedAnswer);
        } catch (UserBookingException ex) {
            log.info(String.format("Number of bookings response for userId:%s is \n %s", userId, ex.toResponseEntity(userId)));
            return ex.toResponseEntity(userId);
        } catch (Exception e) {
            log.error("Internal Server Error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value="/{userId}/totalBookingValue",
            method = RequestMethod.GET,
            produces ="application/json")
    ResponseEntity<String> totalBookingValue(@PathVariable String userId) {
        try {
            log.info(String.format("Total booking value request for userId:%s ",userId));
            Map<String,Object> answer = ImmutableMap.of("userId",userId, "totalBookingValue",userBookingService.totalBookingValue(userId));
            String stringifiedAnswer = objectMapper.writeValueAsString(answer);
            log.info(String.format("Total booking value response for userId:%s is \n %s", userId, stringifiedAnswer));
            return ResponseEntity.ok(stringifiedAnswer);
        } catch (UserBookingException ex) {
            log.info(String.format("Total booking value response for userId:%s is \n %s", userId, ex.toResponseEntity(userId)));
            return ex.toResponseEntity(userId);
        } catch (Exception e) {
            log.error("Internal Server Error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value="/{userId}/averageLengthOfStay",
            method = RequestMethod.GET,
            produces ="application/json")
    ResponseEntity<String> averageLengthOfStay(@PathVariable String userId) {
        try {
            log.info(String.format("Average length of stay request for userId:%s ",userId));
            Map<String,Object> answer = ImmutableMap.of("userId",userId, "averageLengthOfStay",userBookingService.averageLengthOfStay(userId));
            String stringifiedAnswer = objectMapper.writeValueAsString(answer);
            log.info(String.format("Average length of stay response for userId:%s is \n %s", userId, stringifiedAnswer));
            return ResponseEntity.ok(stringifiedAnswer);
        } catch (UserBookingException ex) {
            log.info(String.format("Average length of stay response for userId:%s is \n %s", userId, ex.toResponseEntity(userId)));
            return ex.toResponseEntity(userId);
        } catch (Exception e) {
            log.error("Internal Server Error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
