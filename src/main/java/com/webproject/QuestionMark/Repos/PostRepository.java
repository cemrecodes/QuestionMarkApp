package com.webproject.QuestionMark.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.QuestionMark.Entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

}
