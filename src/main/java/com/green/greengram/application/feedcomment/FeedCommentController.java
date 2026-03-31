package com.green.greengram.application.feedcomment;

import com.green.greengram.application.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetReq;
import com.green.greengram.application.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.application.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.configuration.model.ResultResponse;
import com.green.greengram.configuration.model.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed/comment")
public class FeedCommentController {
    private final FeedCommentService feedCommentService;

    @PostMapping
    public ResultResponse<?> postFeedComment(@AuthenticationPrincipal UserPrincipal userPrincipal
                                           , @RequestBody FeedCommentPostReq req) {

        req.setSignedUserId( userPrincipal.getSignedUserId() );
        log.info("req: {}", req);
        long feedCommentId = feedCommentService.postFeedComment( req );
        return new ResultResponse<>("댓글 등록 완료", feedCommentId);
    }

    @GetMapping
    public ResultResponse<?> getFeedCommentList(@ModelAttribute @Valid FeedCommentGetReq req) {
        log.info("req: {}", req);
        List<FeedCommentGetRes> list = feedCommentService.getFeedCommentList(req);
        return new ResultResponse<>(String.format("%d rows", list.size()), list);
    }

    @DeleteMapping
    public ResultResponse<?> deleteFeedComment(@AuthenticationPrincipal UserPrincipal userPrincipal
                                             , @ModelAttribute FeedCommentDeleteReq req) {
        req.setSignedUserId(userPrincipal.getSignedUserId());
        log.info("req: {}", req);
        int result = feedCommentService.deleteFeedComment(req);
        return new ResultResponse<>("댓글 삭제 완료", result);
    }
}
