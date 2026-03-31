package com.green.greengram.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/* 복합키를 구성하는 클래스
    Embeddable, EqualsAndHashCode, Serializable 필수
*/
@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class UserFollowIds implements Serializable {
    private Long fromUserId;
    private Long toUserId;
}
