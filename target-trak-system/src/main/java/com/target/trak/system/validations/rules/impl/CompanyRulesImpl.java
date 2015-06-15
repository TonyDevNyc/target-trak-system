package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.CompanyRules;
import com.target.trak.system.validations.util.ValidationsUtil;

public class CompanyRulesImpl implements CompanyRules {

	private Properties validationProps;

	@Override
	public TargetTrakValidationError isCompanyIdEmpty(final Long companyId) {
		if (companyId == null || companyId == 0L) {
			return new TargetTrakValidationError("id", validationProps.getProperty("companyId.empty.error"));
		}
		return null;
	}
	
	@Override
	public TargetTrakValidationError isCompanyNameEmpty(final String companyName) {
		if (StringUtils.isEmpty(companyName)) {
			return new TargetTrakValidationError("name", validationProps.getProperty("companyName.empty.error"));
		}

		if (StringUtils.isNotEmpty(companyName) && StringUtils.isEmpty(companyName.trim())) {
			return new TargetTrakValidationError("name", validationProps.getProperty("companyName.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isCompanyNameValidLength(final String companyName) {
		int maxlength = Integer.parseInt(validationProps.getProperty("companyName.maxlength"));
		if (companyName.length() > maxlength) {
			return new TargetTrakValidationError("name", validationProps.getProperty("companyName.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError companyNameContainsValidChars(final String companyName) {
		String allowableChars = validationProps.getProperty("companyName.allowable.chars");

		if (!StringUtils.isAlpha(companyName)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(companyName);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("name", validationProps.getProperty("companyName.allowable.chars.error"));
			}
		}
		return null;
	}

	public void setValidationProps(Properties validationProps) {
		this.validationProps = validationProps;
	}
}