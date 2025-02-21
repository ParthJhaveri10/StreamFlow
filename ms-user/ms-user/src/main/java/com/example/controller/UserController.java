package com.example.controller;

import com.example.model.User;
import com.example.model.Video;
import com.example.service.UserService;
import com.example.service.VideoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final VideoService videoService;

    public UserController(UserService userService, VideoService videoService) {
        this.userService = userService;
        this.videoService = videoService;
    }

    @PostMapping("/register")
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "User created successfully!";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/upload-video")
    public String uploadVideo(@RequestParam String email,
                              @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam MultipartFile file) {
        videoService.uploadVideo(email, title, description, file);
        return "Video uploaded successfully!";
    }

    @GetMapping("/videos")
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/{email}/videos")
    public List<Video> getUserVideos(@PathVariable String email) {
        return videoService.getVideosByEmail(email);
    }
}
