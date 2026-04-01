package com.green.greengram.application.userfollow;

import com.green.greengram.entity.UserFollow;
import com.green.greengram.entity.UserFollowIds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<UserFollow, UserFollowIds> {
}
