package com.example.Neuro_video_generator.service;

import com.example.Neuro_video_generator.repositories.UserFilesRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.Neuro_video_generator.exceptions.StorageException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
@Service
public class UserFilesService implements StorageService {
    @Value("{storage.location.directory}")
    private Path rootLocation;
    @Autowired
    private UserFilesRepository userFilesRepository;
    @Override
    public void init() {

    }
    @Override
    public void store(MultipartFile file) {
            try {
                if (file.isEmpty()) {
                    throw new StorageException("Failed to store empty file.");
                }
                Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
                if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                    // This is a security check
                    throw new StorageException(
                            "Cannot store file outside current directory.");
                }
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, destinationFile,
                            StandardCopyOption.REPLACE_EXISTING);
                }
            }
            catch (IOException e) {
                throw new StorageException("Failed to store file.", e);
            }
    }



    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
