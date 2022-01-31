package com.vijay.LinkedIn.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vijay.LinkedIn.dto.model.ConnectionDto;

public interface ConnectionService {

	ResponseEntity<String> sendInvitation(String email1, String email2);

	ResponseEntity<List<ConnectionDto>> viewAllInvitation(String email);

	ResponseEntity<String> acceptInvitation(String email1, String email2);

	ResponseEntity<String> cancelInvitation(String email1, String email2);

}
