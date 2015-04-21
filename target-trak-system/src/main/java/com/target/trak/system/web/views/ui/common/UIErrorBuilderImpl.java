package com.target.trak.system.web.views.ui.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.target.trak.system.validations.TargetTrakValidationError;

public class UIErrorBuilderImpl implements UIErrorBuilder {

	private MessageSource messageSource;

	@Override
	public List<UIValidationError> buildUiValidationErrors(List<TargetTrakValidationError> apiErrors) {
		List<UIValidationError> list = new ArrayList<UIValidationError>();
		if (apiErrors == null || apiErrors.isEmpty()) {
			return list;
		}

		String msg = null;
		for (TargetTrakValidationError apiError : apiErrors) {
			msg = messageSource.getMessage(apiError.getErrorMessage(), new Object[] {}, Locale.US);
			list.add(new UIValidationError(apiError.getFieldName(), msg));
		}
		return list;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}