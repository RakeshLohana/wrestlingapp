package com.rakesh.wrestlingapp.service.video;



import com.rakesh.wrestlingapp.entity.UpdateModel;
import com.rakesh.wrestlingapp.entity.Videos;

import java.util.List;

public interface VideoInterface {
	
	public Videos createPost(Videos videos);
	
	public Videos getVideosById(Integer id);
	
	public List<Videos> getAllVideos();
	
	public Videos updatePost(Videos videos , Integer id);
	
	public void deleteVideos(Integer id);
	public List<Videos> getVideosByUserId(Integer userId);

public UpdateModel updateModel(UpdateModel updateModel, int id);
}
