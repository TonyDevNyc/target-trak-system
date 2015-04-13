package com.target.trak.system.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.web.session.InvalidSessionStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxRedirectInvalidSessionStrategy implements InvalidSessionStrategy {

	private Logger logger = Logger.getLogger(getClass());

	private static final String AJAX_HEADER_NAME = "x-requested-with";

	private static final String AJAX_REQUEST = "XMLHttpRequest";

	private static final String REDIRECT_URL = "/target-trak-system/sys/login.htm?time=inactivity";

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean ajaxRedirect = AJAX_REQUEST.equals(request.getHeader(AJAX_HEADER_NAME));

		if (ajaxRedirect) {
			Map<String, Object> map = new HashMap<>();
			ObjectMapper mapper = new ObjectMapper();
			map.put("success", Boolean.FALSE);
			map.put("errorType", "SESSION_TIMEOUT");
			map.put("redirectUrl", REDIRECT_URL);

			response.setContentType("application/json");
			response.getWriter().write(mapper.writeValueAsString(map));
		} else {
			logger.info("Session expired due to non-ajax request, starting a new session and redirect to requested url [" + REDIRECT_URL + "]");
			request.getSession(true);
			response.sendRedirect(REDIRECT_URL);
		}
	}
}