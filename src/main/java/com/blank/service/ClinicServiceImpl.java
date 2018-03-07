/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blank.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blank.model.Owner;
import com.blank.model.Pet;
import com.blank.model.Visit;
import com.blank.repository.AuthorityRepository;
import com.blank.repository.OwnerRepository;
import com.blank.repository.PetRepository;
import com.blank.repository.UserRepository;
import com.blank.repository.VisitRepository;

@Service("clinicService")
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private VisitRepository visitRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	@Transactional(readOnly = true)
	public Owner findOwnerById(long id) throws DataAccessException {
		Optional<Owner> optional = ownerRepository.findById(id);
		return optional.get();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Owner> findOwners() throws DataAccessException {
		return ownerRepository.findAll();
	}

	@Override
	@Transactional
	public void saveOwner(Owner owner) throws DataAccessException {
		ownerRepository.save(owner);
	}

	@Override
	@Transactional
	public void saveVisit(Visit visit) throws DataAccessException {
		visitRepository.save(visit);
	}

	@Override
	@Transactional(readOnly = true)
	public Pet findPetById(long id) throws DataAccessException {
		return petRepository.findById(id);
	}

	@Override
	@Transactional
	public void savePet(Pet pet) throws DataAccessException {
		petRepository.save(pet);
	}

}
