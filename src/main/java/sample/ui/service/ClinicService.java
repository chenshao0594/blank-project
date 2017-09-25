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
package sample.ui.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import sample.ui.model.Owner;
import sample.ui.model.Pet;
import sample.ui.model.PetType;
import sample.ui.model.Visit;

/**
 * Mostly used as a facade for all controllers.
 *
 * @author Michael Isvy
 * @author Arnaldo Piccinelli
 */
public interface ClinicService {

	

	// Owner
	public Owner findOwnerById(long id) throws DataAccessException;

	public void saveOwner(Owner owner) throws DataAccessException;


	public Collection<Owner> findOwners() throws DataAccessException;

	// Pet
	public Pet findPetById(long id) throws DataAccessException;

	public void savePet(Pet pet) throws DataAccessException;

	// Visit
	public void saveVisit(Visit visit) throws DataAccessException;

}
