package com.green.greengram.configuration.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
/* 시큐리티 필터에서 발생되는 예외는 GlobalException이 잡지 못 한다.
JwtAuthenticationEntryPoint가 시큐리티쪽에서 발생하는 예외를
GlobalException에 전달하는 역할

TokenAuthenticationFilter에서 authorization
 */

@Component //빈등록
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    //HandlerExceptionResolver 타입으로 만들어진 객체가 2개 이상
    //그 중 원하는 객체의 주소값을 DI 받기 위해 @Qualifier으로 ID값을 작성하면 특정 객체의 주소값 1개가 DI된다.
    public JwtAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        resolver.resolveException(request, response, null, (Exception)request.getAttribute("exception"));
    }
}
