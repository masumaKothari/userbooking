package com.userbooking.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.userbooking.model.UserBooking;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class FileUtilsTest {
    @Test
    public void testGetInputStreamFromHttp() throws IOException {
        String filePath = "https://raw.githubusercontent.com/geraintluff/json-model/master/tests/draft-04-schema.json";
        JsonNode node = new ObjectMapper().readTree(FileUtils.getInputStream(filePath));
        assertEquals("http://json-schema.org/draft-04/schema#", node.get("$schema").asText());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInputStreamFromFileFails() throws IOException {
        String filePath = "file:///c:/nonExisting.json";
        getFileContentAsString(filePath);
    }
    @Test
    public void testGetInputStreamFromFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data/userFeaturesSingle.json").getFile());
        String filePath = "file:///" + file.getAbsolutePath().replaceAll("\\\\", "/");
        UserBooking userBooking = new ObjectMapper().readValue(getFileContentAsString(filePath), UserBooking.class);
        commonUserBookingAsserts(userBooking);
    }

    @Test
    public void testGetInputStreamWithoutAnyProtocolPrefix() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data/userFeaturesSingle.json").getFile());
        String filePath = file.getAbsolutePath().replaceAll("\\\\", "/");
        UserBooking userBooking = new ObjectMapper().readValue(getFileContentAsString(filePath), UserBooking.class);
        commonUserBookingAsserts(userBooking);
    }

    @Test
    public void testGetInputStreamFromClasspath() throws IOException {
        UserBooking userBooking = new ObjectMapper().readValue(getFileContentAsString("classpath:data/userFeaturesSingle.json"), UserBooking.class);
        commonUserBookingAsserts(userBooking);
    }

    public String getFileContentAsString(String filePath) throws IOException {
        try (InputStream is = FileUtils.getInputStream(filePath)) {
            return new String(ByteStreams.toByteArray(is));
        }
    }

    public void commonUserBookingAsserts(UserBooking userBooking) {
        assertEquals("3", userBooking.getUserId());
        assertEquals("19", userBooking.getHotelId());
        assertEquals(8, userBooking.getNumberOfNights());
        assertEquals(456, userBooking.getTotalPriceInUSD(), 0);
    }
}
