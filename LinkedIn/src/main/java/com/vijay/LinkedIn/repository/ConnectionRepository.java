package com.vijay.LinkedIn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.ConnectionEntity;
import com.vijay.LinkedIn.entity.ConnectionStatus;

@Repository
public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Integer> {

	ConnectionEntity findByConnectionEmailAndUserEmail(
			String receiverEmail, String senderEmail);

//	List<ConnectionEntity> findByConnectionEmailAndStatus(String email, ConnectionStatus request);

	List<ConnectionEntity> findByUserEmailAndStatus(
			String senderEmail, ConnectionStatus accept);

}
