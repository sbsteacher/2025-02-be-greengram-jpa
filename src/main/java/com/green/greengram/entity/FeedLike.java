package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        indexes = {
                //@Index(name = "idx_feed_user", columnList="feed_id, user_id"), //복합인덱스
                //@Index(name="idx_feed", columnList = "feed_id") //인덱스
        },
        uniqueConstraints = {
                @UniqueConstraint(name="uk_feed_user", columnNames = {"feed_id", "user_id"})
        }
)
public class FeedLike extends CreatedAt {
    @Id //대리키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name="feed_id", nullable = false)
    private Feed feed;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public FeedLike(Feed feed, User user) {
        this.feed = feed;
        this.user = user;
    }
}
