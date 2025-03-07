# 🎬 StreanFlow

## 🚀 Overview

This project is a microservices-based video streaming platform built using **Spring Boot, MySQL, and HTTP Byte Ranges** for efficient streaming. It currently consists of three microservices that work together to enable user authentication, video uploading, storage, and streaming.

## 🛠 Microservices

| Microservice     | Description                                              |
| ---------------- | -------------------------------------------------------- |
| **ms-user**      | Handles user authentication and registration.            |
| **ms-media**     | Manages video storage, metadata, and retrieval.          |
| **ms-streaming** | Handles video streaming and supports adaptive streaming. |

## 🗂 Microservices Breakdown

### 📍 **ms-user (User Management)**

- Registers users and manages authentication.

#### **Endpoints:**

- `POST /register` – Register a new user
- `POST /login` – Authenticate and receive a JWT token

### 📍 **ms-media (Video Storage & Metadata)**

- Handles video uploads & metadata storage.
- Stores videos in a MySQL database as BLOBs.

#### **Endpoints:**

- `POST /upload` – Upload a video
- `GET /videos/{id}` – Fetch video metadata

### 📍 **ms-streaming (Video Streaming)**

- Streams videos efficiently using HTTP Byte Ranges.
- Supports adaptive streaming for different network conditions.

#### **Endpoints:**

- `GET /stream/{id}` – Stream video by ID

## 🛠 Technologies Used

- ✅ **Spring Boot** – Backend framework
- ✅ **MySQL** – Database storage
- ✅ **Postman** – API testing

## 📡 API Testing with Postman

### ✅ **User Authentication (ms-user)**

#### **Register a new user**

**Endpoint:** `POST /register`

**Request Body (JSON):**

```json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123"
}
```

### ✅ **Video Upload (ms-media)**

#### **Upload a video**

**Endpoint:** `POST /upload`

**Headers:**

```
Content-Type: multipart/form-data
```

**Form Data:**

```
title: "Sample Video"
description: "This is a test video"
file: Choose a video file
```

**Response:**

```json
{
  "videoId": 1,
  "message": "Video uploaded successfully!"
}
```

### ✅ **Retrieve Video Metadata (ms-media)**

#### **Get video metadata by ID**

**Endpoint:** `GET /videos/{id}`

**Example:** `GET /videos/1`

**Response:**

```json
{
  "id": 1,
  "title": "Sample Video",
  "description": "This is a test video",
  "uploaderEmail": "test@example.com"
}
```

### ✅ **Stream Video (ms-streaming)**

#### **Stream video by ID**

**Endpoint:** `GET /stream/{id}`

**Example:** `GET /stream/1`

**Headers:**

```
Accept: video/mp4
```

**Response:** Video stream will start playing.

## 🔒 Security Implementation

- Spring Security handles role-based access control.

## 📌 Future Enhancements

- 🎨 **A frontend UI** to support the current system and render the video content.
- 💬 **A chat section** where users can post comments and interact.

---

🚀 **Developed with Spring Boot & Microservices Architecture**

