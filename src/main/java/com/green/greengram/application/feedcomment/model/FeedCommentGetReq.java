package com.green.greengram.application.feedcomment.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FeedCommentGetReq {
    @Positive
    private int page;
    @Positive
    private long feedId;
    private int startIdx;
    @Min(20)
    private int size;

    public FeedCommentGetReq(int page, long feedId, int size) {
        this.page = page;
        this.feedId = feedId;
        this.size = size;
        this.startIdx = (page - 1) * size;
    }
}
