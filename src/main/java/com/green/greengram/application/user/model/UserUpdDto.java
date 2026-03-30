package com.green.greengram.application.user.model;

import lombok.Builder;
import lombok.Getter;

//DTO: Data Transfer Object
@Getter
@Builder
public class UserUpdDto {
    private long id; //필수
    private String upw;
    private String nm;
    private String pic;
}
