package com.green.greengram.application.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder //부분 속성에 데이터를 넣은 객체를 만들고 싶을 때 주로 사용하는 기법
public class UserSignInRes {
    private long signedUserId;
    private String nm;
    private String pic;
}
