package com.vijay.LinkedIn.entity;

import java.util.Date;

import javax.persistence.Entity;
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
	private Date createdDate;
	private String content;
	
	@ManyToOne
	private PostEntity post;
	
	@ManyToOne
	private UserEntity user;	

}
