package com.rakesh.wrestlingapp.service.video;
import com.rakesh.wrestlingapp.entity.UpdateModel;
import com.rakesh.wrestlingapp.entity.Videos;
import com.rakesh.wrestlingapp.exceptions.ResourceNotFound;
import com.rakesh.wrestlingapp.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoService implements VideoInterface   {
	
	@Autowired
	private VideoRepository videoRepository ;


	@Override
	public Videos createPost(Videos  videos) {
		if(videos.getTitle().isEmpty()) {
			throw new ResourceNotFound("402" ,"please field required details");
		}
		try {
			Videos saveVideo = videoRepository.save(videos);
			videos.setAddedDate(new Date());
			videos.setVideoName(videos.getTitle());
			return videoRepository.save(saveVideo);
		}catch(IllegalArgumentException i) {
			throw new ResourceNotFound("401" ,"hey your data is Empty");
		}catch(Exception e) {
			throw new ResourceNotFound("401" ,"something is wrong"+e.getMessage());
		}
	}

	@Override
	public Videos getVideosById(Integer id) {
	Videos video = this.videoRepository.findById(id).orElseThrow(() -> new ResourceNotFound("504","id is not present"));
	return video ;
	}

	@Override
	public List<Videos> getAllVideos() {
	List<Videos> listOfVideo  = null ;
	try {
		listOfVideo = this.videoRepository.findAll();
		return listOfVideo ;
	}catch(Exception e) {
		throw new ResourceNotFound("404","i am sorry "+e.getMessage());
	}
	}

	@Override
	public Videos updatePost(Videos videos, Integer id) {
		Videos video = this.videoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("501","Id not found"));
		
		video.setTitle(videos.getTitle());
		video.setDescription(videos.getDescription());
		video.setTags(videos.getTags());
		video.setAddedDate(new Date());
		Videos updateVideo =this.videoRepository.save(video);
		return updateVideo ;
	}

	@Override
	public void deleteVideos(Integer id) {
	Videos video = this.videoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("403","video id not found"));
	this.videoRepository.delete(video);
		
	}

	@Override
	public UpdateModel updateModel(UpdateModel updateModel, int id) {
Videos video = this.videoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("501","Id not found"));
		updateModel.setId(id);
		video.setTitle(updateModel.getTitle());
		video.setDescription(updateModel.getDescription());
		video.setTags(updateModel.getTags());
		video.setAddedDate(new Date());
	this.videoRepository.save(video);
		return updateModel ;
	}


	@Override
	public List<Videos> getVideosByUserId(Integer userId) {

		return videoRepository.findAllByUser_Id(userId);
	}
	
}
