package com.green.greengram.application.user;

import com.green.greengram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//< 어떤 엔터티랑 연결하냐, 그 엔터티의 PK타입이 무엇이냐? >
public interface UserRepository extends JpaRepository<User, Long> {
}
