package com.green.greengram.application.feedcomment;

import com.green.greengram.application.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedCommentService {
    private final FeedCommentMapper feedCommentMapper;

    public long postFeedComment(FeedCommentPostReq req) {
        feedCommentMapper.save(req);
        return req.getId();
    }

    public List<FeedCommentGetRes> getFeedCommentList(FeedCommentGetReq req) {
        List<FeedCommentGetRes> commentList = feedCommentMapper.findAll(req);
        return commentList;
    }

    public int deleteFeedComment(FeedCommentDeleteReq req) {
        return feedCommentMapper.delete(req);
    }
}
