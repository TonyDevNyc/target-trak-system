package com.target.trak.system.web.controllers.security;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.target.trak.system.security.service.dto.UserDto;
import com.target.trak.system.security.service.dto.registration.RegistrationApiRequest;
import com.target.trak.system.security.service.dto.registration.RegistrationApiResponse;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.web.forms.RegistrationForm;

@Controller
public class RegisterController {

	private final static String REGISTER_PAGE = "registration";

	private final static String REGISTRATION_COMPLETE_PAGE = "registrationComplete";

	private Logger logger = Logger.getLogger(getClass());

	private TargetTrakService<RegistrationApiRequest, RegistrationApiResponse> registrationService;

	@RequestMapping(value = "/register.htm", method = RequestMethod.GET)
	public ModelAndView showRegisterScreen() {
		return new ModelAndView(REGISTER_PAGE, "registrationForm", new RegistrationForm());
	}

	@RequestMapping(value = "/registerNewUser.htm", method = RequestMethod.POST)
	public String registerNewUser(@ModelAttribute("registrationForm") RegistrationForm registrationForm, BindingResult result, ModelMap model, RedirectAttributes attributes) {
		RegistrationApiRequest request = new RegistrationApiRequest();
		UserDto user = buildUserDto(registrationForm);
		request.setUserRegistration(user);
		RegistrationApiResponse response = null;
		String returnPage = null;

		response = registrationService.processRequest(request);
		if (response.isSuccess()) {
			returnPage = REGISTRATION_COMPLETE_PAGE;
		} else {
			logger.info("Error while processing registration of new user");
			bindValidationErrors(response.getErrors(), result);
			returnPage = REGISTER_PAGE;
		}
		return returnPage;
	}

	private void bindValidationErrors(List<TargetTrakValidationError> validationErrors, final BindingResult result) {
		for (TargetTrakValidationError validationError : validationErrors) {
			result.rejectValue(validationError.getFieldName(), validationError.getErrorMessage());
		}
	}

	private UserDto buildUserDto(final RegistrationForm registrationForm) {
		UserDto user = new UserDto();
		user.setEmail(registrationForm.getEmail());
		user.setEnabled(true);
		user.setFirstName(registrationForm.getFirstName());
		user.setLastName(registrationForm.getLastName());
		user.setTelephoneNumber(registrationForm.getTelephoneNumber());
		user.setUsername(registrationForm.getUsername());
		user.setPassword(registrationForm.getPassword());
		user.setRepeatedPassword(registrationForm.getRepeatedPassword());
		user.setRegistrationDate(Calendar.getInstance());
		return user;
	}

	public void setRegistrationService(TargetTrakService<RegistrationApiRequest, RegistrationApiResponse> registrationService) {
		this.registrationService = registrationService;
	}
}