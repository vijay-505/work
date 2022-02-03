package com.vijay.LinkedIn.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActivityDto {
	
	private String reason;
	private String email;
	private PostDto postDto;
	private CommentDto commentDto;
	private LikeDto likeDto;

}
