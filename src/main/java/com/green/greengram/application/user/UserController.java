package com.green.greengram.application.user;

import com.green.greengram.application.user.model.*;
import com.green.greengram.configuration.model.JwtUser;
import com.green.greengram.configuration.model.ResultResponse;
import com.green.greengram.configuration.model.UserPrincipal;
import com.green.greengram.configuration.security.JwtTokenManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;

    /* file upload는 Multipart 라는 기술로 파일 업로드를 할건데 FE JSON을 보내지만 조금
    * 다르게 받아야 한다. @RequestPart로 받아야 한다.
    * req는 파일을 제외한 데이터(uid, upw, nm 데이터들)
    * pic은 프로파일 이미지 파일
    * required = false 속성이 없다면 반드시 요청 때 Data를 보내주어야 한다.
    *  */
    @PostMapping("/sign-up")
    public ResultResponse<?> signUp(@RequestPart UserSignUpReq req
                                  , @RequestPart(required = false) MultipartFile pic
    ) {
        log.info("req: {}", req);
        int result = userService.signUp(req, pic);
        return new ResultResponse<>("회원가입 성공", result);
    }

    @PostMapping("/sign-in")
    public ResultResponse<?> signIn(HttpServletResponse res, @RequestBody @Valid UserSignInReq req) {
        log.info("sign-in ================= req: {}", req);
        UserSignInRes userSignInRes = userService.signIn(req);
        //보안 쿠키 처리
        if(userSignInRes != null) {
            JwtUser jwtUser = new JwtUser( userSignInRes.getSignedUserId() );
            jwtTokenManager.issue(res, jwtUser);
        }
        return new ResultResponse<>("로그인 성공", userSignInRes);
    }

    @PostMapping("/sign-out")
    public ResultResponse<?> signOut(HttpServletResponse res) {
        jwtTokenManager.signOut(res);
        return new ResultResponse<>("로그아웃 성공", 1);
    }

    @PostMapping("/reissue")
    public ResultResponse<?> reissue(HttpServletResponse res, HttpServletRequest req) {
        jwtTokenManager.reissue(req, res);
        return new ResultResponse<>("AT 재발행", null);
    }

    @GetMapping("/profile")
    public ResultResponse<?> getProfileUser(@AuthenticationPrincipal UserPrincipal userPrincipal
                                          , @RequestParam long profileUserId) {
        UserProfileGetReq req = new UserProfileGetReq( profileUserId, userPrincipal.getSignedUserId() );
        log.info("req: {}", req);
        UserProfileGetRes res = userService.getProfileUser(req);
        return new ResultResponse<>("프로파일 유저 정보", res);
    }

    @PatchMapping("/profile/pic")
    public ResultResponse<?> patchProfileUserPic(@AuthenticationPrincipal UserPrincipal userPrincipal
                                               , @RequestPart MultipartFile pic) {
        String savedFileName = userService.patchProfilePic(userPrincipal.getSignedUserId(), pic);
        return new ResultResponse<>("프로파일 유저 사진 수정", savedFileName);
    }

    @DeleteMapping("/profile/pic")
    public ResultResponse<?> patchProfileUserPic(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.deleteProfilePic( userPrincipal.getSignedUserId() );
        return new ResultResponse<>("프로파일 이미지 삭제 완료", null);
    }
}
