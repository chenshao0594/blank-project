package com.blank.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blank.model.Comment;
import com.blank.repository.CommentRepository;

@Service("commentService")
@Transactional
public class CommentServiceImpl extends  AbstractServiceImpl<Comment, Long> implements CommentService{
	private final CommentRepository repository;
	public CommentServiceImpl(CommentRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
