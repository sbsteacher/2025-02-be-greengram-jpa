package com.green.greengram.configuration.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component //빈등록
@RequiredArgsConstructor
public class ImgUploadManager {
    private final MyFileUtil myFileUtil;

    public List<String> saveFeedPics(long feedId, List<MultipartFile> pics) {
        //폴더 생성, feed/${feedId}
        String feedPath = String.format("feed/%d", feedId);
        myFileUtil.makeFolders(feedPath);

        List<String> picFileNames = new ArrayList(pics.size());
        for(MultipartFile mf : pics) {
            String randomFileName = myFileUtil.makeRandomFileName(mf);
            picFileNames.add(randomFileName);

            //파일을 저장, feedPath 경로에 파일을 저장해야 한다.
            String fullFilePath = String.format("%s/%s", feedPath, randomFileName);

            try {
                myFileUtil.transferTo(mf, fullFilePath);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "E01 - 서버에서 에러가 발생하였습니다.");
            }
        }
        return picFileNames;
    }
}
