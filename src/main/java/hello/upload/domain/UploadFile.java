package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    // 유저가 업로드한 파일명
    private String uploadFileName;

    // 서버 내부에서 관리하는 파일명
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
    // 유저가 업로드한 파일명으로 서버 내부에 파일을 저장하면 안된다.
    // 서로 다른 고객이 같은 파일이름을 업로드 하는 경우 파일명에 충돌이 날 수 있다.
    // 서버에서는 저장할 파일명이 겹치지 않도록 내부에서 관리하는 별도의 파일명이 필요하다.
}
