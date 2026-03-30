package com.green.greengram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

/* 이 클래스의 목적:
    매번 테이블을 만들 때마다 created_at 컬럼을 작성하는게 아니라 이 클래스를
    상속처리로 creatd_at 속성을 가질 수 있도록 처리하기 위함.
*/

@Getter
@Setter
@MappedSuperclass //Entity 부모역할
@EntityListeners(AuditingEntityListener.class) //MySQL로 치면 CurrentTimestamp 역할
public class CreatedAt {
    @CreatedDate //insert시 현재일시값이 삽입된다.
    @Column(nullable = false) //컬럼의 속성값을 줄 때 사용. 지금은 NOT NULL 속성을 추가함.
    //타입, 이름으로 컬럼이 된다. LocalDateTime > DATETIME, createdAt > created_at
    private LocalDateTime createdAt;
}
