package com.green.greengram.application.userfollow;

import com.green.greengram.application.userfollow.model.UserFollowReq;
import com.green.greengram.entity.User;
import com.green.greengram.entity.UserFollow;
import com.green.greengram.entity.UserFollowIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFollowService {
    private final UserFollowMapper userFollowMapper;
    private final UserFollowRepository userFollowRepository;

    public int postUserFollow(UserFollowReq req) {
        //return userFollowMapper.save(req);
        UserFollowIds ids = new UserFollowIds();
        ids.setFromUserId( req.getFromUserId() );
        ids.setToUserId( req.getToUserId() );

        User fromUser = new User();
        fromUser.setId( req.getFromUserId() );

        User toUser = new User();
        toUser.setId( req.getToUserId() );

        UserFollow newUserFollow = new UserFollow();
        newUserFollow.setIds(ids);
        newUserFollow.setFromUser(fromUser);
        newUserFollow.setToUser(toUser);

        userFollowRepository.save(newUserFollow);

        return 1;
    }

    public int deleteUserFollow(UserFollowReq req) {
        //return userFollowMapper.delete(req);

        return 1;
    }
}
