package com.green.greengram.configuration.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueryStringSnakeToCamelFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            // 커스텀 Wrapper로 감싸서 다음 필터/컨트롤러로 전달
            chain.doFilter(new QueryStringSnakeToCamelRequestWrapper((HttpServletRequest) request), response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
