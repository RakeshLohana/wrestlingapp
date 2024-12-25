package com.rakesh.wrestlingapp.repository;

import com.rakesh.wrestlingapp.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Videos, Integer> {

    List<Videos> findAllByUser_Id(Integer userId); // Finds videos by user ID







}
