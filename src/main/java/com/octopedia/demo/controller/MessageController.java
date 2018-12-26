package com.octopedia.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@GetMapping
	public String message(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("message") == null) {
			model.addAttribute("link", "/books");
			model.addAttribute("linkName", "Back to homepage");
			return "message";
		}
		request.getSession().removeAttribute("message");
		return "message";
	}
}
