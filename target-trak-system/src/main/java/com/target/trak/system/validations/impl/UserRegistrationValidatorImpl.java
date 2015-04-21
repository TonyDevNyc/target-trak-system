package com.target.trak.system.validations.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.security.dto.registration.RegistrationApiRequest;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;
import com.target.trak.system.validations.rules.EmailRules;
import com.target.trak.system.validations.rules.FirstNameRules;
import com.target.trak.system.validations.rules.LastNameRules;
import com.target.trak.system.validations.rules.PasswordRules;
import com.target.trak.system.validations.rules.PhoneNumberRules;
import com.target.trak.system.validations.rules.UsernameRules;

public class UserRegistrationValidatorImpl implements TargetTrakValidator<RegistrationApiRequest> {

	private UsernameRules usernameRules;

	private PasswordRules passwordRules;

	private FirstNameRules firstNameRules;

	private LastNameRules lastNameRules;

	private PhoneNumberRules phoneNumberRules;

	private EmailRules emailRules;

	@Override
	public List<TargetTrakValidationError> validate(final RegistrationApiRequest request) throws TargetTrakValidationException {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();

		if (request == null) {
			throw new TargetTrakValidationException("API request is null");
		}

		final UserDto userDto = request.getUserRegistration();
		if (userDto == null) {
			throw new TargetTrakValidationException("User DTO is null");
		}

		errors.addAll(validateUsername(userDto.getUsername()));
		errors.addAll(validatePassword(userDto.getPassword(), userDto.getRepeatedPassword()));
		errors.addAll(validateFirstName(userDto.getFirstName()));
		errors.addAll(validateLastName(userDto.getLastName()));
		errors.addAll(validateEmail(userDto.getEmail()));
		errors.addAll(validateMobileNumber(userDto.getMobileNumber()));

		errors.removeAll(Collections.singleton(null));
		return errors;
	}

	private List<TargetTrakValidationError> validateMobileNumber(final String mobileNumber) {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();

		TargetTrakValidationError error = phoneNumberRules.isPhoneNumberEmpty(mobileNumber);
		if (error == null) {
			errors.add(phoneNumberRules.isPhoneNumberValidLength(mobileNumber));
			errors.add(phoneNumberRules.phoneContainsDigitsAndDashOnly(mobileNumber));
		} else {
			errors.add(error);
		}
		return errors;
	}

	private List<TargetTrakValidationError> validateEmail(final String email) {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();

		TargetTrakValidationError error = emailRules.isEmailEmpty(email);
		if (error == null) {
			errors.add(emailRules.isEmailValidLength(email));
			errors.add(emailRules.isEmailValid(email));
		} else {
			errors.add(error);
		}
		return errors;
	}

	private List<TargetTrakValidationError> validateLastName(final String lastName) {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();

		TargetTrakValidationError error = lastNameRules.isLastNameEmpty(lastName);
		if (error == null) {
			errors.add(lastNameRules.isLastNameValidLength(lastName));
			errors.add(lastNameRules.lastNameIsAlphabeticOnly(lastName));
		} else {
			errors.add(error);
		}

		return errors;
	}

	private List<TargetTrakValidationError> validateFirstName(final String firstName) {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();

		TargetTrakValidationError error = firstNameRules.isFirstNameEmpty(firstName);
		if (error == null) {
			errors.add(firstNameRules.isFirstNameValidLength(firstName));
			errors.add(firstNameRules.firstNameIsAlphabeticOnly(firstName));
		} else {
			errors.add(error);
		}

		return errors;
	}

	private List<TargetTrakValidationError> validateUsername(final String username) {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();

		TargetTrakValidationError error = usernameRules.isUsernameEmpty(username);
		if (error == null) {
			errors.add(usernameRules.isUsernameValidLength(username));
			errors.add(usernameRules.usernameAlreadyExists(username));
			errors.add(usernameRules.usernameIsAlphabeticOnly(username));
		} else {
			errors.add(error);
		}

		return errors;
	}

	private List<TargetTrakValidationError> validatePassword(final String password, final String repeatedPassword) {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();

		TargetTrakValidationError pwError = passwordRules.isPasswordEmpty(password);
		TargetTrakValidationError repeatPwError = passwordRules.repeatedPasswordEmpty(repeatedPassword);

		if (pwError == null && repeatPwError == null) {
			errors.add(passwordRules.isPasswordValidLength(password));
			errors.add(passwordRules.passwordEqualsRepeatedPassword(password, repeatedPassword));
		} else {
			errors.add(pwError);
			errors.add(repeatPwError);
		}

		return errors;
	}

	public void setUsernameRules(UsernameRules usernameRules) {
		this.usernameRules = usernameRules;
	}

	public void setPasswordRules(PasswordRules passwordRules) {
		this.passwordRules = passwordRules;
	}

	public void setFirstNameRules(FirstNameRules firstNameRules) {
		this.firstNameRules = firstNameRules;
	}

	public void setLastNameRules(LastNameRules lastNameRules) {
		this.lastNameRules = lastNameRules;
	}

	public void setPhoneNumberRules(PhoneNumberRules phoneNumberRules) {
		this.phoneNumberRules = phoneNumberRules;
	}

	public void setEmailRules(EmailRules emailRules) {
		this.emailRules = emailRules;
	}
}