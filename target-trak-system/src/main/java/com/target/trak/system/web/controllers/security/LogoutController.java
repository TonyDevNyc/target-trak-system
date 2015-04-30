package com.target.trak.system.web.controllers.security;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogoutController {

	private Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value="/logout.htm", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> handleLogoutRequest() {
		logger.info("Logging out of Target-Trak system");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", Boolean.TRUE);
		return map;
	}
}
