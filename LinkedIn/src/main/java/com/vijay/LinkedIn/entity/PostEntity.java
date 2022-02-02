package com.vijay.LinkedIn.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="post")
public class PostEntity {
	
	@Id
	@GeneratedValue
	private int postId;
	private Date createdDate;
	private Date updatedDate;
	private String description;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.REMOVE)
	private List<LinkEntity> links = new ArrayList<>();;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private UserEntity user;
	
	@OneToMany(mappedBy="post")
	private List<LikeEntity> likes = new ArrayList<>();
	
	@OneToMany(mappedBy="post")
	private List<CommentEntity> comments = new ArrayList<>();

	//tag
}
