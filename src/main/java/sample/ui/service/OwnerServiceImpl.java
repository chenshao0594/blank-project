package sample.ui.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.ui.model.Owner;
import sample.ui.repository.OwnerRepository;

@Service("ownerService")
@Transactional
public class OwnerServiceImpl extends  AbstractServiceImpl<Owner, Long> implements OwnerService{
	private final OwnerRepository ownerRepository;
	public OwnerServiceImpl(OwnerRepository repository) {
		super(repository);
		ownerRepository = repository;
	}

}
