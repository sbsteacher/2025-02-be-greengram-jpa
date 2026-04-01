package com.green.greengram.application.feedcomment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FeedCommentGetRes {
    @JsonProperty("feedCommentId")
    private long id;
    private long feedId;
    private String comment;
    private long writerUserId;
    @JsonProperty("writerNickName")
    private String writerNm;
    private String writerPic;
    private LocalDateTime createdAt;
}
