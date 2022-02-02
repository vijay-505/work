package com.vijay.LinkedIn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.LinkedIn.entity.LikeEntity;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer>{

	Optional<LikeEntity> findByUserEmailAndPostPostId(String email, int postId);

}
