package com.target.trak.system.web.controllers.security;

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

import com.target.trak.system.security.dto.credentials.ForgotPasswordApiRequest;
import com.target.trak.system.security.dto.credentials.ForgotPasswordApiResponse;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.ForgotPasswordService;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.web.forms.ForgotPasswordForm;

@Controller
public class ForgotPasswordController {

	private static final String FORGOT_PASSWORD_PAGE = "forgotPassword";

	private static final String FORGOT_PASSWORD_COMPLETE = "forgotPasswordComplete";

	private Logger logger = Logger.getLogger(getClass());

	private ForgotPasswordService forgotPasswordService;

	@RequestMapping(value = "/forgotPassword.htm", method = RequestMethod.GET)
	public ModelAndView showForgotPasswordScreen() {
		return new ModelAndView(FORGOT_PASSWORD_PAGE, "forgotPasswordForm", new ForgotPasswordForm());
	}

	@RequestMapping(value = "/handleForgotPasswordEmail.htm", method = RequestMethod.POST)
	public String handleForgottenPasswordEmail(@ModelAttribute("forgotPasswordForm") ForgotPasswordForm forgotPasswordForm, BindingResult result, ModelMap model, RedirectAttributes attributes) {
		ForgotPasswordApiRequest request = new ForgotPasswordApiRequest();
		request.setEmail(forgotPasswordForm.getEmail());
		String returnPage = null;

		try {
			ForgotPasswordApiResponse response = forgotPasswordService.handleForgotPassword(request);
			if (response.isSuccess()) {
				returnPage = FORGOT_PASSWORD_COMPLETE;
			} else if (response.getErrors().size() > 0) {
				bindValidationErrors(response.getErrors(), result);
				returnPage = FORGOT_PASSWORD_PAGE;
			}
		} catch (TargetTrakSecurityException e) {
			logger.error(e.getMessage(), e);
			returnPage = FORGOT_PASSWORD_PAGE;
		}
		return returnPage;
	}

	private void bindValidationErrors(List<TargetTrakValidationError> validationErrors, final BindingResult result) {
		for (TargetTrakValidationError validationError : validationErrors) {
			result.rejectValue(validationError.getFieldName(), validationError.getErrorMessage());
		}
	}

	public void setForgotPasswordService(ForgotPasswordService forgotPasswordService) {
		this.forgotPasswordService = forgotPasswordService;
	}
}