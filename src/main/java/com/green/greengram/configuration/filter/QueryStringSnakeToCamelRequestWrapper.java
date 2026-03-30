package com.green.greengram.configuration.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryStringSnakeToCamelRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String[]> convertedParams = new HashMap<>();
    // 스네이크 케이스 패턴: _ 뒤에 소문자가 오는 경우를 찾음
    private static final Pattern SNAKE_PATTERN = Pattern.compile("_([a-z])");

    public QueryStringSnakeToCamelRequestWrapper(HttpServletRequest request) {
        super(request);
        Map<String, String[]> originalParams = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : originalParams.entrySet()) {
            String camelKey = convertToCamelCase(entry.getKey());
            convertedParams.put(camelKey, entry.getValue());
        }
    }

    private String convertToCamelCase(String input) {
        if (input == null || !input.contains("_")) {
            return input;
        }

        // Java 9+ 방식: 정규식 매칭 결과를 Lambda로 처리
        Matcher matcher = SNAKE_PATTERN.matcher(input.toLowerCase());
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            // 그룹 1(소문자)을 대문자로 변환하여 치환
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    @Override
    public String getParameter(String name) {
        String[] values = convertedParams.get(name);
        return (values != null && values.length > 0) ? values[0] : null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(convertedParams);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(convertedParams.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return convertedParams.get(name);
    }
}
