package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface ContactRules {

	public TargetTrakValidationError isIdEmpty(final Long id);
	
	public TargetTrakValidationError isContactTypeEmpty(final String contactType);
	
	public TargetTrakValidationError isContactTypeValidLength(final String contactType);
	
	public TargetTrakValidationError contactTypeContainsAllowableChars(final String contactType);
	
	public TargetTrakValidationError isTitleValidLength(final String title);
	
	public TargetTrakValidationError titleContainsAllowableChars(final String title);
	
	public TargetTrakValidationError isMiddleInitialValidLength(final String middleInitial);
	
	public TargetTrakValidationError middleInitialContainsAllowableChar(final String middleInitial);
	
	public TargetTrakValidationError isSuffixValidLength(final String suffix);
	
	public TargetTrakValidationError suffixContainsAllowableChar(final String suffix);
	
	public TargetTrakValidationError isCompanyEmpty(final Long companyId);
}