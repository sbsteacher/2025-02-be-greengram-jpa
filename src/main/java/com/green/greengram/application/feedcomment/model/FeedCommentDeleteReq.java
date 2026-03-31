package com.green.greengram.application.feedcomment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedCommentDeleteReq {
    private long feedCommentId;
    private long signedUserId;
}
