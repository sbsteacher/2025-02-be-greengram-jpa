package com.green.greengram.application.userfollow;

import com.green.greengram.application.userfollow.model.UserFollowReq;
import com.green.greengram.configuration.model.ResultResponse;
import com.green.greengram.configuration.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/follow")
public class UserFollowController {
    private final UserFollowService userFollowService;

    @PostMapping
    public ResultResponse<?> postUserFollow(@AuthenticationPrincipal UserPrincipal userPrincipal
                                          , @RequestBody UserFollowReq req) {
        req.setFromUserId( userPrincipal.getSignedUserId() );
        log.info("req: {}", req);
        int result = userFollowService.postUserFollow(req);
        return new ResultResponse<>("팔로우 등록", result);
    }

    @DeleteMapping
    public ResultResponse<?> deleteUserFollow(@AuthenticationPrincipal UserPrincipal userPrincipal
                                          , @ModelAttribute UserFollowReq req) {
        req.setFromUserId( userPrincipal.getSignedUserId() );
        log.info("req: {}", req);
        int result = userFollowService.deleteUserFollow(req);
        return new ResultResponse<>("팔로우 취소", result);
    }
}
