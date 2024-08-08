package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

public class TestUtils {

    public static JsonNode decodeBase64TokenSectionAndMapToJson(String tokenSection)
            throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getDecoder();
        String base64AsStr = new String(decoder.decode(tokenSection));

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readTree(base64AsStr);
    }
}
