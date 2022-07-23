package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.bucket.BucketName;
import com.thesis.medicalapp.filestore.FileStore;
import com.thesis.medicalapp.payload.response.ApiResponse;
import com.thesis.medicalapp.payload.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.apache.http.entity.ContentType.*;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileStoreController {
    private final FileStore fileStore;

    @PostMapping("/files/upload")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //check if the file is an image
        if (!Arrays.asList(
                IMAGE_BMP.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        String fileName = String.format("%s", file.getOriginalFilename());
        String path = String.format("%s/%s", UUID.randomUUID(), fileName);

        try {
            fileStore.upload(path, file, file.getInputStream());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(1, "Uploaded the file successfully.", path));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ApiResponse(0, "Could not upload the file!", null));
        }
    }
}
