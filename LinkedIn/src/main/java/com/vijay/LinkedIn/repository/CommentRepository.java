package com.vijay.LinkedIn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer>{

}
