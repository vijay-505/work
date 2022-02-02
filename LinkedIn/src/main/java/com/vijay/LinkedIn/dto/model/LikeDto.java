package com.vijay.LinkedIn.dto.model;

import com.vijay.LinkedIn.entity.React;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDto {
	
	private int likeId;
	private React react;
	private String userProfileUrl;

}
