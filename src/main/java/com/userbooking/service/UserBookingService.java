package com.userbooking.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.userbooking.exception.UserNotFoundException;
import com.userbooking.model.UserBooking;
import com.userbooking.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserBookingService {

    @Value("${userbooking.data.file.location}")
    private String userDataFileLocation;
    private List<UserBooking> userBookings = new ArrayList<>();
    private ObjectMapper objectMapper;

    @Autowired
    public UserBookingService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public long numberOfBookings(String userId) {
        return userBookingsFor(userId).stream().count();
    }

    public long totalBookingValue(String userId) {
        return userBookingsFor(userId).stream().mapToInt(userBooking -> userBooking.getTotalPriceInUSD()).sum();
    }

    public OptionalDouble averageLengthOfStay(String userId) {
        return userBookingsFor(userId).stream().mapToInt(userBooking -> userBooking.getNumberOfNights()).average();
    }

    public List<UserBooking> userBookingsFor(String userId) {
        List<UserBooking> result = userBookings.stream().filter(userBooking -> userBooking.getUserId().equalsIgnoreCase(userId)).collect(Collectors.toList());
        if (result.size() <= 0) {
            throw new UserNotFoundException(userId);
        }
        return result;
    }

    @PostConstruct
    @VisibleForTesting
    private void loadUserBookingData()  {
        try {
            userBookings.clear();
            Preconditions.checkNotNull(userDataFileLocation);
            MappingIterator<UserBooking> iterator = objectMapper.readerFor(UserBooking.class).readValues(FileUtils.getInputStream(userDataFileLocation));
            while(iterator.hasNext()) {
                userBookings.add(iterator.next());
            }
        } catch (Exception ex) {
            log.error(String.format("Check the user info data file at location {%s}: ", userDataFileLocation), ex);
            throw new IllegalStateException(ex);
        }
    }
}
