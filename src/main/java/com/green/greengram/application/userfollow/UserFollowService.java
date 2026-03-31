package com.green.greengram.application.userfollow;

import com.green.greengram.application.userfollow.model.UserFollowReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFollowService {
    private final UserFollowMapper userFollowMapper;

    public int postUserFollow(UserFollowReq req) {
        return userFollowMapper.save(req);
    }

    public int deleteUserFollow(UserFollowReq req) {
        return userFollowMapper.delete(req);
    }
}
