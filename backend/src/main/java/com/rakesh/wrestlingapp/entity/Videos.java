package com.rakesh.wrestlingapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="video")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Videos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private String title ;
	private String description ;
	private String tags ;
	private String videoName ;
	private Date addedDate ;

}
