package hello.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName, @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        // 스프링은 MultipartFile 인터페이스로 멀티파트 파일을 편리하게 지원한다.
        log.info("request={}", request);
        log.info("itemName={}", itemName);
        // @RequestParam MultipartFile file
        // 업로드하는 HTML Form 의 name 에 맞추어 @RequestParam 을 적용 (@ModelAttribute 도 가능)
        log.info("multipartFile={}", file);

        if (!file.isEmpty()) {
            // MultipartFile.getOriginalFilename() : 업로드 파일 명
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            // MultipartFile.transferTo(...) : 파일 저장
            file.transferTo(new File(fullPath));
        }
        return "upload-form";
    }
}


