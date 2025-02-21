package com.example.service;

import com.example.model.Video;
import com.example.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void uploadVideo(String email, String title, String description, MultipartFile file) {
        try {
            // Create unique filename
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String uploadDir = "uploads/";  // Ensure this folder exists in your project root
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs(); // Create directory if not exists
            }

            // Save file to disk
            File savedFile = new File(uploadDir + filename);
            file.transferTo(savedFile);

            // Save metadata to DB
            Video video = new Video();
            video.setEmail(email);
            video.setTitle(title);
            video.setDescription(description);
            video.setFilePath(uploadDir + filename);
            videoRepository.save(video);

        } catch (IOException e) {
            throw new RuntimeException("Failed to store video file", e);
        }
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public List<Video> getVideosByEmail(String email) {
        return videoRepository.findByEmail(email);
    }
}
