package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // 테이블을 만든다.
@Getter
@Setter
public class User extends CreatedUpdatedAt {
    @Id //대리키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String uid;

    @Column(nullable = false, length = 100)
    private String upw;

    @Column(nullable = false, length = 10)
    private String nm;

    @Column(length = 50)
    private String pic;
}
