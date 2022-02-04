package com.vijay.LinkedIn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.AppliedJobEntity;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJobEntity, Integer>{

	Optional<AppliedJobEntity> findByUserEmailAndJobJobId(String email, int jobId);

}
