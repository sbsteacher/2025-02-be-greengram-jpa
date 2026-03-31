package com.green.greengram.application.feed.model;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class FeedPostReq {
    private String location;
    private String contents;
    private long signedUserId;
    private long feedId;

    public void setSignedUserId(long signedUserId) {
        this.signedUserId = signedUserId;
    }
}
