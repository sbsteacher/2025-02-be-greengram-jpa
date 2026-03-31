package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserFollow extends CreatedAt {
    @EmbeddedId
    private UserFollowIds ids;

    @ManyToOne
    @JoinColumn(name="from_user_id") //컬럼명
    @MapsId("fromUserId") //필드명
    private User fromUser;

    @ManyToOne
    @JoinColumn(name="to_user_id") //컬럼명
    @MapsId("toUserId") //필드명
    private User toUser;
}
