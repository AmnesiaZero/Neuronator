package com.example.Neuro_video_generator.controllers;

import com.example.Neuro_video_generator.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/get-processed-video")
    public void getProcessedVideo(@RequestParam(name = "name") String fileName,@RequestParam("fps") int fps){
        log.debug("filename = " + fileName);
        userService.getProcessedVideo(fileName,fps);
    }
    @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("file") MultipartFile file){
//        fileStorageService.store(file);

    }

}
