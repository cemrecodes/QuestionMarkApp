package com.webproject.QuestionMark.responses;

import com.webproject.QuestionMark.Entities.Post;

public class PostResponse {
	
	Long id;
	Long userId;
	String userName;
	String title;
	String text;
	
	public PostResponse(Post entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUsername();
		this.title = entity.getTitle();
		this.text = entity.getText();
	}
	
	
}
