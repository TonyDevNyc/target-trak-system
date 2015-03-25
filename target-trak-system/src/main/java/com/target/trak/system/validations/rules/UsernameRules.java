package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface UsernameRules {

	public TargetTrakValidationError isUsernameEmpty(final String username);

	public TargetTrakValidationError isUsernameValidLength(final String username);

	public TargetTrakValidationError usernameIsAlphabeticOnly(final String username);

	public TargetTrakValidationError usernameAlreadyExists(final String username);
}
