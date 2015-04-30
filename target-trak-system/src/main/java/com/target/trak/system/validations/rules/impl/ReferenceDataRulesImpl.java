package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.springframework.util.StringUtils;

import com.target.trak.system.dao.ReferenceDataDao;
import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.ReferenceDataRules;

public class ReferenceDataRulesImpl implements ReferenceDataRules {

	private Properties validationProps;

	private ReferenceDataDao referenceDataDao;

	// TODO implement regex for special characters after bus req updatedSS

	@Override
	public TargetTrakValidationError isIdEmpty(final Long id) {
		if (id == null || id == 0L) {
			return new TargetTrakValidationError("id", "REFERENCE_DATA_011");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isTypeEmpty(final String type) {
		if (StringUtils.isEmpty(type)) {
			return new TargetTrakValidationError("type", "REFERENCE_DATA_001");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isTypeValidLength(final String type) {
		String strLength = (String) validationProps.get("type.maxlength");
		int maxLength = Integer.parseInt(strLength);
		if (type.length() > maxLength) {
			return new TargetTrakValidationError("type", "REFERENCE_DATA_002");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError typeContainsAllowableChars(final String type) {
		// String pattern = "a-zA-Z0-9~@#\^\$&\*\(\)-_\+=\[\]\{\}\|\\,\.\?\s]*";
		// [a-zA-Z0-9\\s\\,\\-\\.]+
		// TODO implementation in progress
		return null;
	}

	@Override
	public TargetTrakValidationError isLabelEmpty(final String label) {
		if (StringUtils.isEmpty(label)) {
			return new TargetTrakValidationError("label", "REFERENCE_DATA_004");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isLabelValidLength(final String label) {
		int maxLength = Integer.parseInt((String) validationProps.get("label.maxlength"));
		if (label.length() > maxLength) {
			return new TargetTrakValidationError("label", "REFERENCE_DATA_005");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError labelContainsAllowableChars(final String label) {
		// String pattern = "a-zA-Z0-9~@#\^\$&\*\(\)-_\+=\[\]\{\}\|\\,\.\?\s]*";
		// [a-zA-Z0-9\\s\\,\\-\\.]+
		// TODO implementation in progress
		return null;
	}

	@Override
	public TargetTrakValidationError isValueEmpty(final String value) {
		if (StringUtils.isEmpty(value)) {
			return new TargetTrakValidationError("value", "REFERENCE_DATA_007");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isValueValidLength(final String value) {
		int maxLength = Integer.parseInt((String) validationProps.get("value.maxlength"));
		if (value.length() > maxLength) {
			return new TargetTrakValidationError("value", "REFERENCE_DATA_008");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError valueContainsAllowableChars(final String value) {
		// String pattern = "a-zA-Z0-9~@#\^\$&\*\(\)-_\+=\[\]\{\}\|\\,\.\?\s]*";
		// String pattern = "a-zA-Z0-9~@#\^\$&\*\(\)-_\+=\[\]\{\}\|\\,\.\?\s]*";
		// [a-zA-Z0-9\\s\\,\\-\\.]+
		// TODO implementation in progress
		return null;
	}

	@Override
	public TargetTrakValidationError referenceDataAlreadyExists(final String type, final String label, final String value) {
		boolean exists = referenceDataDao.referenceDataAlreadyExists(type, label, value);
		if (exists) {
			return new TargetTrakValidationError("api", "REFERENCE_DATA_010");
		}
		return null;
	}
	
	@Override
	public TargetTrakValidationError checkReferenceDataConstraint(final Long requestId, final String type, final String label, final String value) {
		ReferenceDataDomain domain = referenceDataDao.selectReferenceDataByFields(type, label, value);
		if (domain == null) {
			return null;
		}
		
		if (domain.getId() != requestId) {
			return new TargetTrakValidationError("api", "REFERENCE_DATA_010");
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