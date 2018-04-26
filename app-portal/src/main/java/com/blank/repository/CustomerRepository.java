package com.blank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blank.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
