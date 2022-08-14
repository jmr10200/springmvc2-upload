package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }
        // 유저가 업로드한 파일명 취득
        String originalFilename = multipartFile.getOriginalFilename();
        // 서버에서 관리하는 파일명 (생성)
        String storeFileName = createStoreFileName(originalFilename);
        // 서버에서 관리하는 파일명으로 저장
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }

    /**
     * 서버 내부에서 관리하는 파일명 생성
     * UUID 를 생성하여 충돌하지 않도록 한다.
     */
    private String createStoreFileName(String originalFilename) {
        String ext = extractExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        // 확장자를 별도로 추출해서 서버 내부에서 관리하는 파일명에도 붙여준다.
        return uuid + "." + ext;
    }

    /**
     * 확장자 추출
     */
    private String extractExtension(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
