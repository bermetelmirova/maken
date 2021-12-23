package kg.academy.maken.service;

import kg.academy.maken.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String saveImageInCloudinary(MultipartFile multipartFile);

    Image saveImage(String url);

    Image saveImage(MultipartFile multipartFile);
}
