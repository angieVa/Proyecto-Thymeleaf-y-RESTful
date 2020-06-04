package com.computacion.taller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

}
