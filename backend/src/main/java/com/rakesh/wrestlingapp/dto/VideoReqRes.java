//package com.rakesh.wrestlingapp.dto;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.rakesh.wrestlingapp.entity.Video;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class VideoReqRes {
//
//    private int statusCode;
//    private String error;
//    private String message;
//
//    // Video-specific fields
//    private Long videoId;
//    private Long userId;
//    private String title;
//    private String description;
//    private String fileUrl;
//    private String thumbnailUrl;
//    private Boolean isPublic;
//    private LocalDateTime uploadDate;
//    private Long views;
//    private Long likes;
//    private Double duration;
//
//    // For returning entities or lists
//    private Video video;
//    private List<Video> videos;
//
//}
