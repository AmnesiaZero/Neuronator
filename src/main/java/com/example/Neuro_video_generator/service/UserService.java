package com.example.Neuro_video_generator.service;

import com.example.Neuro_video_generator.convertors.TypeConvertor;
import com.example.Neuro_video_generator.repositories.UserRepository;
import com.zakgof.velvetvideo.IDemuxer;
import com.zakgof.velvetvideo.IMuxer;
import com.zakgof.velvetvideo.IVelvetVideoLib;
import com.zakgof.velvetvideo.IVideoDecoderStream;
import com.zakgof.velvetvideo.IVideoEncoderStream;
import com.zakgof.velvetvideo.IVideoFrame;
import com.zakgof.velvetvideo.impl.VelvetVideoLib;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Log4j
@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    private String sep = System.getProperty("file.separator");
    @SneakyThrows
    public void getProcessedVideo(String inputVideoPath, int fps){
        inputVideoPath = "D:" + sep + "JavaProjects" + sep + "Neuro_video_generator" + sep + "src" + sep + "main" + sep + "resources" + sep + "media" + sep + "videos" + sep + "input_videos" + sep + "input_video.mkv";
        String resourcesDirectory = System.getProperty("user.dir")  + sep + "src" + sep + "main" + sep + "resources";
        List<BufferedImage> processedImages = new ArrayList<>();
        IVelvetVideoLib lib = VelvetVideoLib.getInstance();
        File inputVideo = new File(inputVideoPath);
        try (IDemuxer demuxer = lib.demuxer(inputVideo)) {
            IVideoDecoderStream videoStream = demuxer.videoStreams().get(0);
            IVideoFrame videoFrame;
            while ((videoFrame=videoStream.nextFrame())!=null){
                BufferedImage image = videoFrame.image();
                BufferedImage processedImage = getProcessedImage(image);
                processedImages.add(processedImage);
            }
        }
        String outputVideoPath = resourcesDirectory + "media" + sep + "videos" + sep + "output_videos" + sep + "output_video.webm";
        File outputVideo = new File(outputVideoPath);
        try (IMuxer muxer = lib.muxer("webm").videoEncoder(lib.videoEncoder("libvpx-vp9").framerate(1, 5))
                .build(outputVideo)) {
            IVideoEncoderStream encoder = muxer.videoEncoder(0);
            for (BufferedImage image : processedImages) {
                encoder.encode(image);
            }
        }
    }
    @SneakyThrows
    public BufferedImage getProcessedImage(BufferedImage image) {
        String resourcesDirectory = System.getProperty("user.dir")  + sep + "src" + sep + "main" + sep + "resources";

        String inputImagePath = resourcesDirectory  + sep + "media" + sep + "images" + sep + "input_images" + sep + "input_image.png";
        log.debug("image path = " + inputImagePath);
        File inputImage = new File(inputImagePath);
        ImageIO.write(image,"png",inputImage);

        String outputImageDirectory = resourcesDirectory  + sep + "media" + sep + "images" + sep + "output_images";
        String outputImagePathSvg = outputImageDirectory  + sep + "output_image.svg";
        File outputImageSvg = new File(outputImagePathSvg);
        if(outputImageSvg.exists()){
            log.debug("Файл - " + outputImagePathSvg + " уже существует,удаляю");
            outputImageSvg.delete();
        }
        outputImageSvg.createNewFile();
        String linedrawPath = resourcesDirectory +  sep + "libraries" + sep + "linedraw" + sep + "linedraw.py";
        String command = "python "  + linedrawPath + " -i " + inputImagePath + " -o " + outputImagePathSvg;
        log.debug(command);
        Process process = Runtime.getRuntime().exec(command);
        String outputImagePathPng = outputImageDirectory  + sep + "output_image.png";
        BufferedImage processedImage = TypeConvertor.svgToPng(outputImagePathSvg,outputImagePathPng);
        inputImage.delete();
        outputImageSvg.delete();
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
