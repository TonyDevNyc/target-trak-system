package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface ContactRules {

	public TargetTrakValidationError isIdEmpty(final Long id);
	
	public TargetTrakValidationError isContactTypeEmpty(final String contactType);
	
	public TargetTrakValidationError isContactTypeValidLength(final String contactType);
	
	public TargetTrakValidationError contactTypeContainsAllowableChars(final String contactType);
	
	public TargetTrakValidationError isTitleValidLength(final String title);
	
	public TargetTrakValidationError titleContainsAllowableChars(final String title);
	
	public TargetTrakValidationError isFirstNameEmpty(final String firstName);
	
	public TargetTrakValidationError isFirstNameValidLength(final String firstName);
	
	public TargetTrakValidationError firstNameContainsAllowableChars(final String firstName);
	
	public TargetTrakValidationError isLastNameEmpty(final String lastName);
	
	public TargetTrakValidationError isLastNameValidLength(final String lastName);
	
	public TargetTrakValidationError lastNameContainsAllowableChars(final String lastName);
	
	public TargetTrakValidationError isMiddleInitialValidLength(final String middleInitial);
	
	public TargetTrakValidationError middleInitialContainsAllowableChar(final String middleInitial);
	
	public TargetTrakValidationError isSuffixValidLength(final String suffix);
	
	public TargetTrakValidationError suffixContainsAllowableChar(final String suffix);
	
	public TargetTrakValidationError isTelephoneEmpty(final String telephoneNumber);
	
	public TargetTrakValidationError isTelephoneValidLength(final String telephoneNumber);
	
	public TargetTrakValidationError telephoneContainsAllowableChars(final String telephoneNumber);
	
	public TargetTrakValidationError isEmailEmpty(final String email);
	
	public TargetTrakValidationError isEmailValidLength(final String email);
	
	public TargetTrakValidationError emailContainsAllowableChars(final String email);
	
	public TargetTrakValidationError isCompanyEmpty(final Long companyId);
	
	public TargetTrakValidationError isActiveAtCompanyEmpty(final boolean activeAtCompany);
}