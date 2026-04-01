package com.green.greengram.application.feedlike;

import com.green.greengram.application.feedlike.model.FeedLikeReq;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedLike;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedLikeService {
    private final FeedLikeMapper feedLikeMapper;
    private final FeedLikeRepository feedLikeRepository;
    //좋아요 처리 true
    //좋아요 취소 false
    // feedId, userId를 알고 있다.

    //0. delete를 한다.
    //case 1. 영향받은 행이 1이다. return false;
    //case 2. 영향받은 행이 0이다. insert를 하고 return true;
    public boolean toggleFeedLike(FeedLikeReq req) {
//        int delAffectedRows = feedLikeMapper.delete(req);
//        if(delAffectedRows == 1) {
//            return false;
//        }
//        feedLikeMapper.save(req);
//        return true;
        Feed feed = new Feed();
        feed.setId( req.getFeedId() );
        User user = new User();
        user.setId( req.getSignedUserId() );
        FeedLike feedLike = feedLikeRepository.findByFeedAndUser(feed, user);

        if(feedLike == null) { //좋아요 처리를 해야 한다.
            //save
            FeedLike newFeedLike = new FeedLike(feed, user);
            feedLikeRepository.save(newFeedLike);
            return true;
        }  //이미 좋아요가 되어 있었다. 좋아요 취소를 해야 한다.

        feedLikeRepository.delete(feedLike);
        return false;
    }
}
