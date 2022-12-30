package com.start.hellospring.service;

import com.start.hellospring.domain.Image;
import com.start.hellospring.repository.IImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final IImageRepository iImageRepository;
    @Value("${file.dir}") private String path;

    public void save(List<MultipartFile> multipartFileList, Long id) throws IOException {
        MultipartFile imageFile = multipartFileList.get(0);
        String storedName = storedName(imageFile);
        Image image = Image.getImage(imageFile.getOriginalFilename(), storedName, id);
        iImageRepository.save(image);
        imageFile.transferTo(new File(getFullPath(storedName)));
    }

    public byte[] getByteArray(Image image) throws IOException {
        File file = new File(getFullPath(image.getStored_name()));
        byte[] array = Files.readAllBytes(file.toPath());
        return array;
    }

    private String getFullPath(String storedName) {
        return path + storedName;
    }

    private String storedName(MultipartFile image) {
        String originalFilename = image.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        log.info("storedName: {}", uuid + LocalDate.now() + "." + ext);
        return uuid + "&" + LocalDate.now() + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");
        return originalFilename.substring(position + 1);
    }
}
