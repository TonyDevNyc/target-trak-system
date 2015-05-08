package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface AddressRules {

	public TargetTrakValidationError isAddressLine1Empty(final String addressLine1);

	public TargetTrakValidationError isAddressLine1ValidLength(final String addressLine1);

	public TargetTrakValidationError addressLine1ContainsValidChars(final String addressLine1);

	public TargetTrakValidationError isAddressLine2ValidLength(final String addressLine2);

	public TargetTrakValidationError addressLine2ContainsValidChars(final String addressLine1);

	public TargetTrakValidationError isCityEmpty(final String city);

	public TargetTrakValidationError isCityValidLength(final String city);

	public TargetTrakValidationError cityContainsValidChars(final String city);

	public TargetTrakValidationError isStateEmpty(final String state);

	public TargetTrakValidationError isStateValidLength(final String state);

	public TargetTrakValidationError stateContainsValidChars(final String state);

	public TargetTrakValidationError isZipCodeEmpty(final String zipcode);

	public TargetTrakValidationError isZipCodeValidLength(final String zipcode);

	public TargetTrakValidationError zipCodeContainsAllowableChars(final String zipcode);

	public TargetTrakValidationError isCountryEmpty(final String country);

	public TargetTrakValidationError isCountryValidLength(final String country);

	public TargetTrakValidationError countryContainsValidChars(final String country);
}
