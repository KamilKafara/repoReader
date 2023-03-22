package com.kafarson.github.api;

import java.io.IOException;
import java.util.Map;

public interface FetchApi {

    <T> T fetchDataByType(String url, Map<String, String> headers, Class<T> target) throws IOException;

    String fetchData(String url, Map<String, String> header);
}
