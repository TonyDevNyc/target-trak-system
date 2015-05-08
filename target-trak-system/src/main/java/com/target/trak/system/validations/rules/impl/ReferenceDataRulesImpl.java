package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.target.trak.system.dao.ReferenceDataDao;
import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.ReferenceDataRules;
import com.target.trak.system.validations.util.ValidationsUtil;

public class ReferenceDataRulesImpl implements ReferenceDataRules {

	private Properties validationProps;

	private ReferenceDataDao referenceDataDao;

	@Override
	public TargetTrakValidationError isIdEmpty(final Long id) {
		if (id == null || id == 0L) {
			return new TargetTrakValidationError("id", validationProps.getProperty("reference.data.id.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isTypeEmpty(final String type) {
		if (StringUtils.isBlank(type)) {
			return new TargetTrakValidationError("type", validationProps.getProperty("type.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isTypeValidLength(final String type) {
		String strLength = (String) validationProps.get("type.maxlength");
		int maxLength = Integer.parseInt(strLength);
		if (type.length() > maxLength) {
			return new TargetTrakValidationError("type", validationProps.getProperty("type.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError typeContainsAllowableChars(final String type) {
		String allowableChars = validationProps.getProperty("type.allowable.chars");

		if (!StringUtils.isAlpha(type)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(type);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("type", validationProps.getProperty("type.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isLabelEmpty(final String label) {
		if (StringUtils.isBlank(label)) {
			return new TargetTrakValidationError("label", validationProps.getProperty("label.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isLabelValidLength(final String label) {
		int maxLength = Integer.parseInt((String) validationProps.get("label.maxlength"));
		if (label.length() > maxLength) {
			return new TargetTrakValidationError("label", validationProps.getProperty("label.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError labelContainsAllowableChars(final String label) {
		String allowableChars = validationProps.getProperty("label.allowable.chars");

		if (!StringUtils.isAlpha(label)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(label);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("label", validationProps.getProperty("label.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isValueEmpty(final String value) {
		if (StringUtils.isBlank(value)) {
			return new TargetTrakValidationError("value", validationProps.getProperty("value.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isValueValidLength(final String value) {
		int maxLength = Integer.parseInt((String) validationProps.get("value.maxlength"));
		if (value.length() > maxLength) {
			return new TargetTrakValidationError("value", validationProps.getProperty("value.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError valueContainsAllowableChars(final String value) {
		String allowableChars = validationProps.getProperty("value.allowable.chars");

		if (!StringUtils.isAlpha(value)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(value);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("value", validationProps.getProperty("value.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError referenceDataAlreadyExists(final String type, final String label, final String value) {
		boolean exists = referenceDataDao.referenceDataAlreadyExists(type, label, value);
		if (exists) {
			return new TargetTrakValidationError("api", validationProps.getProperty("reference.data.already.exists.error"));
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
			return new TargetTrakValidationError("api", validationProps.getProperty("reference.data.already.exists.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isStatusEmpty(final String status) {
		if (StringUtils.isBlank(status)) {
			return new TargetTrakValidationError("status", validationProps.getProperty("status.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isStatusValidLength(final String status) {
		int statusLength = status.length();
		int maxLength = Integer.parseInt((String) validationProps.get("status.maxlength"));
		if (statusLength > maxLength) {
			return new TargetTrakValidationError("status", validationProps.getProperty("status.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError containsAllowableStatus(final String status) {
		if (!"In-active".equals(status) && !"Active".equals(status)) {
			return new TargetTrakValidationError("status", validationProps.getProperty("status.allowable.values.error"));
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