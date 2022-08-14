package hello.upload.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 아이템 저장용 폼
 */
@Data
public class ItemForm {

    private Long itemId;
    private String itemName;
    // 이미지를 다중 업로드 하기위해 MultipartFile 사용
    private List<MultipartFile> imageFiles;
    // 멀티파트는 @ModelAttribute 에서 사용할 수 있다.
    private MultipartFile attachFile;
}
