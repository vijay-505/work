package com.vijay.LinkedIn.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer>{

	List<JobEntity> findByTitleContains(String title);

	List<JobEntity> findByTitleContainsAndLocationContains(
			String title, String location);

	List<JobEntity> findByTitleContainsAndDateBetween(String title, Date start_date, Date end_date);

}
