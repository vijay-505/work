package com.vijay.LinkedIn.dto.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
	
	private int commentId;
	private Date date;
	@NotNull
	private String content;
	private String userProfileUrl;

}
