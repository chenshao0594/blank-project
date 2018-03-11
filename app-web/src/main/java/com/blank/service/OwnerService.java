package com.blank.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blank.common.service.AbstractService;
import com.blank.domain.Owner;

public interface OwnerService extends AbstractService<Owner, Long>{
	public Page<Owner> findAllByLastNameIgnoringCase(String lastName, Pageable pageable) throws DataAccessException;


}
