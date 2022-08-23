package com.example.RecipeBookBackEnd.service;

import com.example.RecipeBookBackEnd.model.Photo;
import com.example.RecipeBookBackEnd.repository.FileSystemRepository;
import com.example.RecipeBookBackEnd.repository.ImageDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FileLocationService {

    @Autowired
    private FileSystemRepository fileSystemRepository;
    @Autowired
    private ImageDbRepository imageDbRepository;

    public Long save(byte[] bytes, String photoName) throws Exception {
        String location = fileSystemRepository.save(bytes, photoName);

        return imageDbRepository.save(new Photo(photoName, location))
            .getId();
    }

    public FileSystemResource find(Long photoId) {
        Photo photo = imageDbRepository.findById(photoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(photo.getLocation());
    }

}