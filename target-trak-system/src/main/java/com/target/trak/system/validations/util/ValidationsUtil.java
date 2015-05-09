package com.target.trak.system.validations.util;

import org.apache.commons.lang3.StringUtils;

public final class ValidationsUtil {

	private ValidationsUtil() {
	}

	public static boolean containsAllowableSpecialChars(final String value, final char[] allowableSpecialChars) {
		int length = value.length();
		for (int i = 0; i < length; i++) {
			char c = value.charAt(i);
			if (!containsAllowableCharacter(c, allowableSpecialChars)) {
				return false;
			}
		}
		return true;
	}

	private static boolean containsAllowableCharacter(char c, final char[] allowableSpecialChars) {
		for (int i = 0; i < allowableSpecialChars.length; i++) {
			if (c == allowableSpecialChars[i]) {
				return true;
			}
		}
		return false;
	}

	public static String getNonAlphaCharacters(final String value) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char character = value.charAt(i);
			if (!Character.isLetter(character)) {
				builder.append(character);
			}
		}
		return builder.toString();
	}
	
	public static String getNonNumericCharacters(final String value) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char character = value.charAt(i);
			if (!Character.isDigit(character)) {
				builder.append(character);
			}
		}
		return builder.toString();
	}
	
	public static boolean specialCharactersAreWhitespacesOnly(final String value) {
		if (StringUtils.isEmpty(value)) {
			return true;
		}
		
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (!Character.isWhitespace(c)) {
				return false;
			}
		}
		return true;
	}
}