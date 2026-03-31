package com.green.greengram.application.feed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FeedGetRes {
    @JsonProperty("feedId") //JSON으로 변경될 때 key 값이 feedId로 변경된다.
    private long id;
    private String contents;
    private String location;
    private String createdAt;
    private long writerUserId;
    private String writerUid;
    private String writerNickName;
    private String writerPic;
    private int isLike; //내가 이 FEED를 좋아요를 했나? 했으면 1 아니면 0
    private int likeCount; //해당 피드에 좋아요 한 수
    private List<String> pics = new ArrayList<>();
    private int commentCount; //피드에 달린 댓글 수

    //private FeedCommentGetRes comments;
}
