package com.blank.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.blank.common.controller.AbstractController;
import com.blank.customer.domain.Customer;
import com.blank.customer.service.CustomerService;

@Controller
@SessionAttributes(types = Customer.class)
@RequestMapping(value = "/customers")
public class CustomerController extends AbstractController<Customer, Long> {

	@Autowired
	private CustomerService customerService;

	public CustomerController(CustomerService service) {
		super(service, Customer.class);
		this.customerService = service;
	}

}
