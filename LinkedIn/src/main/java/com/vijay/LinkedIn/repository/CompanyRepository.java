package com.vijay.LinkedIn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer>{

	Optional<CompanyEntity> findByEmail(String email);

	List<CompanyEntity> findByNameContains(String name);

}
