package com.target.trak.system.validations.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;
import com.target.trak.system.validations.rules.AddressRules;
import com.target.trak.system.validations.rules.CompanyRules;

public class CompanyValidatorImpl implements TargetTrakValidator<CompanyApiRequest> {

	private CompanyRules companyRules;

	private AddressRules addressRules;

	@Override
	public List<TargetTrakValidationError> validate(final CompanyApiRequest request) throws TargetTrakValidationException {
		List<TargetTrakValidationError> validationErrors = new ArrayList<TargetTrakValidationError>();

		if (request == null) {
			throw new TargetTrakValidationException("API request is null");
		}

		final CompanyDto companyDto = request.getCompany();

		switch (request.getRequestType()) {
			case CREATE:
				validationErrors.addAll(validateCreate(companyDto));
				break;
			case UPDATE:
				validationErrors.addAll(validateUpdate(companyDto));
			default:
				System.out.println("No implementation available");
				break;
			}
		validationErrors.removeAll(Collections.singleton(null));
		return validationErrors;
	}

	private List<TargetTrakValidationError> validateCreate(final CompanyDto companyDto) {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();
		validateCompanyName(errors, companyDto.getName());
		validateAddressLine1(errors, companyDto.getAddressLine1());
		validateAddressLine2(errors, companyDto.getAddressLine2());
		validateCity(errors, companyDto.getCity());
		validateState(errors, companyDto.getState());
		validateZipcode(errors, companyDto.getZipcode());
		validateCountry(errors, companyDto.getCountry());
		return errors;
	}
	
	private List<TargetTrakValidationError> validateUpdate(final CompanyDto companyDto) {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();
		validateCompanyId(errors, companyDto.getId());
		validateCompanyName(errors, companyDto.getName());
		validateAddressLine1(errors, companyDto.getAddressLine1());
		validateAddressLine2(errors, companyDto.getAddressLine2());
		validateCity(errors, companyDto.getCity());
		validateState(errors, companyDto.getState());
		validateZipcode(errors, companyDto.getZipcode());
		validateCountry(errors, companyDto.getCountry());
		return errors;
	}

	private List<TargetTrakValidationError> validateCompanyId(final List<TargetTrakValidationError> errors, final Long companyId) {
		errors.add(companyRules.isCompanyIdEmpty(companyId));
		return errors;
	}
	
	private List<TargetTrakValidationError> validateCompanyName(final List<TargetTrakValidationError> errors, final String companyName) {
		TargetTrakValidationError error = companyRules.isCompanyNameEmpty(companyName);
		if (error == null) {
			errors.add(companyRules.isCompanyNameValidLength(companyName));
			errors.add(companyRules.companyNameContainsValidChars(companyName));
		} else {
			errors.add(error);
		}
		return errors;
	}

	private List<TargetTrakValidationError> validateAddressLine1(final List<TargetTrakValidationError> errors, final String addressLine1) {
		TargetTrakValidationError error = addressRules.isAddressLine1Empty(addressLine1);
		if (error == null) {
			errors.add(addressRules.isAddressLine1ValidLength(addressLine1));
			errors.add(addressRules.addressLine1ContainsValidChars(addressLine1));
		} else {
			errors.add(error);
		}
		return errors;
	}

	private List<TargetTrakValidationError> validateAddressLine2(final List<TargetTrakValidationError> errors, final String addressLine2) {
		errors.add(addressRules.isAddressLine2ValidLength(addressLine2));
		errors.add(addressRules.addressLine2ContainsValidChars(addressLine2));
		return errors;
	}

	private List<TargetTrakValidationError> validateCity(final List<TargetTrakValidationError> errors, final String city) {
		TargetTrakValidationError error = addressRules.isCityEmpty(city);
		if (error == null) {
			errors.add(addressRules.isCityValidLength(city));
			errors.add(addressRules.cityContainsValidChars(city));
		} else {
			errors.add(error);
		}
		return errors;
	}

	private List<TargetTrakValidationError> validateState(final List<TargetTrakValidationError> errors, final String state) {
		TargetTrakValidationError error = addressRules.isStateEmpty(state);
		if (error == null) {
			errors.add(addressRules.isStateValidLength(state));
			errors.add(addressRules.stateContainsValidChars(state));
		} else {
			errors.add(error);
		}
		return errors;
	}

	private List<TargetTrakValidationError> validateZipcode(final List<TargetTrakValidationError> errors, final String zipcode) {
		TargetTrakValidationError error = addressRules.isZipCodeEmpty(zipcode);
		if (error == null) {
			errors.add(addressRules.isZipCodeValidLength(zipcode));
			errors.add(addressRules.zipCodeContainsAllowableChars(zipcode));
		} else {
			errors.add(error);
		}
		return errors;
	}

	private List<TargetTrakValidationError> validateCountry(final List<TargetTrakValidationError> errors, final String country) {
		TargetTrakValidationError error = addressRules.isCountryEmpty(country);
		if (error == null) {
			errors.add(addressRules.isCountryValidLength(country));
			errors.add(addressRules.countryContainsValidChars(country));
		} else {
			errors.add(error);
		}
		return errors;
	}

	public void setCompanyRules(CompanyRules companyRules) {
		this.companyRules = companyRules;
	}

	public void setAddressRules(AddressRules addressRules) {
		this.addressRules = addressRules;
	}
}