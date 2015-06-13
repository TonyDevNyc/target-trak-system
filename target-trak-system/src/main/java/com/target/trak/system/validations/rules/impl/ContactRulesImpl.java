package com.target.trak.system.validations.rules.impl;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.ContactRules;

public class ContactRulesImpl implements ContactRules {

	@Override
	public TargetTrakValidationError isIdEmpty(Long id) {
		if (id == null || id == 0L) {
			return new TargetTrakValidationError("id", "");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isContactTypeEmpty(String contactType) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isContactTypeValidLength(String contactType) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError contactTypeContainsAllowableChars(String contactType) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isTitleValidLength(String title) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError titleContainsAllowableChars(String title) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isFirstNameEmpty(String firstName) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isFirstNameValidLength(String firstName) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError firstNameContainsAllowableChars(String firstName) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isLastNameEmpty(String lastName) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isLastNameValidLength(String lastName) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError lastNameContainsAllowableChars(String lastName) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isMiddleInitialValidLength(String middleInitial) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError middleInitialContainsAllowableChar(String middleInitial) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isSuffixValidLength(String suffix) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError suffixContainsAllowableChar(String suffix) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isTelephoneEmpty(String telephoneNumber) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isTelephoneValidLength(String telephoneNumber) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError telephoneContainsAllowableChars(String telephoneNumber) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isEmailEmpty(String email) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isEmailValidLength(String email) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError emailContainsAllowableChars(String email) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isCompanyEmpty(Long companyId) {
		
		return null;
	}

	@Override
	public TargetTrakValidationError isActiveAtCompanyEmpty(boolean activeAtCompany) {
		
		return null;
	}
}