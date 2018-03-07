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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.blank.model.Comment;
import com.blank.model.Contribution;
import com.blank.repository.CommentRepository;
import com.blank.service.ContributionService;

@Controller
@SessionAttributes(types = Contribution.class)
@RequestMapping(value = "/contributions")
public class ContributionController {

	private String sectionKey = "contribution";

	@Autowired
	private ContributionService contributionService;

	@Autowired
	private CommentRepository commentRepository;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping()
	public String listEntities(Model model, Pageable pageable) {
		Page<Contribution> page = this.contributionService.findAll(pageable);
		model.addAttribute("page", page);
		return this.sectionKey + "/list";
	}

	@PostMapping()
	public String processCreationForm(Contribution entity, Model model, BindingResult result,
			@RequestParam("file") MultipartFile file, SessionStatus status) throws IOException {
		if (file == null) {
			FieldError error = new FieldError("Contribution", "field", "field should not be null");
			result.addError(error);
			return this.sectionKey + "/dialog";
		}
		if (result.hasErrors()) {
			return this.sectionKey + "/dialog";
		}
		String fileName = file.getOriginalFilename();
		String fileType = file.getContentType();
		byte[] content = file.getBytes();
		entity.setFileName(fileName);
		entity.setContent(content);
		entity.setContentType(fileType);
		Contribution en = this.contributionService.save(entity);
		status.setComplete();
		model.addAttribute(en);
		return this.sectionKey + "/detail";

	}

	@GetMapping(value = "/new")
	public String initCreationForm(Model model) throws InstantiationException, IllegalAccessException {
		model.addAttribute(new Contribution());
		return this.sectionKey + "/dialog";
	}

	@GetMapping("/{id}")
	public ModelAndView showOne(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView(this.sectionKey + "/detail");
		mav.addObject(this.contributionService.findOne(id));
		return mav;
	}

	@GetMapping(value = "/{id}/download")
	public ResponseEntity<byte[]> downloadContribution(@PathVariable("id") long id)
			throws UnsupportedEncodingException {
		Contribution contribution = this.contributionService.findOne(id).get();
		String fileName = contribution.getFileName();
		fileName = URLEncoder.encode(fileName, "UTF-8");
		String contentType = contribution.getContentType();
		String[] types = contentType.split("/");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(types[0], types[1]));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return new ResponseEntity<byte[]>(contribution.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping(value = "/{contributionId}/comments/new")
	public String initNewCommentDialog(@PathVariable("contributionId") long contributionId, Model model) {
		Contribution contribution = this.contributionService.findOne(contributionId).get();
		Comment comment = new Comment();
		contribution.addComment(comment);
		model.addAttribute("comment", comment);
		model.addAttribute("contribution", contribution);
		return "comment/dialog";
	}

	@PostMapping(value = "/{contributionId}/comments")
	public String addComment(@PathVariable("contributionId") long contributionId, Comment comment, Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			return "comment/dialog";
		}
		this.commentRepository.save(comment);
		return "redirect:/contributions/{contributionId}";
	}
}
