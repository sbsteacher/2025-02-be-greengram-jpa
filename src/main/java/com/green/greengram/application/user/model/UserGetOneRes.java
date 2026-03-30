package com.green.greengram.application.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserGetOneRes {
    private long id;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
    private String createdAt;
}
