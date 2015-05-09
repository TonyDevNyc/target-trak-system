package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.target.trak.system.dao.ReferenceDataDao;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.AddressRules;
import com.target.trak.system.validations.util.ValidationsUtil;

public class AddressRulesImpl implements AddressRules {

	private Properties validationProps;

	private ReferenceDataDao referenceDataDao;

	@Override
	public TargetTrakValidationError isAddressLine1Empty(final String addressLine1) {
		if (StringUtils.isEmpty(addressLine1)) {
			return new TargetTrakValidationError("addressLine1", validationProps.getProperty("addressLine1.empty.error"));
		}

		if (StringUtils.isNoneEmpty(addressLine1) && StringUtils.isEmpty(addressLine1.trim())) {
			return new TargetTrakValidationError("addressLine1", validationProps.getProperty("addressLine1.empty.error"));
		}

		return null;
	}

	@Override
	public TargetTrakValidationError isAddressLine1ValidLength(final String addressLine1) {
		int maxLength = Integer.parseInt(validationProps.getProperty("addressLine1.maxlength"));
		if (addressLine1.length() > maxLength) {
			return new TargetTrakValidationError("addressLine1", validationProps.getProperty("addressLine1.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError addressLine1ContainsValidChars(final String addressLine1) {
		String allowableChars = validationProps.getProperty("addressLine1.allowable.chars");

		if (!StringUtils.isAlpha(addressLine1)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(addressLine1);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("addressLine1", validationProps.getProperty("addressLine1.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isAddressLine2ValidLength(String addressLine2) {
		if (StringUtils.isNotBlank(addressLine2)) {
			int maxLength = Integer.parseInt(validationProps.getProperty("addressLine2.maxlength"));
			if (addressLine2.length() > maxLength) {
				return new TargetTrakValidationError("addressLine2", validationProps.getProperty("addressLine2.maxlength.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError addressLine2ContainsValidChars(final String addressLine2) {
		if (StringUtils.isNotBlank(addressLine2)) {
			String allowableChars = validationProps.getProperty("addressLine2.allowable.chars");

			if (!StringUtils.isAlpha(addressLine2)) {
				String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(addressLine2);
				char[] allowableSpecialChars = allowableChars.toCharArray();

				if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
					return new TargetTrakValidationError("addressLine2", validationProps.getProperty("addressLine2.allowable.chars.error"));
				}
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isCityEmpty(final String city) {
		if (StringUtils.isEmpty(city)) {
			return new TargetTrakValidationError("city", validationProps.getProperty("city.empty.error"));
		}

		if (StringUtils.isNotEmpty(city) && StringUtils.isEmpty(city.trim())) {
			return new TargetTrakValidationError("city", validationProps.getProperty("city.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isCityValidLength(final String city) {
		int maxLength = Integer.parseInt(validationProps.getProperty("city.maxlength"));
		if (city.length() > maxLength) {
			return new TargetTrakValidationError("city", validationProps.getProperty("city.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError cityContainsValidChars(final String city) {
		if (!StringUtils.isAlpha(city)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(city);
			
			if (!ValidationsUtil.specialCharactersAreWhitespacesOnly(nonAlphaChars)) {
				return new TargetTrakValidationError("city", validationProps.getProperty("city.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isStateEmpty(final String state) {
		if (StringUtils.isEmpty(state)) {
			return new TargetTrakValidationError("state", validationProps.getProperty("state.empty.error"));
		}

		if (StringUtils.isNotEmpty(state) && StringUtils.isEmpty(state.trim())) {
			return new TargetTrakValidationError("state", validationProps.getProperty("state.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isStateValidLength(final String state) {
		int maxLength = Integer.parseInt(validationProps.getProperty("state.maxlength"));
		if (state.length() != maxLength) {
			return new TargetTrakValidationError("state", validationProps.getProperty("state.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError stateContainsValidChars(final String state) {
		if (!StringUtils.isAlpha(state)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(state);
			
			if (!ValidationsUtil.specialCharactersAreWhitespacesOnly(nonAlphaChars)) {
				return new TargetTrakValidationError("state", validationProps.getProperty("state.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isZipCodeEmpty(final String zipcode) {
		if (StringUtils.isEmpty(zipcode)) {
			return new TargetTrakValidationError("zipcode", validationProps.getProperty("zipcode.empty.error"));
		}

		if (StringUtils.isNotEmpty(zipcode) && StringUtils.isEmpty(zipcode.trim())) {
			return new TargetTrakValidationError("zipcode", validationProps.getProperty("zipcode.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isZipCodeValidLength(final String zipcode) {
		int maxLength = Integer.parseInt(validationProps.getProperty("zipcode.maxlength"));
		if (zipcode.length() > maxLength) {
			return new TargetTrakValidationError("zipcode", validationProps.getProperty("zipcode.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError zipCodeContainsAllowableChars(String zipcode) {
		String allowableChars = validationProps.getProperty("zipcode.allowable.chars");

		if (!StringUtils.isNumeric(zipcode)) {
			String nonAlphaChars = ValidationsUtil.getNonNumericCharacters(zipcode);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("zipcode", validationProps.getProperty("zipcode.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isCountryEmpty(final String country) {
		if (StringUtils.isBlank(country)) {
			return new TargetTrakValidationError("country", validationProps.getProperty("country.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isCountryValidLength(final String country) {
		int maxLength = Integer.parseInt(validationProps.getProperty("country.maxlength"));
		if (country.length() != maxLength) {
			return new TargetTrakValidationError("country", validationProps.getProperty("country.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError countryContainsValidChars(final String country) {
		if (!StringUtils.isAlpha(country)) {
			return new TargetTrakValidationError("country", validationProps.getProperty("country.allowable.chars.error"));
		}
		return null;
	}

	public void setValidationProps(Properties validationProps) {
		this.validationProps = validationProps;
	}

	public void setReferenceDataDao(ReferenceDataDao referenceDataDao) {
		this.referenceDataDao = referenceDataDao;
	}
}