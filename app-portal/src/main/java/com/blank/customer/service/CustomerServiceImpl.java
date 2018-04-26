package com.blank.customer.service;

import org.springframework.stereotype.Service;

import com.blank.common.service.AbstractServiceImpl;
import com.blank.customer.domain.Customer;
import com.blank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl extends AbstractServiceImpl<Customer, Long> implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository repository) {
		super(repository);
		this.customerRepository = repository;

	}
}
