package com.green.greengram.application.feedcomment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedCommentPostReq {
    private long id;
    private long signedUserId;
    private long feedId;
    private String comment;
}
