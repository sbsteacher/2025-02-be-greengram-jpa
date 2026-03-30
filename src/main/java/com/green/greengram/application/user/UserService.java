package com.green.greengram.application.user;

import com.green.greengram.application.user.model.*;
import com.green.greengram.configuration.util.MyFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final MyFileUtil myFileUtil;

    public int signUp(UserSignUpReq req, MultipartFile mf) {
        String hashedPw = passwordEncoder.encode( req.getUpw() );
        log.info("hashedPw: {}", hashedPw);
        req.setUpw(hashedPw);

        //파일 업로드가 되었으면 저장하는 파일명을 테이블에 저장
        String savedPicFileName = mf == null ? null : myFileUtil.makeRandomFileName(mf);
        req.setPic(savedPicFileName);

        //회원가입한 유저의 id값을 얻어오고 싶다.
        int result = userMapper.signUp(req);
        if( mf != null ) {
            long id = req.getId(); //프로파일 이미지 저장하는 규칙이 있는데 pk값의 폴더를 만들고 거기에 이미지 파일을 저장한다.
            //String middlePath = String.format("user/%d", id);
            String middlePath = "user/" + id;
            //폴더 만들기
            myFileUtil.makeFolders(middlePath);

            String fullFilePath = String.format("%s/%s", middlePath, savedPicFileName);

            try {
                myFileUtil.transferTo(mf, fullFilePath);
            } catch (IOException e) {
                e.printStackTrace(); //오류 메세지 콘솔에 출력
            }
        }

        return result;
    }

    public UserSignInRes signIn(UserSignInReq req) {
        UserGetOneRes res = userMapper.findByUid( req.getUid() );

        log.info("res: {}", res);
        if(res == null || !passwordEncoder.matches(req.getUpw(), res.getUpw())) { //비밀번호가 맞지 않으면?
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디, 비밀번호를 확인해 주세요.");
        }
        //로그인 성공!! 예전에는 AT, RT을 FE전달  >>> 보안 쿠키 이용
//        JwtUser jwtUser = new JwtUser(res.getId());
//        String accessToken = jwtTokenProvider.generateAccessToken(jwtUser);
//        String refreshToken = jwtTokenProvider.generateRefreshToken(jwtUser);

        return UserSignInRes.builder()
                            .signedUserId( res.getId() )
                            .nm( res.getNm() )
                            .pic( res.getPic() )
                            .build();
    }

    public UserProfileGetRes getProfileUser (UserProfileGetReq req) {
        /* followState, 0, 1, 2, 3
        profile주인, 로그인한 사용자 간의 팔로우 상태값
        0: 서로 팔로우 안 한 상태
        1: 로그인한 사용자가 profile주인을 팔로우 한 상태
        2: profile주인이 로그인한 사용자를 팔로우 한 상태
        3: 서로 팔로우 한 상태
         */
        return userMapper.findProfileUser(req);
    }

    public String patchProfilePic(long signedUserId, MultipartFile pic) {
        //기존 프로파일 사진은 삭제, 기존 파일명을 구해야 함.
        UserGetOneRes res = userMapper.findById( signedUserId );
        String folderPath = String.format("user/%d", signedUserId);

        //파일 삭제 고고!!
        String existedFilePath = String.format("%s/%s", folderPath, res.getPic());
        myFileUtil.deleteFile(existedFilePath);

        //폴더 생성
        myFileUtil.makeFolders(folderPath);

        //업로드 한 파일 원하는 위치로 이동
        String saveFileName = myFileUtil.makeRandomFileName(pic); //저장시킬 파일명 만들었고
        String saveFullFilePath = String.format("%s/%s", folderPath, saveFileName);

        try {
            myFileUtil.transferTo(pic, saveFullFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //DB 수정처리
        UserUpdDto dto = UserUpdDto.builder()
                                   .id( signedUserId )
                                   .pic( saveFileName )
                                   .build();
        userMapper.updUser(dto);
        return saveFileName;
    }

    public void deleteProfilePic(long signedUserId) {
        //폴더 째로 삭제
        String absolutePath = String.format("%s/user/%d", myFileUtil.fileUploadPath, signedUserId);
        myFileUtil.deleteDirectory( absolutePath );

        //user테이블의 해당 row의 pic 컬럼의 값을 null로 변경
        UserUpdDto dto = UserUpdDto.builder()
                                   .id( signedUserId )
                                   .pic( "" )
                                   .build();
        userMapper.updUser(dto);
    }
}
