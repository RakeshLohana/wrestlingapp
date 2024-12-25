package com.rakesh.wrestlingapp.controller;


import com.rakesh.wrestlingapp.dto.ReqRes;
import com.rakesh.wrestlingapp.dto.VideoDTO;
import com.rakesh.wrestlingapp.entity.FileModel;
import com.rakesh.wrestlingapp.entity.OurUsers;
import com.rakesh.wrestlingapp.entity.UpdateModel;
import com.rakesh.wrestlingapp.entity.Videos;
import com.rakesh.wrestlingapp.exceptions.ControllerException;
import com.rakesh.wrestlingapp.exceptions.ResourceNotFound;
import com.rakesh.wrestlingapp.repository.VideoRepository;
import com.rakesh.wrestlingapp.service.UserService;
import com.rakesh.wrestlingapp.service.video.FileInterface;
import com.rakesh.wrestlingapp.service.video.VideoInterface;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
//@CrossOrigin("http://localhost:3000")
public class VideoController {

	@Value("${project.video}")
	private String path;

	@Autowired
	private VideoInterface service;

	@Autowired
	private FileInterface fileSevice;

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<?> saveVideo(@RequestBody Videos video) {
		try {
			Videos saveVideos = service.createPost(video);
			return new ResponseEntity<Videos>(saveVideos, HttpStatus.OK);
		} catch (ResourceNotFound e) {
			ControllerException controllerException = new ControllerException(e.getErrorCode(),
					e.getErrorMessage() + e.getMessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		}
	}

	//1. get Video by id ;
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getDataById(@PathVariable Integer id) {
		try {
			Videos video = service.getVideosById(id);
			return new ResponseEntity<Videos>(video, HttpStatus.CREATED);

		} catch (ResourceNotFound e) {
			ControllerException controllerException = new ControllerException(e.getErrorCode(),
					e.getErrorMessage() + e.getMessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException controllerException = new ControllerException("504", "id not found");
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		}
	}

	//2. Get List Of videos .
	@SuppressWarnings("unused")
	@GetMapping("/all")
	public ResponseEntity<?> getListofData() {
		Videos videos = new Videos();
		try {
			if (videos == null) {
				throw new ResourceNotFound("404", "data is null");
			}
			List<Videos> v = service.getAllVideos();
			return new ResponseEntity<List<Videos>>(v, HttpStatus.CREATED);
		} catch (Exception e) {
			ControllerException controllerException = new ControllerException("404", "Empty database is found");
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		}
	}

	//3. Posting Video api .
	@PostMapping("/upload/{id}")
	public ResponseEntity<Videos> uploadVideo(@RequestParam("video") MultipartFile video, @PathVariable Integer id)
			throws IOException {
		Videos v = this.service.getVideosById(id);
		FileModel fileModel = this.fileSevice.uploadVideo(path, video);
		v.setVideoName(fileModel.getVideoFileName());
		Videos uploadVideo = this.service.updatePost(v, id);
		return new ResponseEntity<Videos>(uploadVideo, HttpStatus.OK);
	}

	//4.To play video .
	@GetMapping(value = "/play/{id}", produces = MediaType.ALL_VALUE)
	public void downloadImage(@PathVariable int id, HttpServletResponse response) throws IOException {
		Optional<Videos> video = videoRepository.findById(id);
		InputStream resource = this.fileSevice.getResource(path, video.get().getVideoName(), id);
		response.setContentType(MediaType.ALL_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

	//5. Delete videos by id .
	@DeleteMapping("/{id}")
	public String deleteVideo(@PathVariable Integer id) throws IOException {
		Optional<Videos> video = videoRepository.findById(id);
		Path exactPath = Paths.get(path + File.separator + video.get().getVideoName());
		System.out.println(exactPath);
		try {
			Files.deleteIfExists(exactPath);

		} catch (Exception e1) {
			e1.getMessage();
			System.out.println(e1.getMessage()+"can not delete now ");
		}
		this.service.deleteVideos(id);

		return "video deleted successfully";
	}
	
	//Update DataModel of Video .
	@PutMapping("/update/{id}")
	public ResponseEntity<UpdateModel> setVideoData(@RequestBody UpdateModel updateModel, @PathVariable int id){
		try {
		service.updateModel(updateModel, id);
		return new ResponseEntity<UpdateModel>(updateModel, HttpStatus.OK);	
		}catch(Exception e) {
			throw new ResourceNotFound("404","user id not found");
		}
	}


	@PostMapping("/save-and-upload")
	public ResponseEntity<?> saveAndUploadVideo(
			@RequestParam("video") MultipartFile videoFile,
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("userId") Integer userId) {
		try {
			// Step 1: Save video metadata to the database

			OurUsers user = userService.getUserByIdV1(userId);
			if (user == null) {
				throw new ResourceNotFound("404", "User not found with ID: " + userId);
			}
			Videos video = new Videos();
			video.setTitle(title);
			video.setDescription(description);
//			video.setUser(user);
			Videos savedVideo = service.createPost(video);

			// Step 2: Upload video file and update the saved video record
			FileModel fileModel = fileSevice.uploadVideo(path, videoFile);
			savedVideo.setVideoName(fileModel.getVideoFileName());
			Videos updatedVideo = service.updatePost(savedVideo, savedVideo.getId());
			return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
		} catch (ResourceNotFound e) {
			ControllerException controllerException = new ControllerException(e.getErrorCode(), e.getErrorMessage() + e.getMessage());
			return new ResponseEntity<>(controllerException, HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			ControllerException controllerException = new ControllerException("500", "File upload failed: " + e.getMessage());
			return new ResponseEntity<>(controllerException, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/all/{userId}")
	public ResponseEntity<?> getVideosByUserId(@PathVariable Integer userId) {
		try {
			List<Videos> userVideos = service.getVideosByUserId(userId);
			if (userVideos.isEmpty()) {
				throw new ResourceNotFound("404", "No videos found for the given user");
			}
			return new ResponseEntity<List<Videos>>(userVideos, HttpStatus.OK);
		} catch (ResourceNotFound e) {
			ControllerException controllerException = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<>(controllerException, HttpStatus.BAD_REQUEST);
		}
	}
}
