package com.target.trak.system.validations.impl;

import java.util.List;

import com.target.trak.system.service.dto.contact.ContactApiRequest;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;
import com.target.trak.system.validations.rules.EmailRules;
import com.target.trak.system.validations.rules.FirstNameRules;
import com.target.trak.system.validations.rules.LastNameRules;
import com.target.trak.system.validations.rules.PhoneNumberRules;

public class ContactValidatorImpl implements TargetTrakValidator<ContactApiRequest> {

	private FirstNameRules firstNameRules;
	
	private LastNameRules lastNameRules;
	
	private EmailRules emailRules;
	
	private PhoneNumberRules telephoneNumberRules;
	
	@Override
	public List<TargetTrakValidationError> validate(final ContactApiRequest object) throws TargetTrakValidationException {
		
		return null;
	}
	
	private List<TargetTrakValidationError> validateId(final Long id) {
		return null;
	}
	
	private List<TargetTrakValidationError> validateFirstName(final String firstName) {
		
		return null;
	}
	
	private List<TargetTrakValidationError> validateLastName(final String lastName) {
		
		return null;
	}
	
	

	public void setFirstNameRules(FirstNameRules firstNameRules) {
		this.firstNameRules = firstNameRules;
	}

	public void setLastNameRules(LastNameRules lastNameRules) {
		this.lastNameRules = lastNameRules;
	}

	public void setEmailRules(EmailRules emailRules) {
		this.emailRules = emailRules;
	}

	public void setTelephoneNumberRules(PhoneNumberRules telephoneNumberRules) {
		this.telephoneNumberRules = telephoneNumberRules;
	}
}