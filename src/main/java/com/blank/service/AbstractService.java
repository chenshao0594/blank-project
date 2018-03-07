package com.blank.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<E, K> {
	Page<E> findAll(Pageable pageable);

	E save(E entity);

	void update(E entity);

	void delete(K id);

	Optional<E> findOne(K id);
}
