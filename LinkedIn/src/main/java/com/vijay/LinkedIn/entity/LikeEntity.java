package com.vijay.LinkedIn.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="post_like")
public class LikeEntity {
	
	@Id
	@GeneratedValue
	private int likeId;
	@Enumerated(EnumType.STRING)
	private React react;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private PostEntity post;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private UserEntity user;

}
