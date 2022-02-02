package com.vijay.LinkedIn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.LinkEntity;

@Repository
public interface LinkRepository extends JpaRepository<LinkEntity, Integer>{

}
