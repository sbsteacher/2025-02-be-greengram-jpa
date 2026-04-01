package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    //참조 당하는 쪽은 항상 One, mappedBy는 상대방쪽에서 나를 참조하는 필드명
    //cascade All은 Feed가 삭제되면 참조하는 쪽도 삭제처리
    //orphanRmoval은 자식이 고아가 되면 해당 자식은 삭제처리, 즉, pics에서 특정 객체 삭제하면 그 객체와 맵핑되어있는 row가 삭제
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedPic> pics = new ArrayList<>();

    //댓글
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedComment> comments = new ArrayList<>();

    //좋아요
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedLike> likes = new ArrayList<>();
}
