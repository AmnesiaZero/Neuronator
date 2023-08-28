package com.example.Neuro_video_generator.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.opensdk.config.ConnectionConfiguration;
import com.amazonaws.opensdk.config.TimeoutConfiguration;
import com.deeparteffects.sdk.java.DeepArtEffectsClient;
import com.deeparteffects.sdk.java.model.PostUploadRequest;
import com.deeparteffects.sdk.java.model.PostUploadResult;
import com.deeparteffects.sdk.java.model.UploadRequest;
import com.example.Neuro_video_generator.configurations.DeepArtConfiguration;
import com.example.Neuro_video_generator.repositories.UserRepository;
import com.zakgof.velvetvideo.IDemuxer;
import com.zakgof.velvetvideo.IVelvetVideoLib;
import com.zakgof.velvetvideo.IVideoDecoderStream;
import com.zakgof.velvetvideo.IVideoFrame;
import com.zakgof.velvetvideo.impl.VelvetVideoLib;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Log4j
@Service
public class UserService extends JFrame {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeepArtConfiguration deepArtConfiguration;
    @SneakyThrows
    public void getProcessedVideo(String fileName){
        IVelvetVideoLib lib = VelvetVideoLib.getInstance();
        try (IDemuxer demuxer = lib.demuxer(new File(fileName))) {
            IVideoDecoderStream videoStream = demuxer.videoStream(0);
            IVideoFrame videoFrame;
            List<BufferedImage> processedImages = new ArrayList<>();
            while ((videoFrame = videoStream.nextFrame()) != null) {
                BufferedImage image = videoFrame.image();
                String processedImage = getProcessedImage(image,1);
            }
        }
        log.debug("Test over");
        System.exit(0);
    }
    public String getProcessedImage(BufferedImage image, int styleId){
        String encodedImage = encodeImage(image);
        UploadRequest uploadRequest = new UploadRequest();
        PostUploadRequest postUploadRequest = new PostUploadRequest();
        uploadRequest.setStyleId(String.valueOf(styleId));
        uploadRequest.setImageBase64Encoded(encodedImage);
        postUploadRequest.setUploadRequest(uploadRequest);
        DeepArtEffectsClient deepArtEffectsClient = deepArtConfiguration.configureDeepArtEffectsClient();
        PostUploadResult postUploadResult = deepArtEffectsClient.postUpload(postUploadRequest);
        String processedImage = postUploadResult.getUploadResponse().getSubmissionId();
        log.debug("Message - " + processedImage);
        return processedImage;
    }
    @SneakyThrows
    public String encodeImage(BufferedImage image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);

    }
}
