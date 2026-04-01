package com.green.greengram.application.feed;

import com.green.greengram.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    //쿼리 메소드로 id, user_id를 where 조건으로 해서 1개의 row를 가져오고 싶다.
}
