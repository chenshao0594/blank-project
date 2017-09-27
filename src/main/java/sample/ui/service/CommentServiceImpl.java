package sample.ui.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.ui.model.Comment;
import sample.ui.repository.CommentRepository;

@Service("commentService")
@Transactional
public class CommentServiceImpl extends  AbstractServiceImpl<Comment, Long> implements CommentService{
	private final CommentRepository repository;
	public CommentServiceImpl(CommentRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
