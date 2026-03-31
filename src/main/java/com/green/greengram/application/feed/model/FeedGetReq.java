package com.green.greengram.application.feed.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedGetReq {
    private int page;
    private int size;
    private Long profileUserId;
    private long signedUserId;
    private int startIdx;

    public FeedGetReq(int page, int size, Long profileUserId) {
        this.page = page;
        this.size = size;
        this.profileUserId = profileUserId;
        this.startIdx = (page - 1) * size;
    }

    public void setSignedUserId(long signedUserId) {
        this.signedUserId = signedUserId;
    }
}
