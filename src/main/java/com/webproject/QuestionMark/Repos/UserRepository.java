package com.webproject.QuestionMark.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.QuestionMark.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);

}
