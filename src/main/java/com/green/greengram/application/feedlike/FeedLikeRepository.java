package com.green.greengram.application.feedlike;

import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedLike;
import com.green.greengram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {
    FeedLike findByFeedAndUser(Feed feed, User user);
}
