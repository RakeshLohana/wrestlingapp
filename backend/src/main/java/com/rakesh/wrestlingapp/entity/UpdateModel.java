package com.rakesh.wrestlingapp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UpdateModel {
	
	private int id ;
	private String tags ;
	private String description ;
	private String title ;

}
