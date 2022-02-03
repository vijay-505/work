package com.vijay.LinkedIn.dto.model;

import java.util.Date;

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
	private Date date;
	private String userProfileUrl;

}
