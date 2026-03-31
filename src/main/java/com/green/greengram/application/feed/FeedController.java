package com.green.greengram.application.feed;


import com.green.greengram.application.feed.model.*;
import com.green.greengram.configuration.model.ResultResponse;
import com.green.greengram.configuration.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public ResultResponse<?> postFeed(@AuthenticationPrincipal UserPrincipal userPrincipal
                                    , @RequestPart FeedPostReq req
                                    , @RequestPart(name = "pic") List<MultipartFile> pics) {
        log.info("req: {}", req);
        log.info("pics.size(): {}", pics.size());
        req.setSignedUserId(userPrincipal.getSignedUserId());
        FeedPostRes res = feedService.postFeed(req, pics);
        return new ResultResponse<>("success", res);
    }

    @GetMapping
    public ResultResponse<?> getFeedList(@AuthenticationPrincipal UserPrincipal userPrincipal
                                       , @ModelAttribute FeedGetReq req ) {
        req.setSignedUserId( userPrincipal.getSignedUserId() );
        log.info( "req: {}", req );
        List<FeedGetRes> list = feedService.getFeedList(req);
        return new ResultResponse<>("success", list);
    }

    @DeleteMapping
    public ResultResponse<?> deleteFeed(@AuthenticationPrincipal UserPrincipal userPrincipal
                                      , @ModelAttribute FeedDeleteReq req) {
        req.setSignedUserId( userPrincipal.getSignedUserId() );
        log.info("req: {}", req);
        int result = feedService.deleteFeed(req);
        return new ResultResponse<>("success", result);
    }
}
