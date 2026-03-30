package com.green.greengram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CreatedUpdatedAt extends CreatedAt {
    @LastModifiedDate //insert, update 되었을 때 현재일시값을 넣는다.
    @Column(nullable = false) //컬럼의 속성값을 줄 때 사용. 지금은 NOT NULL 속성을 추가함.
    //타입, 이름으로 컬럼이 된다. LocalDateTime > DATETIME, updatedAt > updated_at
    private LocalDateTime updatedAt;
}
