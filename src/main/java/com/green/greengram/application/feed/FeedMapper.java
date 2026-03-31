package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.FeedDeleteReq;
import com.green.greengram.application.feed.model.FeedGetReq;
import com.green.greengram.application.feed.model.FeedGetRes;
import com.green.greengram.application.feed.model.FeedPostReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedMapper {
    int save(FeedPostReq req);
    List<FeedGetRes> findAll(FeedGetReq req);
    List<FeedGetRes> findAllResultMap(FeedGetReq req);
    int delete(FeedDeleteReq req);
    int deleteRef(FeedDeleteReq req);

    int savePics(@Param("feedId") long feedId
               , @Param("picSavedNames") List<String> picSavedNames);

    List<String> findPicsById(long feedId);

}
