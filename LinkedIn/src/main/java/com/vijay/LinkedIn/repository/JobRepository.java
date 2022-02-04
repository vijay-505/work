package com.vijay.LinkedIn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer>{

}
