package com.blank.common.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;

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

import com.blank.common.domain.frw.DomainMeta;
import com.blank.common.domain.frw.DomainMetaBuilder;
import com.blank.common.service.AbstractService;

public abstract class AbstractController<E, K> {
	private final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

	private final AbstractService<E, K> service;

	private final Class entityClass;
	private DomainMeta metadata;

	public AbstractController(AbstractService<E, K> service, Class entityClass) {
		this.service = service;
		this.entityClass = entityClass;
	}

	@PostConstruct
	private void initDomainMeta() {
		this.metadata = DomainMetaBuilder.build(this.entityClass);
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping()
	public String listEntities(Model model, Pageable pageable) {
		Page<E> page = this.service.findAll(pageable);
		model.addAttribute("domainMetadata", this.metadata);
		model.addAttribute("page", page);
		return "domain/list";
	}

	@PostMapping()
	public String processCreationForm(E entity, Model model, BindingResult result, SessionStatus status) {
		model.addAttribute("domainMetadata", this.metadata);
		if (result.hasErrors()) {
			return "domain/dialog";
		} else {
			E en = this.service.save(entity);
			status.setComplete();
			model.addAttribute("item", en);
			return "domain/detail";
		}
	}

	@GetMapping(value = "/new")
	public String initCreationForm(Model model) throws InstantiationException, IllegalAccessException {
		model.addAttribute(entityClass.newInstance());
		model.addAttribute("domainMetadata", this.metadata);
		return "domain/dialog";
	}

	@GetMapping("/{id}")
	public ModelAndView showOne(@PathVariable("id") K id) {
		ModelAndView mav = new ModelAndView("domain/detail");
		mav.addObject("domainMetadata", this.metadata);
		Optional<E> entity = this.service.findOne(id);
		mav.addObject("item", entity.get());
		return mav;
	}

	@GetMapping(value = "/{id}/edit")
	public String initUpdateOwner(@PathVariable("id") K id, Model model) {
		model.addAttribute("domainMetadata", this.metadata);
		E entity = this.service.findOne(id).get();
		model.addAttribute(entity);
		return "domain/dialog";
	}

	@PostMapping(value = "/{id}/edit")
	public String processUpdateOwner(E entity, BindingResult result, SessionStatus status, Model model) {
		model.addAttribute("domainMetadata", this.metadata);
		if (result.hasErrors()) {
			return "domain/dialog";
		} else {
			this.service.update(entity);
			status.setComplete();
			model.addAttribute(entity);
			return "domain/detail";
		}
	}
}
