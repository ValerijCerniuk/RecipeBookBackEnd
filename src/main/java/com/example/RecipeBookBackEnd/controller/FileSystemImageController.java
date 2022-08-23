package com.example.RecipeBookBackEnd.controller;

import com.example.RecipeBookBackEnd.service.FileLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;


@RestController
@RequestMapping("files")
@CrossOrigin
public class FileSystemImageController {

    private final Logger LOGGER = LoggerFactory.getLogger(FileSystemImageController.class);

    @Autowired
    private FileLocationService fileLocationService;

    @PostMapping("/photos")
    public ResponseEntity<Long> uploadPhoto(@RequestParam MultipartFile photo) {
        Long response = 0L;
        try {
            response = fileLocationService.save(photo.getBytes(), photo.getOriginalFilename());
            LOGGER.info("File " + photo.getOriginalFilename() + " saved by id: " + response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Error in uploadPhoto() of FileSystemImageController: " + e);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
// ne atvaizdoja webp paveiksliuku POSTMANe, gal reiketu apribot ju naudojima
    @GetMapping(value = "/photo/{photoId}")
    public ResponseEntity<?> downloadPhoto(@PathVariable("photoId") Long photoId) {

        FileSystemResource photo = fileLocationService.find(photoId);

        if (!photo.exists()) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        String contentType = fileTypeMap.getContentType(photo.getFile().getName());
        File file = photo.getFile();

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .contentLength(photo.contentLength())
                    .body(new ByteArrayResource(Files.readAllBytes(file.toPath())));
        } catch (IOException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}