package com.vijay.LinkedIn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.FollowerEntity;

@Repository
public interface FollowerRepository extends JpaRepository<FollowerEntity, Integer>{

	Optional<FollowerEntity> findByUserEmailAndCompanyEmail(
			String companyEmail, String userEmail);

}
