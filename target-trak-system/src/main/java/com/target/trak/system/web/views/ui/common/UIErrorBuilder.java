package com.target.trak.system.web.views.ui.common;

import java.util.List;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface UIErrorBuilder {

	public List<UIValidationError> buildUiValidationErrors(final List<TargetTrakValidationError> apiErrors);
	
	public String buildErrorMessage(final String errorCode);
}
