package com.green.greengram.application.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//회원가입 때 FE로 넘어오는 데이터를 담기위한 클래스
@Getter
@Setter
@ToString
public class UserSignUpReq {
    private long id;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
}
