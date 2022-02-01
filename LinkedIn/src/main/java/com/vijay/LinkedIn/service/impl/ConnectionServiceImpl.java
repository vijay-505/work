package com.vijay.LinkedIn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vijay.LinkedIn.dto.mapper.ConnectionMapper;
import com.vijay.LinkedIn.dto.model.ConnectionDto;
import com.vijay.LinkedIn.entity.ConnectionEntity;
import com.vijay.LinkedIn.entity.ConnectionStatus;
import com.vijay.LinkedIn.entity.UserEntity;
import com.vijay.LinkedIn.repository.ConnectionRepository;
import com.vijay.LinkedIn.repository.UserRepository;
import com.vijay.LinkedIn.service.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService{
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConnectionMapper connectionMapper;

	@Override
	public ResponseEntity<String> sendInvitation(
			String senderEmail, String receiverEmail) {
		ConnectionEntity connectionSent = connectionRepository
				.findByConnectionEmailAndUserEmail(receiverEmail,senderEmail);
		ConnectionEntity connectionReceived = connectionRepository
				.findByConnectionEmailAndUserEmail(senderEmail,receiverEmail);
		if(connectionSent!=null || connectionReceived!=null) {
			return new ResponseEntity<>("Connection request already available.",
					HttpStatus.CREATED);
		}
		
		UserEntity receiverUser = userRepository.findByEmail(receiverEmail).get();
		UserEntity senderUser = userRepository.findByEmail(senderEmail).get();
		
		ConnectionEntity connection = new ConnectionEntity();
		connection.setConnectionEmail(senderEmail);
		connection.setUser(receiverUser);
		connection.setStatus(ConnectionStatus.REQUEST);
		String connectionUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("users/"+senderUser.getEmail())
				.toUriString();
		connection.setConnectionUrl(connectionUrl);
		connectionRepository.save(connection);
		return new ResponseEntity<>("Connection request sent.",
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<ConnectionDto>> viewAllInvitation(String email) {
		List<ConnectionEntity> connectionList = 
				connectionRepository.findByUserEmailAndStatus(
						email, ConnectionStatus.REQUEST);
		return new ResponseEntity<>(
				connectionMapper.toConnectionDtos(connectionList),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> acceptInvitation(
			String receiverEmail, String senderEmail) {
		UserEntity receiverUser = userRepository.findByEmail(receiverEmail).get();
		UserEntity senderUser = userRepository.findByEmail(senderEmail).get();
		
		ConnectionEntity receiverConnection = connectionRepository
				.findByConnectionEmailAndUserEmail(senderEmail,receiverEmail);
		receiverConnection.setStatus(ConnectionStatus.ACCEPT);
		
		receiverUser.setTotalConnections(receiverUser.getConnections().size());//this connection already available
		connectionRepository.save(receiverConnection);
		
		ConnectionEntity senderConnection = new ConnectionEntity();
		senderConnection.setConnectionEmail(receiverEmail);
		senderConnection.setUser(senderUser);
		senderConnection.setStatus(ConnectionStatus.ACCEPT);
		String connectionUrl2 = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("users/"+receiverUser.getEmail())
				.toUriString();
		senderConnection.setConnectionUrl(connectionUrl2);
		
		senderUser.setTotalConnections(senderUser.getConnections().size()+1L);
		connectionRepository.save(senderConnection);
		
		return new ResponseEntity<>("Connection request accepted.",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> cancelInvitation(
			String receiverEmail, String senderEmail) {
		ConnectionEntity connection = connectionRepository 
				.findByConnectionEmailAndUserEmail(senderEmail,receiverEmail);
		connection.setStatus(ConnectionStatus.CANCEL);
		connectionRepository.save(connection);
		connectionRepository.delete(connection);
		return new ResponseEntity<>("Connection request cancel.",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> removeConnection(String senderEmail, String receiverEmail) {
		ConnectionEntity senderConnection = connectionRepository 
				.findByConnectionEmailAndUserEmail(receiverEmail,senderEmail);
		ConnectionEntity receiverConnection = connectionRepository 
				.findByConnectionEmailAndUserEmail(senderEmail,receiverEmail);
		UserEntity senderUser = userRepository.findByEmail(senderEmail).get();
		UserEntity receiverUser = userRepository.findByEmail(senderEmail).get();
		senderUser.setTotalConnections(senderUser.getTotalConnections()-1L);
		connectionRepository.delete(senderConnection);
		receiverUser.setTotalConnections(receiverUser.getTotalConnections()-1L);
		connectionRepository.delete(receiverConnection);
		return new ResponseEntity<>("Connection removed.",HttpStatus.OK);
	}

}
