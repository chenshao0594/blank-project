package sample.ui.web;

import javax.validation.Valid;

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

import sample.ui.service.AbstractService;

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
	public String listEntities(Model model,Pageable pageable ) {
		Page<E> page = this.service.findAll(pageable);
		model.addAttribute("page", page);
		return this.sectionKey + "/list";
	}

	@PostMapping()
	public String processCreationForm(@Valid E entity,Model model, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return this.sectionKey + "/dialog";
		} else {
			E en = this.service.save(entity);
			status.setComplete();
			model.addAttribute(en);
			return this.sectionKey + "/detail";
		}
	}

	@GetMapping(value = "/new")
	public String initCreationForm(Model model) throws InstantiationException, IllegalAccessException {
		model.addAttribute(entityClass.newInstance());
		return this.sectionKey + "/dialog";
	}
	
	@GetMapping("/{id}")
	public ModelAndView showOne(@PathVariable("id") K id) {
		ModelAndView mav = new ModelAndView(this.sectionKey+"/detail");
		mav.addObject(this.service.findOne(id));
		return mav;
	}
	
	@GetMapping(value = "/{id}/edit")
	public String initUpdateOwner(@PathVariable("id") K id, Model model) {
		E entity = this.service.findOne(id);
		model.addAttribute(entity);
		return this.sectionKey+"/dialog";
	}
	
	@PostMapping(value = "/{id}/edit")
	public String processUpdateOwner(@Valid E entity, BindingResult result, SessionStatus status,Model model) {
		if (result.hasErrors()) {
			return this.sectionKey+"/dialog";
		} else {
			this.service.update(entity);
			status.setComplete();
			model.addAttribute(entity);
			return this.sectionKey+"/detail";
		}
	}
}
