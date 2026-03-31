package com.green.greengram.application.userfollow.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserFollowReq {
    private long fromUserId;
    private long toUserId;
}
