package com.green.greengram.application.feedcomment;

import com.green.greengram.application.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int save(FeedCommentPostReq req);
    List<FeedCommentGetRes> findAll(FeedCommentGetReq req);
    int delete(FeedCommentDeleteReq req);
}
