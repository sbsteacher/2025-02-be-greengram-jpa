package com.green.greengram.application.feedlike;

import com.green.greengram.application.feedlike.model.FeedLikeReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedLikeService {
    private final FeedLikeMapper feedLikeMapper;

    //좋아요 처리 true
    //좋아요 취소 false
    // feedId, userId를 알고 있다.

    //0. delete를 한다.
    //case 1. 영향받은 행이 1이다. return false;
    //case 2. 영향받은 행이 0이다. insert를 하고 return true;
    public boolean toggleFeedLike(FeedLikeReq req) {
        int delAffectedRows = feedLikeMapper.delete(req);
        if(delAffectedRows == 1) {
            return false;
        }
        feedLikeMapper.save(req);
        return true;
    }
}
