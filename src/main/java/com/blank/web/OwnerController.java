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
package com.blank.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.blank.model.Owner;
import com.blank.service.OwnerService;

@Controller
@SessionAttributes(types = Owner.class)
@RequestMapping(value = "/owners")
public class OwnerController extends AbstractController<Owner, Long> {

	@Autowired
	private OwnerService ownerService;

	public OwnerController(OwnerService service) {

		super(service, Owner.class);
		this.ownerService = service;
	}

	@GetMapping(value = "/search")
	public String processFindForm(Owner owner, BindingResult result, Model model, HttpSession session,
			Pageable pageable) {
		Page<Owner> page = null;
		if (StringUtils.isEmpty(owner.getLastName())) {
			page = this.ownerService.findAll(pageable);
		} else {
			page = this.ownerService.findAllByLastNameIgnoringCase(owner.getLastName(), pageable);
		}
		model.addAttribute("page", page);
		session.setAttribute("searchLastName", owner.getLastName());
		if (CollectionUtils.isEmpty(page.getContent())) {
			result.rejectValue("lastName", "notFound", new Object[] { owner.getLastName() }, "not found");
		}
		return "owner/list";
	}

}
