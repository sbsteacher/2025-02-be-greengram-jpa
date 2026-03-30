package com.green.greengram.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

//JWT를 만들 때 payload에 담을 객체
@Getter
@AllArgsConstructor
public class JwtUser {
    private long signedUserId;
}
