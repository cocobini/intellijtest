package com.shop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        UUID uuid = UUID.randomUUID();

        // 확장자를 뽑아냄
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // 저장될 파일명(uuid + 확장자)
        String savedFileName = uuid.toString() + extension;

        // 파일 저장경로 + 저장파일명
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        // 파일 저장 후 자원 반납
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        // 저장 파일명 리턴
        return savedFileName;
    } // end uploadFile

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);

        if (deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일 삭제");
        } else {
            log.info("파일이 존재하지 않음");
        }
    } // end deleteFile

}
