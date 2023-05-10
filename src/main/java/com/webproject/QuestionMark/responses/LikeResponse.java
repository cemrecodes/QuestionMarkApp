package com.webproject.QuestionMark.responses;

import com.webproject.QuestionMark.Entities.Like;

import lombok.Data;

@Data
public class LikeResponse {

	Long id;
	Long userId;
	Long postId;
	
	public LikeResponse(Like entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.postId = entity.getPost().getId();
	} 
}