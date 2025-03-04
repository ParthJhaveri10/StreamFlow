package com.example.service;

import com.example.model.Video;
import com.example.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public String uploadVideo(String title, String description, String email, MultipartFile file) {
        try {
            Video video = new Video();
            video.setTitle(title);
            video.setDescription(description);
            video.setUploaderEmail(email);
            video.setVideoData(file.getBytes());  // Store video as byte[]

            videoRepository.save(video);
            return "Video uploaded successfully with ID: " + video.getId();
        } catch (IOException e) {
            return "Error uploading video: " + e.getMessage();
        }
    }

    public Video getVideoById(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        return video.orElse(null);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> getVideosByUploader(String email) {
        return videoRepository.findByUploaderEmail(email);
    }
}
