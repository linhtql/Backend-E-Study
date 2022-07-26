package com.estudy.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    String storageFile(MultipartFile file);

}
