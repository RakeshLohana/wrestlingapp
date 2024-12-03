//package com.rakesh.wrestlingapp.service;
//
//
//import com.rakesh.wrestlingapp.dto.VideoReqRes;
//import com.rakesh.wrestlingapp.entity.Video;
//import com.rakesh.wrestlingapp.exceptions.ResourceNotFoundException;
//import com.rakesh.wrestlingapp.repository.VideoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class VideoServiceImpl implements VideoService {
//
//    @Autowired
//    private VideoRepository videoRepository;
//
//    @Override
//    public Video createVideo(VideoReqRes videoDTO) {
//        Video video = new Video();
//        video.setUserId(videoDTO.getUserId());
//        video.setTitle(videoDTO.getTitle());
//        video.setDescription(videoDTO.getDescription());
//        video.setFileUrl(videoDTO.getFileUrl());
//        video.setThumbnailUrl(videoDTO.getThumbnailUrl());
//        video.setIsPublic(videoDTO.getIsPublic());
//        video.setDuration(videoDTO.getDuration());
//        video.setUploadDate(LocalDateTime.now());
//        return videoRepository.save(video);
//    }
//
//    @Override
//    public Video getVideoById(Long videoId) {
//        return videoRepository.findById(videoId)
//                .orElseThrow(() -> new ResourceNotFoundException("Video not found with id: " + videoId));
//    }
//
//    @Override
//    public List<Video> getAllVideos() {
//        return videoRepository.findAll();
//    }
//
//    @Override
//    public List<Video> getVideosByUserId(Long userId) {
//        return videoRepository.findByUserId(userId);
//    }
//
//    @Override
//    public Video updateVideo(Long videoId, VideoReqRes videoDTO) {
//        Video video = getVideoById(videoId);
//        video.setTitle(videoDTO.getTitle());
//        video.setDescription(videoDTO.getDescription());
//        video.setIsPublic(videoDTO.getIsPublic());
//        return videoRepository.save(video);
//    }
//
//    @Override
//    public void deleteVideo(Long videoId) {
//        Video video = getVideoById(videoId);
//        videoRepository.delete(video);
//    }
//}
