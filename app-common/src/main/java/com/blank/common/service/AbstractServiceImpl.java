package com.blank.common.service;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractServiceImpl<E, K extends Serializable & Comparable<K>> implements AbstractService<E, K> {
	protected JpaRepository<E, K> repository;

	@PersistenceContext
	private EntityManager em;

	public AbstractServiceImpl(JpaRepository repository) {
		this.repository = repository;
	}

	public Page<E> findAll(Pageable pageable) {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		// CriteriaQuery<Owner> criteria = builder.createQuery(Owner.class);
		// Root<Owner> personRoot = criteria.from(Owner.class);

		// criteria.multiselect(personRoot.get("city"), personRoot.get("address"));
		// criteria.where(builder.equal(personRoot.get("address"), "110"));

		// Predicate menRestriction = builder.and(builder.equal(men.get(Person_.gender),
		// Gender.MALE),
		// builder.equal(men.get(Person_.relationshipStatus),
		// RelationshipStatus.SINGLE));
		// Predicate womenRestriction =
		// builder.and(builder.equal(women.get(Person_.gender), Gender.FEMALE),
		// builder.equal(women.get(Person_.relationshipStatus),
		// RelationshipStatus.SINGLE));
		// query.where(builder.and(menRestriction, womenRestriction));

		// TypedQuery<Owner> query = this.em.createQuery(criteria);

		// List<Owner> people = query.getResultList();
		// System.out.println("criteria query is " + people);
		return this.repository.findAll(pageable);
	}

	public E save(E e) {
		return this.repository.save(e);
	}

	public Optional<E> findOne(K id) {
		Optional<E> op = this.repository.findById(id);
		return op;
	}

	public void update(E entity) {
		this.repository.saveAndFlush(entity);

	}

	public void delete(K id) {
		this.repository.deleteById(id);
	}
}
