package com.vijay.LinkedIn.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comment")
public class CommentEntity {
	
	@Id
	@GeneratedValue
	private int commentId;
	private Date date;
	private String content;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private PostEntity post;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private UserEntity user;	

}
