package sample.ui.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.ui.model.Contribution;
import sample.ui.repository.ContributionRepository;

@Service("contributionService")
@Transactional
public class ContributionServiceImpl extends  AbstractServiceImpl<Contribution, Long> implements ContributionService{
	private final ContributionRepository repository;
	public ContributionServiceImpl(ContributionRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
