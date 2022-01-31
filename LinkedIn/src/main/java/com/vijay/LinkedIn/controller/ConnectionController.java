package com.vijay.LinkedIn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("connections/{email1}/{email2}")
	public ResponseEntity<String> sendInvitation(@PathVariable String email1,
			@PathVariable String email2) {
		return connectionService.sendInvitation(email1, email2);
		
	}
	
	@GetMapping("connections/{email}")
	public ResponseEntity<List<ConnectionDto>> viewAllInvitation(
			@PathVariable String email) {
		return connectionService.viewAllInvitation(email);
		
	}
	
	@PutMapping("connections/{email1}/{email2}/accept")
	public ResponseEntity<String> acceptInvitation(@PathVariable String email1,
			@PathVariable String email2) {
		return connectionService.acceptInvitation(email1, email2);
	}

	@PutMapping("connections/{email1}/{email2}/cancel")
	public ResponseEntity<String> cancelInvitation(@PathVariable String email1,
			@PathVariable String email2) {
		return connectionService.cancelInvitation(email1, email2);
	}
}
