package com.vijay.LinkedIn.dto.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;
	private boolean edited;
	private String userEmail;
	private String userProfileUrl;
	private Date date;
	private String description;
	private List<LinkDto> links;
	private long totalLikes;
	private String likesUrl;
	private long totalComments;
	private String commentsUrl;
	

}
