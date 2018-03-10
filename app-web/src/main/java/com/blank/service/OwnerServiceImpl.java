package com.blank.service;


import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blank.common.service.AbstractServiceImpl;
import com.blank.model.Owner;
import com.blank.repository.OwnerRepository;

@Service("ownerService")
@Transactional
public class OwnerServiceImpl extends  AbstractServiceImpl<Owner, Long> implements OwnerService{
	private final OwnerRepository ownerRepository;
	public OwnerServiceImpl(OwnerRepository repository) {
		super(repository);
		ownerRepository = repository;
	}

	@Override
	public Page<Owner> findAllByLastNameIgnoringCase(String lastName, Pageable pageable) throws DataAccessException {
//		Owner owner = new Owner();                        
//		owner.setLastName(lastName);                        
//		Example<Owner> example = Example.of(owner);
		return this.ownerRepository.queryByLastNameContaining(lastName, pageable);
	}

}
