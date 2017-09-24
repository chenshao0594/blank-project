package sample.ui.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sample.ui.repository.OwnerRepository;

public abstract class AbstractServiceImpl<E, K extends Serializable & Comparable<K>> implements AbstractService<E, K> {
	protected JpaRepository<E, K> repository;
	public AbstractServiceImpl(JpaRepository repository) {
		this.repository = repository;
	}

	public Page<E> findAll(Pageable pageable){
		return this.repository.findAll(pageable);
	}

}
