package com.green.greengram.configuration.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component //빈등록
public class MyCookieUtil {
    //(보안)쿠키 데이터를 담아라 Client한테 명령
    public void setCookie(HttpServletResponse res, String key, String value, int maxAge, String path) {
        Cookie cookie = new Cookie(key, value); //import jakarta.servlet.http.Cookie;
        cookie.setMaxAge(maxAge); //쿠키 내용이 살아있는 시간(초단위)
        cookie.setHttpOnly(true); //보안 쿠키 활성화, 브라우저의 JS가 접근할 수 없다.
        //path 설정을 하지 않으면 모든 요청마다 해당 쿠키가 서버쪽으로 넘어온다.
        //path 설정을 하면 그 url일 때만 해당 쿠키가 서버쪽으로 넘어온다.
        if(path != null) {
            cookie.setPath(path);
        }

        res.addCookie(cookie);
    }

    public String getValue(HttpServletRequest req, String key) {
        Cookie cookie = getCookie(req, key);
        return cookie == null ? null : cookie.getValue();
    }

    public Cookie getCookie(HttpServletRequest req, String key) {
        Cookie[] cookies = req.getCookies();
        if( cookies != null && cookies.length > 0 ) { //쿠키에 뭔가 담겨져 있다면
//            for( Cookie c : cookies ) {
//                if(c.getName().equals(key)) { //key이름으로 담겨진 쿠키가 있니?
//                    return c;
//                }
//            }
            for(int i=0; i<cookies.length; i++) {
                Cookie c = cookies[i];
                if(c.getName().equals(key)) { //key이름으로 담겨진 쿠키가 있니?
                    return c;
                }
            }
        }
        return null;
    }

    public void deleteCookie(HttpServletResponse res, String key, String path) {
        setCookie(res, key, null, 0, path);
    }
}
