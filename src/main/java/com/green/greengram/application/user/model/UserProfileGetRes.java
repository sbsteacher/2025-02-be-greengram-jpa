package com.green.greengram.application.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserProfileGetRes {
    @JsonProperty("userId")
    private long id;
    private String pic;
    private String createdAt;
    private String uid;
    @JsonProperty("nickName")
    private String nm;

    private int feedCount; //프로파일 유저가 등록한 피드 수

    //TODO: 아래는 향후 작업
    private int allFeedLikeCount; //나의 모든 피드에 좋아요 수

    private int followerCount; //profileUserId를 팔로우하는 사람들의 수
    private int followingCount; //profileUserId가 팔로우하는 사람들의 수

    private int followState; //팔로우 상태
}
