package com.target.trak.system.web.controllers.security;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(getClass());
	
	private static final String LOGIN_PAGE = "login";
	
	@RequestMapping(value="/login.htm", method=RequestMethod.GET)
	public String showLoginScreen() {
		logger.info("Forwarding guest to login screen");
		return LOGIN_PAGE;
	}
}
