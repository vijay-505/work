package com.vijay.LinkedIn.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vijay.LinkedIn.dto.model.ConnectionDto;

public interface ConnectionService {

	ResponseEntity<String> sendInvitation(String senderEmail, String receiverEmail);

	ResponseEntity<List<ConnectionDto>> viewAllInvitation(String email);

	ResponseEntity<String> acceptInvitation(String receiverEmail, String senderEmail);

	ResponseEntity<String> cancelInvitation(String receiverEmail, String senderEmail);

	ResponseEntity<String> removeConnection(String senderEmail, String receiverEmail);

	ResponseEntity<List<ConnectionDto>> retrieveAllConnection(String email);

}
