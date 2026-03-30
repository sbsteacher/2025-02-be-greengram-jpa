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
    //private final UserMapper userMapper;
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


}
