package com.vijay.LinkedIn.dto.model;

import com.vijay.LinkedIn.entity.ConnectionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionDto {
	private int id;
	private String connectionEmail;
	private String connectionUrl;
	private String userEmail;
	private ConnectionStatus status;
}
