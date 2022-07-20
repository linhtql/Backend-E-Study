package com.estudy.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.estudy.service.IStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService implements IStorageService {

    @Autowired
    Cloudinary cloudinary;

    @Override
    public String storageFile(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinary.uploader().uploadLarge(uploadedFile, ObjectUtils.emptyMap());
            boolean isDeleted = uploadedFile.delete();
            if (isDeleted){
                System.out.println("File successfully deleted");
            }else
                System.out.println("File doesn't exist");
            return  uploadResult.get("url").toString();



        }
     catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {

        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String generatedFileName = UUID.randomUUID().toString().replace("-", "");
        generatedFileName= generatedFileName+"."+fileExtension;
        File convFile = new File(generatedFileName);

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
