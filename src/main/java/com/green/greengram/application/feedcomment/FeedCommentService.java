package com.green.greengram.application.feedcomment;

import com.green.greengram.application.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;
    private final FeedCommentMapper feedCommentMapper;

    public long postFeedComment(FeedCommentPostReq req) {
        User writerUser = new User();
        writerUser.setId( req.getSignedUserId() );

        Feed feed = new Feed();
        feed.setId( req.getFeedId() );

        FeedComment newFeedComment = new FeedComment();
        newFeedComment.setUser( writerUser );
        newFeedComment.setFeed( feed );
        newFeedComment.setComment( req.getComment() );

        feedCommentRepository.save( newFeedComment );
        return newFeedComment.getId();
    }

    public List<FeedCommentGetRes> getFeedCommentList(FeedCommentGetReq req) {
        List<FeedCommentGetRes> commentList = feedCommentMapper.findAll(req);
        return commentList;
    }

    public int deleteFeedComment(FeedCommentDeleteReq req) {
        return feedCommentMapper.delete(req);
    }
}
