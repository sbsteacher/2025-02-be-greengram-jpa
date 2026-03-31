package com.green.greengram.application.feedlike.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedLikeReq {
    private long feedId;
    private long signedUserId;
}
