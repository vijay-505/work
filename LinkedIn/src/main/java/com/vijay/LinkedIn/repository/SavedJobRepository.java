package com.vijay.LinkedIn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.SavedJobEntity;

@Repository
public interface SavedJobRepository extends JpaRepository<SavedJobEntity, Integer>{

	Optional<SavedJobEntity> findByUserEmailAndJobJobId(String email, int jobId);

}
