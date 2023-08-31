package com.example.Neuro_video_generator.convertors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


import com.aspose.words.Shape;
import com.groupdocs.conversion.filetypes.ImageFileType;
import com.groupdocs.conversion.options.convert.ImageConvertOptions;
import com.jogamp.common.net.Uri;
import lombok.extern.log4j.Log4j;
import com.aspose.words.*;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Log4j
public class TypeConvertor {
    public BufferedImage svgToPng(String inputImagePath,String outputImagePath) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream(inputImagePath);
        TranscoderInput transcoderInput = new TranscoderInput(inputStream);
//        log.debug(outputImagePath + " удалено - "  + outputFile.delete());
        File outputFile = new File(outputImagePath);
        log.debug("Создан файл - " + outputFile.createNewFile());
        // Define OutputStream Location
        OutputStream outputStream = new FileOutputStream(outputImagePath);
        TranscoderOutput transcoderOutput = new TranscoderOutput(outputStream);

        // Convert SVG to PNG and Save to File System
        PNGTranscoder pngTranscoder = new PNGTranscoder();
        pngTranscoder.transcode(transcoderInput, transcoderOutput);

        // Clean Up
        outputStream.flush();
        outputStream.close();

        BufferedImage image = ImageIO.read(outputFile);
//        System.exit(0);
//        log.debug(outputImagePath + " удален - "  + outputFile.delete());
        return image;
    }
//public static BufferedImage svgToPng(String inputImagePath, String outputImagePath) throws Exception {
//    Converter converter = new Converter(inputImagePath);
//    ImageConvertOptions options = new ImageConvertOptions();
//    options.setFormat(ImageFileType.Png);
//    File imagePng = new File(outputImagePath);
//    if(imagePng.exists()){
//        log.debug("Файл " + outputImagePath + " существует,удаляю");
//        imagePng.delete();
//    }
//    converter.convert(outputImagePath, options);
//    BufferedImage image = ImageIO.read(imagePng);
//    imagePng.delete();
//    return image;
//}
//public static BufferedImage svgToPng(String inputImagePath, String outputImagePath) throws Exception {
//    Document doc = new Document();
//    DocumentBuilder builder = new DocumentBuilder(doc);
//    Path svgPath = Paths.get(inputImagePath);
//    String svgText = Files.readString(svgPath);
//    log.debug("svg text - " + svgText);
//    log.debug("input image path - " + inputImagePath);
//    Shape shape = builder.insertImage(inputImagePath);
//    shape.getImageData().save(outputImagePath);
//    File outputImageFile = new File(outputImagePath);
//    BufferedImage outputImage = ImageIO.read(outputImageFile);
//    outputImageFile.delete();
//    return outputImage;
//
//}
}