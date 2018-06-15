package com.userbooking.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import org.junit.Test;

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

    public static String getFileContentAsString(String filePath) throws IOException {
        try (InputStream is = FileUtils.getInputStream(filePath)) {
            return new String(ByteStreams.toByteArray(is));
        }
    }
}
