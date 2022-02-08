package com.vijay.LinkedIn.dto.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.vijay.LinkedIn.entity.React;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDto {
	
	private int likeId;
	@NotNull
	private React react;
	private Date date;
	private String userProfileUrl;

}
