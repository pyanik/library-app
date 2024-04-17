package com.app.library.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

@UtilityClass
public class TestControllerUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    public static String getUrl(String path, int port) {
        return "http://localhost:" + port + path;
    }

    @SneakyThrows
    public static  <T> T mapResponse(Class<T> responseType, MvcResult result) {
        if (Void.class.equals(responseType)) {
            return null;
        }
        String contentAsString = result.getResponse()
                .getContentAsString();
        if (String.class.equals(responseType)) {
            return (T) contentAsString;
        }
        return Optional.of(contentAsString)
                .filter(StringUtils::isNotBlank)
                .map(content -> readValue(responseType, content))
                .orElse(null);
    }

    @SneakyThrows
    private <T> T readValue(Class<T> responseType, String content) {
        return objectMapper.readValue(content, responseType);
    }

    @SneakyThrows
    public static  <T> T mapResponse(TypeReference<T> responseType, MvcResult result) {
        return Optional.of(result.getResponse()
                .getContentAsString())
                .filter(StringUtils::isNotBlank)
                .map(content -> readValue(responseType, content))
                .orElse(null);
    }

    @SneakyThrows
    private <T> T readValue(TypeReference<T> responseType, String content) {
        return objectMapper.readValue(content, responseType);
    }

    @SneakyThrows
    public static  <R> String getContent(R requestBody) {
        return objectMapper.writeValueAsString(requestBody);
    }
}
