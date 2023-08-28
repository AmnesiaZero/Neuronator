package com.example.Neuro_video_generator.repositories;

import com.example.Neuro_video_generator.models.UserFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<UserFiles,Integer> {
}
