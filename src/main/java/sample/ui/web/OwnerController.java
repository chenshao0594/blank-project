/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.ui.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import sample.ui.model.Owner;
import sample.ui.service.ClinicService;
import sample.ui.service.OwnerService;
@Controller
@SessionAttributes(types = Owner.class)
@RequestMapping(value = "/owners")
public class OwnerController extends AbstractController<Owner, Long> {

	

	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private OwnerService ownerService;

	public OwnerController(OwnerService service) {
		
		super(service, Owner.class);
		this.ownerService = service;
	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping()
	public String listEntities(Model model, Pageable pageable) {
		Page<Owner> page = this.ownerService.findAll(pageable);
		model.addAttribute("page", page);
		return "owners/ownersList";
	}


	@GetMapping(value = "/new")
	public String initCreationForm(Model model) {
		Owner owner = new Owner();
		model.addAttribute(owner);
		return "owners/ownerForm";
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "owners/ownerForm";
		} else {
			this.clinicService.saveOwner(owner);
			status.setComplete();
			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping(value = "/find")
	public String initFindForm(Model model) {
		model.addAttribute("owner", new Owner());
		return "owners/findOwners";
	}


	@GetMapping(value = "/list")
	public String processFindForm(Owner owner, BindingResult result, Model model, HttpSession session, Pageable pageable) {
		Page<Owner> page = null;

		// find owners by last name
		if (StringUtils.isEmpty(owner.getLastName())) {
			// allow parameterless GET request for /owners to return all records
			page = this.ownerService.findAll(pageable);
		} else {
			page = this.ownerService.findAllByLastNameIgnoringCase(owner.getLastName(), pageable);
		}

		if (page.getContent().size() < 1) {
			// no owners found
			result.rejectValue("lastName", "notFound", new Object[] { owner.getLastName() }, "not found");
			return "owners/findOwners";
		}

		session.setAttribute("searchLastName", owner.getLastName());

		if (page.getContent().size() > 1) {
			// multiple owners found
			model.addAttribute("page", page);
			return "owners/ownersList";
		} else {
			// 1 owner found
			owner = page.getContent().get(0);
			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping(value = "/{ownerId}/edit")
	public String initUpdateOwner(@PathVariable("ownerId") int ownerId, Model model) {
		Owner owner = this.clinicService.findOwnerById(ownerId);
		model.addAttribute(owner);
		return "owners/ownerForm";
	}

	@PostMapping(value = "/{ownerId}/edit")
	public String processUpdateOwner(@Valid Owner owner, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "owners/ownerForm";
		} else {
			this.clinicService.saveOwner(owner);
			status.setComplete();
			return "redirect:/owners/{ownerId}";
		}
	}

	/**
	 * Custom handler for displaying an owner.
	 *
	 * @param ownerId
	 *            the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	/*
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(this.clinicService.findOwnerById(ownerId));
		return mav;
	}
	 */
}
