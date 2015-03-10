package com.target.trak.system.validations.rules;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;

@Component("referenceDataRules")
public class ReferenceDataRules {

	@Autowired
	@Qualifier("dwValidationProps")
	private Properties dwValidationProps;

	/**
	 * type.empty.error=REFERENCE_DATA_001
	 * type.maxlength.error=REFERENCE_DATA_002
	 * label.empty.error=REFERENCE_DATA_003
	 * label.maxlength.error=REFERENCE_DATA_004
	 * value.empty.error=REFERENCE_DATA_005
	 * value.maxlength.error=REFERENCE_DATA_006
	 * reference.data.exists.error=REFERENCE_DATA_007
	 */

	public TargetTrakValidationError isTypeEmpty(final String type) {
		if (StringUtils.isEmpty(type)) {
			return new TargetTrakValidationError("type", "REFERENCE_DATA_001");
		}
		return null;
	}

	public TargetTrakValidationError isTypeValidLength(final String type) {
		int maxLength = Integer.parseInt((String) dwValidationProps.get("type.maxlength"));
		if (type.length() > maxLength) {
			return new TargetTrakValidationError("type", "REFERENCE_DATA_002");
		}
		return null;
	}

	public TargetTrakValidationError typeContainsAllowableChars(final String type) {
		// String pattern = "a-zA-Z0-9~@#\^\$&\*\(\)-_\+=\[\]\{\}\|\\,\.\?\s]*";
		if (type.matches("[a-z]+")) {
			return new TargetTrakValidationError("type", "REFERENCE_DATA_003");
		}
		return null;
	}

	public TargetTrakValidationError isLabelEmpty(final String label) {
		if (StringUtils.isEmpty(label)) {
			return new TargetTrakValidationError("label", "REFERENCE_DATA_003");
		}
		return null;
	}

	public TargetTrakValidationError isLabelValidLength(final String label) {
		int maxLength = Integer.parseInt((String) dwValidationProps.get("label.maxlength"));
		if (label.length() > maxLength) {
			return new TargetTrakValidationError("type", "REFERENCE_DATA_004");
		}
		return null;
	}
}
