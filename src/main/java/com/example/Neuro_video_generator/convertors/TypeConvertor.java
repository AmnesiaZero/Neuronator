package com.example.Neuro_video_generator.convertors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import com.aspose.words.Shape;
import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.filetypes.ImageFileType;
import com.groupdocs.conversion.options.convert.ImageConvertOptions;
import com.jogamp.common.net.Uri;
import lombok.extern.log4j.Log4j;
import com.aspose.words.*;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;

import javax.imageio.ImageIO;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Log4j
public class TypeConvertor {
//    public static BufferedImage svgToPng(String inputImagePath, String outputImagePath) throws Exception {
//        //Step -1: We read the input SVG document into Transcoder Input
//        //We use Java NIO for this purpose
//        Path path = Path.of(inputImagePath);
//        String svgUriInput = Files.readString(path);
//        log.debug("uri = " + svgUriInput);
//        InputStream inputStream = new InputStream() {
//        }
//        TranscoderInput inputSvgImage = new TranscoderInput();
//        //Step-2: Define OutputStream to PNG Image and attach to TranscoderOutput
//        OutputStream pngStream = new FileOutputStream(outputImagePath);
//        TranscoderOutput outputPng = new TranscoderOutput(pngStream);
//        // Step-3: Create PNGTranscoder and define hints if required
//        PNGTranscoder converter = new PNGTranscoder();
//        // Step-4: Convert and Write output
//        converter.transcode(inputSvgImage,outputPng);
//        // Step 5- close / flush Output Stream
//        pngStream.close();
//        File imageFile = new File(outputImagePath);
//        BufferedImage image = ImageIO.read(imageFile);
//        imageFile.delete();
//        return image;
//    }
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
public static BufferedImage svgToPng(String inputImagePath, String outputImagePath) throws Exception {
    Document doc = new Document();
    DocumentBuilder builder = new DocumentBuilder(doc);
    inputImagePath = inputImagePath.replace("\\","/");
    Shape shape = builder.insertImage(inputImagePath);
    shape.getImageData().save(outputImagePath);
    File imageFile = new File(outputImagePath);
    BufferedImage image = ImageIO.read(imageFile);
    imageFile.delete();
    return image;

}
}