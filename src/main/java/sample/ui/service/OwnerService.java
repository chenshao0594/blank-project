package sample.ui.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sample.ui.model.Owner;

public interface OwnerService extends AbstractService<Owner, Long>{
	public Page<Owner> findAllByLastNameIgnoringCase(String lastName, Pageable pageable) throws DataAccessException;


}
