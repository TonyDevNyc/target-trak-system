package com.target.trak.system.security.validations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.security.dto.registration.RegistrationApiRequest;
import com.target.trak.system.security.validations.rules.EmailRules;
import com.target.trak.system.security.validations.rules.FirstNameRules;
import com.target.trak.system.security.validations.rules.LastNameRules;
import com.target.trak.system.security.validations.rules.PasswordRules;
import com.target.trak.system.security.validations.rules.PhoneNumberRules;
import com.target.trak.system.security.validations.rules.UsernameRules;

@Component("registrationValidator")
public class UserRegistrationValidator implements TargetTrakValidator<RegistrationApiRequest> {

	@Autowired
	private UsernameRules usernameRules;

	@Autowired
	private PasswordRules passwordRules;

	@Autowired
	private FirstNameRules firstNameRules;

	@Autowired
	private LastNameRules lastNameRules;

	@Autowired
	private PhoneNumberRules phoneNumberRules;

	@Autowired
	private EmailRules emailRules;

	@Override
	public List<SecurityValidationError> validate(final RegistrationApiRequest request) throws IllegalArgumentException {
		List<SecurityValidationError> errors = new ArrayList<SecurityValidationError>();

		if (request == null) {
			throw new IllegalArgumentException("API request is null");
		}

		final UserDto userDto = request.getUserRegistration();
		if (userDto == null) {
			throw new IllegalArgumentException("User DTO is null");
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

	private List<SecurityValidationError> validateMobileNumber(final String mobileNumber) {
		List<SecurityValidationError> errors = new ArrayList<SecurityValidationError>();

		SecurityValidationError error = phoneNumberRules.isPhoneNumberEmpty(mobileNumber);
		if (error == null) {
			errors.add(phoneNumberRules.isPhoneNumberValidLength(mobileNumber));
			errors.add(phoneNumberRules.phoneContainsDigitsAndDashOnly(mobileNumber));
		} else {
			errors.add(error);
		}
		return errors;
	}
	
	private List<SecurityValidationError> validateEmail(final String email) {
		List<SecurityValidationError> errors = new ArrayList<SecurityValidationError>();

		SecurityValidationError error = emailRules.isEmailEmpty(email);
		if (error == null) {
			errors.add(emailRules.isEmailValidLength(email));
			errors.add(emailRules.isEmailValid(email));
		} else {
			errors.add(error);
		}
		return errors;
	}

	private List<SecurityValidationError> validateLastName(final String lastName) {
		List<SecurityValidationError> errors = new ArrayList<SecurityValidationError>();

		SecurityValidationError error = lastNameRules.isLastNameEmpty(lastName);
		if (error == null) {
			errors.add(lastNameRules.isLastNameValidLength(lastName));
			errors.add(lastNameRules.lastNameIsAlphabeticOnly(lastName));
		} else {
			errors.add(error);
		}

		return errors;
	}

	private List<SecurityValidationError> validateFirstName(final String firstName) {
		List<SecurityValidationError> errors = new ArrayList<SecurityValidationError>();

		SecurityValidationError error = firstNameRules.isFirstNameEmpty(firstName);
		if (error == null) {
			errors.add(firstNameRules.isFirstNameValidLength(firstName));
			errors.add(firstNameRules.firstNameIsAlphabeticOnly(firstName));
		} else {
			errors.add(error);
		}

		return errors;
	}

	private List<SecurityValidationError> validateUsername(final String username) {
		List<SecurityValidationError> errors = new ArrayList<SecurityValidationError>();

		SecurityValidationError error = usernameRules.isUsernameEmpty(username);
		if (error == null) {
			errors.add(usernameRules.isUsernameValidLength(username));
			errors.add(usernameRules.usernameAlreadyExists(username));
			errors.add(usernameRules.usernameIsAlphabeticOnly(username));
		} else {
			errors.add(error);
		}

		return errors;
	}

	private List<SecurityValidationError> validatePassword(final String password, final String repeatedPassword) {
		List<SecurityValidationError> errors = new ArrayList<SecurityValidationError>();

		SecurityValidationError pwError = passwordRules.isPasswordEmpty(password);
		SecurityValidationError repeatPwError = passwordRules.repeatedPasswordEmpty(repeatedPassword);

		if (pwError == null && repeatPwError == null) {
			errors.add(passwordRules.isPasswordValidLength(password));
			errors.add(passwordRules.passwordEqualsRepeatedPassword(password, repeatedPassword));
		} else {
			errors.add(pwError);
			errors.add(repeatPwError);
		}

		return errors;
	}

}
