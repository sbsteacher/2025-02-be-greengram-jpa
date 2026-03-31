package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // 테이블을 만든다.
@Getter
@Setter
@ToString
public class Feed extends CreatedUpdatedAt {
    @Id //대리키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(length = 50)
    private String location;

    @Column(length = 1_000)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User writerUser;
}
