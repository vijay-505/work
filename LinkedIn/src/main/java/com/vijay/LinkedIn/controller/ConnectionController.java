package com.vijay.LinkedIn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.LinkedIn.dto.model.ConnectionDto;
import com.vijay.LinkedIn.service.ConnectionService;

@RestController
public class ConnectionController {
	
	@Autowired
	private ConnectionService connectionService;
	
	@PostMapping("connections/{senderEmail}/{receiverEmail}")
	public ResponseEntity<String> sendInvitation(@PathVariable String senderEmail,
			@PathVariable String receiverEmail) {
		return connectionService.sendInvitation(senderEmail, receiverEmail);
		
	}
	
	@GetMapping("connections/{email}/request")
	public ResponseEntity<List<ConnectionDto>> viewAllInvitation(
			@PathVariable String email) {
		return connectionService.viewAllInvitation(email);
		
	}
	
	@PutMapping("connections/{receiverEmail}/{senderEmail}/accept")
	public ResponseEntity<String> acceptInvitation(@PathVariable String receiverEmail,
			@PathVariable String senderEmail) {
		return connectionService.acceptInvitation(receiverEmail, senderEmail);
	}

	@PutMapping("connections/{receiverEmail}/{senderEmail}/cancel")
	public ResponseEntity<String> cancelInvitation(@PathVariable String receiverEmail,
			@PathVariable String senderEmail) {
		return connectionService.cancelInvitation(receiverEmail, senderEmail);
	}
	
	@DeleteMapping("connections/{senderEmail}/{receiverEmail}")
	public ResponseEntity<String> removeConnection(@PathVariable String senderEmail,
			@PathVariable String receiverEmail) {
		return connectionService.removeConnection(senderEmail, receiverEmail);
	}
	
	@GetMapping("connections/{email}")
	public  ResponseEntity<List<ConnectionDto>> retrieveAllConnection(
			@PathVariable String email){
		return connectionService.retrieveAllConnection(email);
	}
}
