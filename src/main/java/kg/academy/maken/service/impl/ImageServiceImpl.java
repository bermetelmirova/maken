package kg.academy.maken.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.academy.maken.entity.Image;
import kg.academy.maken.repository.ImageRepository;
import kg.academy.maken.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private static final String CLOUDINARY_URL = "cloudinary://513634983379558:egniiuYsUyUDCW9FuFvA982uAuE@dh7tacgyi";
    @Autowired
    private  ImageRepository imageRepository;


    @Override
    public String saveImageInCloudinary(MultipartFile multipartFile) {
        File file;

        try {
            file = Files.createTempFile(System.currentTimeMillis() + "",
                            Objects.requireNonNull(multipartFile.getOriginalFilename())
                                    .substring(multipartFile.getOriginalFilename().length() - 4))
                    .toFile();

            multipartFile.transferTo(file);

            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return ((String) uploadResult.get("url"));
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Image saveImage(String url) {
        return imageRepository.save(Image.builder()
                .imageUrl(url)
                .build());
    }


    @Override
    public Image saveImage(MultipartFile multipartFile) {
        String url = saveImageInCloudinary(multipartFile);
        return saveImage(url);
    }
}
