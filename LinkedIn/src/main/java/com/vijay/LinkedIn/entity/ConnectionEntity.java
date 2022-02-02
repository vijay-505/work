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
@Table(name="connection")
public class ConnectionEntity {
	
	@Id
	@GeneratedValue
	private int id;
	private String connectionEmail;
	private String connectionUrl;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private UserEntity user;
	
	@Enumerated(EnumType.STRING)
	private ConnectionStatus status;
	

}
