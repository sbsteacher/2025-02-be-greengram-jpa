package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // 테이블을 만든다.
@Getter
@Setter
@ToString
public class FeedComment extends CreatedUpdatedAt {
    @Id //대리키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name="feed_id", nullable = false)
    private Feed feed;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(length = 1_000, nullable = false)
    private String comment;
}
