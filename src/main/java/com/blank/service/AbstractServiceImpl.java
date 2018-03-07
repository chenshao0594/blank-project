package com.blank.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractServiceImpl<E, K extends Serializable & Comparable<K>> implements AbstractService<E, K> {
	protected JpaRepository<E, K> repository;

	public AbstractServiceImpl(JpaRepository repository) {
		this.repository = repository;
	}

	public Page<E> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	public E save(E e) {
		return this.repository.save(e);
	}

	public Optional<E> findOne(K id) {
		Optional<E> op = null;
		return op;
	}

	public void update(E entity) {
		this.repository.saveAndFlush(entity);

	}

	public void delete(K id) {
		this.repository.deleteById(id);
	}
}
