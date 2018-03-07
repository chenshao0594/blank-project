package com.blank.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blank.model.Contribution;
import com.blank.repository.ContributionRepository;

@Service("contributionService")
@Transactional
public class ContributionServiceImpl extends  AbstractServiceImpl<Contribution, Long> implements ContributionService{
	private final ContributionRepository repository;
	public ContributionServiceImpl(ContributionRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
