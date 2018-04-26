package com.blank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class TestRestController {

	@GetMapping()
	public Map<String, Object> test() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "customer-1");
		map.put("2", "customer-2");
		map.put("test ", "ajax test in dialog page ...");

		return map;
	}
}
