package com.example.service;

import com.example.model.Video;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface VideoService {
    String uploadVideo(String title, String description, String email, MultipartFile file);
    List<Video> getAllVideos();
    List<Video> getVideosByUploader(String email);
    Video getVideoById(Long id);
}
