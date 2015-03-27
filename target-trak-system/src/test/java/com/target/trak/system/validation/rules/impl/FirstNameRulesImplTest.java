package com.target.trak.system.validation.rules.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.FirstNameRules;
import com.target.trak.system.validations.rules.impl.FirstNameRulesImpl;

public class FirstNameRulesImplTest {

	private FirstNameRules firstNameRules;

	@Before
	public void setup() {
		firstNameRules = new FirstNameRulesImpl();
	}

	@Test
	public void testFirstNameForNullValue() throws Exception {
		TargetTrakValidationError result = firstNameRules.isFirstNameEmpty(null);
		assertNotNull("Validation Error should not be null", result);
		assertEquals("Registration 007 should be error", result.getErrorMessage(), "REGISTRATION_007");
	}
	
	@Test
	public void testFirstNameForEmptyValue() throws Exception {
		TargetTrakValidationError result = firstNameRules.isFirstNameEmpty("");
		assertNotNull("Validation Error should not be null", result);
		assertEquals("Registration 007 should be error", result.getErrorMessage(), "REGISTRATION_007");
	}
	
	@Test
	public void testFirstNameForWhitespaceValue() throws Exception {
		TargetTrakValidationError result = firstNameRules.isFirstNameEmpty(" ");
		assertNotNull("Validation Error should not be null", result);
		assertEquals("Registration 007 should be error", result.getErrorMessage(), "REGISTRATION_007");
	}
	
	@Test
	public void testFirstNameForActualValue() throws Exception {
		TargetTrakValidationError result = firstNameRules.isFirstNameEmpty("Christina");
		assertNull("Validation Error should be null", result);
	}
	
}
