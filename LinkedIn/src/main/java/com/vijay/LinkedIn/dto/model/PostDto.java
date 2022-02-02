package com.vijay.LinkedIn.dto.model;

import java.util.Date;
import java.util.List;

import com.vijay.LinkedIn.entity.CommentEntity;
import com.vijay.LinkedIn.entity.LikeEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private int postId;
	private String userEmail;
	private String UserProfileUrl;
	private Date createdDate;
	private Date updatedDate;
	private String description;
	private List<LinkDto> links;
	private List<LikeEntity> likes;
	private List<CommentDto> comments; 
	

}
