package com.green.greengram.configuration.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j //로그
@Component //빈등록
public class MyFileUtil {
    public final String fileUploadPath;

    //String값 하나 파라미터로 받는 생성자 만든다
    public MyFileUtil(@Value("${constants.file.directory}") String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    //디렉토리 생성
    public void makeFolders(String path) {
        //File IO (input/output)
        // D:/test/download/greengram + path경로, 예를들어 path에 ddd/abc
        // 결과: D:/test/download/greengram/ddd/abc 경로를 가진 파일 객체가 만들어진다.
        File file = new File(fileUploadPath, path);

        //해당 경로의 디렉토리가 없다면 디렉토리를 생성한다.
        if( !file.exists() ) { //해당 경로의 디렉토리가 없다면
            file.mkdirs(); //폴더를 만든다. mkdirs는 중간경로에 디렉토리가 없어도 전부 디렉토리 만들어준다.
        }
    }

    //파일명에서 .포함한 확장자 리턴
    public String getExt(String fileName) {
        return fileName.substring( fileName.lastIndexOf(".") );
    }

    //랜덤 파일명 리턴
    public String makeRandomFileName() {
        return UUID.randomUUID().toString();
    }

    //랜덤파일명 + 확장자 리턴
    public String makeRandomFileName(String originalFileName) {
        return makeRandomFileName() + getExt(originalFileName);
    }

    //랜덤파일명 + 확장자 리턴
    public String makeRandomFileName(MultipartFile mf) {
        String originalFileName = mf.getOriginalFilename();
        return makeRandomFileName(originalFileName);
    }

    public void deleteFile(String path) {
        File file = new File(fileUploadPath, path);
        if(file.exists()) {
            file.delete();
        }
    }

    // C:\Windows
    // C:\Windows\appcompat
    // C:\Windows\appcompat\appraiser
    public void deleteDirectory(String fullPath) {
        File directory = new File(fullPath);
        if( directory.exists() && directory.isDirectory() ) {
            File[] includedFiles = directory.listFiles(); //폴더 안에 있는 모든 파일들을 가져온다.

            for( File file : includedFiles ) {
                if( file.isDirectory() ) {
                    deleteDirectory( file.getAbsolutePath() ); //재귀호출
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    //MultipartFile 객체에 있는 파일을 원하는 위치로 저장
    public void transferTo(MultipartFile mf, String targetPath) throws IOException {
        File file = new File(fileUploadPath, targetPath);
        mf.transferTo(file);
    }
}
