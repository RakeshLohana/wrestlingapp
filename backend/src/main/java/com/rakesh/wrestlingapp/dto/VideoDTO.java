package com.rakesh.wrestlingapp.dto;

import com.rakesh.wrestlingapp.entity.Videos;

public class VideoDTO {
    private Integer id;
    private String title;
    private String description;
    private String videoName;
    private String userName; // Add other required fields

    public VideoDTO(Videos video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.videoName = video.getVideoName();
//        this.userName = video.getUser().getName();
    }
}
