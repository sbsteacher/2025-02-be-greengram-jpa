package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name="uk_feed_pic", columnNames = {"feed_id", "pic"})
        }
)
public class FeedPic {
    @Id //대리키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name="feed_id", nullable = false)
    private Feed feed;

    @Column(length = 50, nullable = false)
    private String pic;

    public FeedPic(Feed feed, String pic) {
        this.feed = feed;
        this.pic = pic;
    }
}
