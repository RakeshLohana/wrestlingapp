package com.rakesh.wrestlingapp.repository;

import com.rakesh.wrestlingapp.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Videos, Integer> {
	
	
	
	
	

	

}
