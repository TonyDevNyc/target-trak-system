package com.target.trak.system.web.controllers.homepage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomepageController {

private Logger logger = Logger.getLogger(getClass());
	
	private static final String HOMEPAGE = "index";
	
	@RequestMapping(value="/index.htm", method=RequestMethod.GET)
	public String showHomepage() {
		logger.info("Forwarding authenticated user to homepage screen");
		return HOMEPAGE;
	}
}
