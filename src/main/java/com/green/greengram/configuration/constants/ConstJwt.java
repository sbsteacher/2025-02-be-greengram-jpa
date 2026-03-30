package com.green.greengram.configuration.constants;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix="constants.jwt") //빈등록
@ToString
public class ConstJwt {
    private final String issuer;
    private final String bearerFormat;
    private final String claimKey;
    private final String secretKey;
    private final String accessTokenCookieName;
    private final String accessTokenCookiePath;
    private final int accessTokenCookieValiditySeconds;
    private final long accessTokenValidityMilliseconds;
    private final String refreshTokenCookieName;
    private final String refreshTokenCookiePath;
    private final int refreshTokenCookieValiditySeconds;
    private final long refreshTokenValidityMilliseconds;

    public ConstJwt(String issuer, String bearerFormat, String claimKey, String secretKey, String accessTokenCookieName, String accessTokenCookiePath, int accessTokenCookieValiditySeconds, long accessTokenValidityMilliseconds, String refreshTokenCookieName, String refreshTokenCookiePath, int refreshTokenCookieValiditySeconds, long refreshTokenValidityMilliseconds) {
        this.issuer = issuer;
        this.bearerFormat = bearerFormat;
        this.claimKey = claimKey;
        this.secretKey = secretKey;
        this.accessTokenCookieName = accessTokenCookieName;
        this.accessTokenCookiePath = accessTokenCookiePath;
        this.accessTokenCookieValiditySeconds = accessTokenCookieValiditySeconds - 2; //Cookie에서 받아온 값을 처리 중에 토큰 만료가 될 수 있기 때문에 2초 정도 먼저 cookie에서 사라지도록 처리
        this.accessTokenValidityMilliseconds = accessTokenValidityMilliseconds;
        this.refreshTokenCookieName = refreshTokenCookieName;
        this.refreshTokenCookiePath = refreshTokenCookiePath;
        this.refreshTokenCookieValiditySeconds = refreshTokenCookieValiditySeconds - 2; //Cookie에서 받아온 값을 처리 중에 토큰 만료가 될 수 있기 때문에 2초 정도 먼저 cookie에서 사라지도록 처리
        this.refreshTokenValidityMilliseconds = refreshTokenValidityMilliseconds;
    }
}
