package com.vijay.LinkedIn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.ExperienceEntity;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceEntity, Integer>{

}
