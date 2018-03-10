package com.blank.common.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.blank.common.domain.DomainMetaBuilder;
import com.blank.common.domain.DomainMetadata;
import com.blank.common.service.AbstractService;

public abstract class AbstractController<E, K> {
	private final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

	private final AbstractService<E, K> service;

	private final Class entityClass;

	private final String sectionKey;

	public AbstractController(AbstractService<E, K> service, Class entityClass) {
		this.service = service;
		this.entityClass = entityClass;
		this.sectionKey = entityClass.getSimpleName().toLowerCase();
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping()
	public String listEntities(Model model, Pageable pageable) {
		Page<E> page = this.service.findAll(pageable);
		DomainMetadata metadata = DomainMetaBuilder.build(this.entityClass);
		model.addAttribute("domainMetadata", metadata);
		model.addAttribute("page", page);
		return "domain/list";
	}

	@PostMapping()
	public String processCreationForm(E entity, Model model, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			DomainMetadata metadata = DomainMetaBuilder.build(this.entityClass);
			model.addAttribute("domainMetadata", metadata);
			return "domain/dialog";
		} else {
			E en = this.service.save(entity);
			DomainMetadata metadata = DomainMetaBuilder.build(this.entityClass);
			model.addAttribute("domainMetadata", metadata);
			status.setComplete();
			model.addAttribute("item", en);
			return "domain/detail";
		}
	}

	@GetMapping(value = "/new")
	public String initCreationForm(Model model) throws InstantiationException, IllegalAccessException {
		model.addAttribute(entityClass.newInstance());
		DomainMetadata metadata = DomainMetaBuilder.build(this.entityClass);
		model.addAttribute("domainMetadata", metadata);
		return "domain/dialog";
	}

	@GetMapping("/{id}")
	public ModelAndView showOne(@PathVariable("id") K id) {
		ModelAndView mav = new ModelAndView("domain/detail");
		DomainMetadata metadata = DomainMetaBuilder.build(this.entityClass);
		mav.addObject("domainMetadata", metadata);
		Optional<E> entity = this.service.findOne(id);
		mav.addObject("item", entity.get());
		return mav;
	}

	@GetMapping(value = "/{id}/edit")
	public String initUpdateOwner(@PathVariable("id") K id, Model model) {
		DomainMetadata metadata = DomainMetaBuilder.build(this.entityClass);
		model.addAttribute("domainMetadata", metadata);
		E entity = this.service.findOne(id).get();
		model.addAttribute(entity);
		return "domain/dialog";
	}

	@PostMapping(value = "/{id}/edit")
	public String processUpdateOwner(E entity, BindingResult result, SessionStatus status, Model model) {
		if (result.hasErrors()) {
			DomainMetadata metadata = DomainMetaBuilder.build(this.entityClass);
			model.addAttribute("domainMetadata", metadata);
			return "domain/dialog";
		} else {
			this.service.update(entity);
			status.setComplete();
			model.addAttribute(entity);
			return "domain/detail";
		}
	}
}
