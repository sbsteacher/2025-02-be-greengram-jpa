package com.green.greengram.application.user;

import com.green.greengram.application.user.model.*;
import com.green.greengram.configuration.util.MyFileUtil;
import com.green.greengram.entity.User;
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
    private final UserRepository userRepository; //DI 받는다.
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
        User newUser = new User();
        newUser.setUid( req.getUid() );
        newUser.setUpw( hashedPw );
        newUser.setNm( req.getNm() );
        newUser.setPic( savedPicFileName );

        userRepository.save(newUser);

        int result = 1;

        //int result = userMapper.signUp(req);
        if( mf != null ) {
            //long id = req.getId(); //프로파일 이미지 저장하는 규칙이 있는데 pk값의 폴더를 만들고 거기에 이미지 파일을 저장한다.
            long id = newUser.getId();
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
        User user = userRepository.findByUid( req.getUid() );
        log.info("user: {}", user);
        if(user == null || !passwordEncoder.matches(req.getUpw(), user.getUpw())) { //비밀번호가 맞지 않으면?
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디, 비밀번호를 확인해 주세요.");
        }

        return UserSignInRes.builder()
                .signedUserId( user.getId() )
                .nm( user.getNm() )
                .pic( user.getPic() )
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
        //UserGetOneRes res = userMapper.findById( signedUserId );
        //밑의 res는 영속성이 있는 객체라고 부른다. 영속성이 있다는 말은 엔티티 매니저가 관리하는 객체
        User res = userRepository.findById( signedUserId ).orElseThrow();
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

        res.setPic( saveFileName );

        userRepository.save(res); //영속성이 있는 객체를 save하면 update문이 된다.

        return saveFileName;
    }


}
