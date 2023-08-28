package com.example.Neuro_video_generator.controllers;

import com.example.Neuro_video_generator.service.UserFilesService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j
@RestController
@RequestMapping("/user-files")
public class UserFilesController {
    @Autowired
    private UserFilesService userFilesService;
    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file){
        userFilesService.store(file);
    }
}
