package com.kafarson.github.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import com.kafarson.github.api.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Service
public class FetchImpl implements FetchApi {
    @Override
    public <T> T fetchDataByType(String url, Map<String, String> headers, Class<T> target) throws IOException {
        String data = fetchData(url, headers);
        final ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS)
                .enable(ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        return objectMapper.readValue(data, target);
    }

    @Override
    public String fetchData(String url, Map<String, String> headers) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            headers.forEach(httpURLConnection::setRequestProperty);
            httpURLConnection.setUseCaches(true);
            checkErrorCode(httpURLConnection);

            InputStream inputStream = httpURLConnection.getInputStream();
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SystemException("The server encountered an internal error and was unable to complete your request.",
                    "An unexpected error occurred while processing your request. Please try again later or contact support if the problem persists.",
                    Lists.newArrayList(new FieldInfo("internal", ErrorCode.SYSTEM_EXCEPTION)));
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    private static void checkErrorCode(HttpURLConnection httpURLConnection) throws IOException {
        int statusCode = httpURLConnection.getResponseCode();
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            throw new NotFoundException("The resource you are looking for could not be found. Please check the URL or try a different search term.",
                    Lists.newArrayList(new FieldInfo("attributes", ErrorCode.NOT_FOUND)));
        }
        if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
            throw new AuthenticationException("Access denied. You are not authorized to fetch this data. Please log in with appropriate credentials or contact the administrator.",
                    Lists.newArrayList(new FieldInfo("token", ErrorCode.UNAUTHORIZED)));
        }
    }
}
