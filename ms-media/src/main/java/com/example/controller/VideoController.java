package com.example.controller;

import com.example.model.Video;
import com.example.service.VideoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("email") String email,
            @RequestPart("file") MultipartFile file) {  // Use @RequestPart for the file

        String response = videoService.uploadVideo(title, description, email, file);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
//        Video video = videoService.getVideoById(id);
//        return ResponseEntity.ok(video);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getVideoById(@PathVariable Long id) {
        Video video = videoService.getVideoById(id);

        if (video != null && video.getVideoData() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                    .body(video.getVideoData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/by-uploader")
    public List<Video> getVideosByUploader(@RequestParam String email) {
        return videoService.getVideosByUploader(email);
    }
}
