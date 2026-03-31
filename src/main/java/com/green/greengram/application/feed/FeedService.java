package com.green.greengram.application.feed;

import com.green.greengram.application.feed.model.*;
import com.green.greengram.configuration.util.ImgUploadManager;
import com.green.greengram.configuration.util.MyFileUtil;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedPic;
import com.green.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository; //DI
    private final FeedPicRepository feedPicRepository;
    private final FeedMapper feedMapper;
    private final ImgUploadManager imgUploadManager;
    private final MyFileUtil myFileUtil;

    @Transactional
    public FeedPostRes postFeed(FeedPostReq req, List<MultipartFile> pics) {
        User signedUser = new User();
        signedUser.setId(req.getSignedUserId());

        Feed newFeed = new Feed();
        newFeed.setContents( req.getContents() );
        newFeed.setLocation( req.getLocation() );
        newFeed.setWriterUser( signedUser );
        //이 시점까지는 newFeed는 영속성이 없음

        feedRepository.save( newFeed );
        //newFeed는 영속성이 생긴다.

        //int saveAffectedRows = feedMapper.save(req);

        //save이후에 방금 insert한 feed테이블의 id값이 필요해요.
        //long feedId = req.getFeedId();
        long feedId = newFeed.getId();
        log.info("feedId: {}", feedId);

        //saveFeedPics메소드 호출하고 싶다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //picSavedNames 무엇이 저장되어 있나? 이미지 파일 이름?
        List<String> picSavedNames = imgUploadManager.saveFeedPics(feedId, pics);

        try {
            //feedMapper.savePics(feedId, picSavedNames);
            for(String pic : picSavedNames) {
                FeedPic newFeedPic = new FeedPic(newFeed, pic);
                feedPicRepository.save( newFeedPic );
            }
        } catch (Exception e) {
            //사진을 지운다.
            String directoryPath = String.format("%s/feed/%d", myFileUtil.fileUploadPath, feedId);
            log.info("directoryPath: {}", directoryPath);
            myFileUtil.deleteDirectory(directoryPath);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "SQL Syntax Error 발생");
        }
        return new FeedPostRes(feedId, picSavedNames);
    }

    public List<FeedGetRes> getFeedList(FeedGetReq req) {
        List<FeedGetRes> list = feedMapper.findAll(req);
        //작업!!! 피드 당 사진 정보를 가져오는 작업을 해야한다.
        for(FeedGetRes res : list) {
            //사진 가져오는 select
            List<String> pics = feedMapper.findPicsById(res.getId());
            res.setPics(pics);
        }
        return list;
    }


    public List<FeedGetRes> getFeedList2(FeedGetReq req) {
        return feedMapper.findAllResultMap(req);
    }

    @Transactional
    public int deleteFeed(FeedDeleteReq req) {

        //feed_pic, feed_like, feed_comment에 feedId가 사용된 모든 row를 삭제
        feedMapper.deleteRef(req);

        //feed 테이블의 row는 가장 마지막에 삭제처리
        feedMapper.delete(req);

        // 폴더 째 삭제
        String delDirectoryPath = String.format("%s/feed/%d", myFileUtil.fileUploadPath, req.getFeedId());
        myFileUtil.deleteDirectory(delDirectoryPath);
        return 1;
    }
}
