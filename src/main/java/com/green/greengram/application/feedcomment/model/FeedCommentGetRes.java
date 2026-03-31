package com.green.greengram.application.feedcomment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCommentGetRes {
    @JsonProperty("feedCommentId")
    private long id;
    private long feedId;
    private String comment;
    private long writerUserId;
    @JsonProperty("writerNickName")
    private String writerNm;
    private String writerPic;
    private String createdAt;
}
