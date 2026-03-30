package com.green.greengram.application.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserProfileGetReq {
    private long profileUserId; //프로파일 사용자 userId
    private long signedUserId; //로그인한 사용자 userId (현재는 필요없고, 나중에 필요, 팔로우/팔로잉 처리 시 필요)
}
