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
        File outputFile = new File(outputImagePath);
        log.debug("Создан файл - " + outputFile.createNewFile());
        OutputStream outputStream = new FileOutputStream(outputImagePath);
        TranscoderOutput transcoderOutput = new TranscoderOutput(outputStream);

        PNGTranscoder pngTranscoder = new PNGTranscoder();
        pngTranscoder.transcode(transcoderInput, transcoderOutput);

        outputStream.flush();
        outputStream.close();

        BufferedImage image = ImageIO.read(outputFile);
        log.debug(outputImagePath + " удален - "  + outputFile.delete());
        return image;
    }

}