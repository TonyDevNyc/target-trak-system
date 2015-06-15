package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.ContactRules;
import com.target.trak.system.validations.util.ValidationsUtil;

public class ContactRulesImpl implements ContactRules {

	private Properties validationProps;

	@Override
	public TargetTrakValidationError isIdEmpty(final Long id) {
		if (id == null || id == 0L) {
			return new TargetTrakValidationError("id", validationProps.getProperty("contact.type.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isContactTypeEmpty(final String contactType) {
		if (StringUtils.isEmpty(contactType)) {
			return new TargetTrakValidationError("contactType", validationProps.getProperty("contact.type.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isContactTypeValidLength(final String contactType) {
		int maxlength = Integer.parseInt(validationProps.getProperty("contact.type.maxlength"));
		if (contactType.length() > maxlength) {
			return new TargetTrakValidationError("contactType", validationProps.getProperty("contact.type.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError contactTypeContainsAllowableChars(final String contactType) {
		String allowableChars = validationProps.getProperty("contact.type.allowable.chars");

		if (!StringUtils.isAlpha(contactType)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(contactType);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("contactType", validationProps.getProperty("contact.type.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isTitleValidLength(final String title) {
		if (StringUtils.isEmpty(title)) {
			return new TargetTrakValidationError("title", validationProps.getProperty("contact.title.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError titleContainsAllowableChars(final String title) {
		String allowableChars = validationProps.getProperty("contact.title.allowable.chars");

		if (!StringUtils.isAlpha(title)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(title);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("title", validationProps.getProperty("contact.title.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isMiddleInitialValidLength(final String middleInitial) {
		int maxlength = Integer.parseInt(validationProps.getProperty("contact.middleInitial.maxlength"));
		if (middleInitial.length() > maxlength) {
			return new TargetTrakValidationError(middleInitial, validationProps.getProperty("contact.middleInitial.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError middleInitialContainsAllowableChar(final String middleInitial) {
		if (!StringUtils.isAlpha(middleInitial)) {
			return new TargetTrakValidationError(middleInitial, validationProps.getProperty("contact.middleInitial.allowable.chars.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isSuffixValidLength(final String suffix) {
		int maxlength = Integer.parseInt(validationProps.getProperty("contact.suffix.maxlength"));
		if (suffix.length() > maxlength) {
			return new TargetTrakValidationError(suffix, validationProps.getProperty("contact.suffix.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError suffixContainsAllowableChar(final String suffix) {
		String allowableChars = validationProps.getProperty("contact.suffix.allowable.chars");

		if (!StringUtils.isAlpha(suffix)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(suffix);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("suffix", validationProps.getProperty("contact.suffix.allowable.chars.error"));
			}
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isCompanyEmpty(final Long company) {
		if (company == null || company == 0L) {
			return new TargetTrakValidationError("company", validationProps.getProperty("contact.company.empty.error"));
		}
		return null;
	}

	public void setValidationProps(Properties validationProps) {
		this.validationProps = validationProps;
	}
}