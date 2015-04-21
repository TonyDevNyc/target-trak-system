package com.target.trak.system.validation.rules.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.FirstNameRules;

@SuppressWarnings("deprecation")
@RunWith(JMock.class)
public class FirstNameRulesImplTest {

	private FirstNameRules firstNameRulesMock;
	private JUnit4Mockery mockery = new JUnit4Mockery();

	@Before
	public void setup() {
		firstNameRulesMock = mockery.mock(FirstNameRules.class);
	}

	@Test
	public void testFirstNameForNullValue() throws Exception {
		final String firstName = null;
		mockery.checking(new Expectations() {
			{
				oneOf(firstNameRulesMock).isFirstNameEmpty(with(equal(firstName)));
				will(returnValue(buildError("firstName", "REGISTRATION_007")));
			}
		});

		TargetTrakValidationError result = firstNameRulesMock.isFirstNameEmpty(firstName);
		assertNotNull("Validation Error should not be null", result);
		assertEquals("Registration 007 should be error", result.getErrorMessage(), "REGISTRATION_007");
	}

	private TargetTrakValidationError buildError(final String field, final String message) {
		final TargetTrakValidationError error = new TargetTrakValidationError(field, message);
		return error;
	}

	@Test
	public void testFirstNameForEmptyValue() throws Exception {
		final String firstName = "";
		mockery.checking(new Expectations() {
			{
				oneOf(firstNameRulesMock).isFirstNameEmpty(with(equal(firstName)));
				will(returnValue(buildError("firstName", "REGISTRATION_007")));
			}
		});

		TargetTrakValidationError result = firstNameRulesMock.isFirstNameEmpty(firstName);
		assertNotNull("Validation Error should not be null", result);
		assertEquals("Registration 007 should be error", result.getErrorMessage(), "REGISTRATION_007");
	}

	@Test
	public void testFirstNameForWhitespaceValue() throws Exception {
		final String firstName = " ";
		mockery.checking(new Expectations() {
			{
				oneOf(firstNameRulesMock).isFirstNameEmpty(with(equal(firstName)));
				will(returnValue(buildError("firstName", "REGISTRATION_007")));
			}
		});

		TargetTrakValidationError result = firstNameRulesMock.isFirstNameEmpty(firstName);
		assertNotNull("Validation Error should not be null", result);
		assertEquals("Registration 007 should be error", result.getErrorMessage(), "REGISTRATION_007");
	}

	@Test
	public void testFirstNameForActualValue() throws Exception {
		final String firstName = "Christina";
		mockery.checking(new Expectations() {
			{
				oneOf(firstNameRulesMock).isFirstNameEmpty(with(equal(firstName)));
				will(returnValue(with(equal(null))));
			}
		});

		TargetTrakValidationError result = firstNameRulesMock.isFirstNameEmpty(firstName);
		assertNull("Validation Error should be null", result);
	}
	
	@Test
	public void testFirstNameValidLength_forValidLength() throws Exception {
		final String firstName = "Christina";
		mockery.checking(new Expectations() {
			{
				oneOf(firstNameRulesMock).isFirstNameValidLength(with(equal(firstName)));
				will(returnValue(with(equal(null))));
			}
		});
		
		TargetTrakValidationError result = firstNameRulesMock.isFirstNameValidLength(firstName);
		assertNull("Validation Error should be null", result);
	}
	
	@Test
	public void testFirstNameValidLength_forInvalidLength() throws Exception {
		final String firstName = "Not a valid length";
		mockery.checking(new Expectations() {
			{
				oneOf(firstNameRulesMock).isFirstNameValidLength(with(equal(firstName)));
				will(returnValue(buildError("firstName", "REGISTRATION_008")));
			}
		});
		
		TargetTrakValidationError result = firstNameRulesMock.isFirstNameValidLength(firstName);
		assertEquals("Registration 008 should be error", result.getErrorMessage(), "REGISTRATION_008");
	}
	
	@Test
	public void testFirstNameContainsLetters_forValidCharacters() throws Exception {
		final String firstName = "Antoine";
		mockery.checking(new Expectations() {
			{
				oneOf(firstNameRulesMock).firstNameIsAlphabeticOnly(with(equal(firstName)));
				will(returnValue(null));
			}
		});
		
		TargetTrakValidationError result = firstNameRulesMock.firstNameIsAlphabeticOnly(firstName);
		assertNull("Validation Error should be null", result);
	}
	
	@Test
	public void testFirstNameContainsLetters_forInvalidCharacters() throws Exception {
		final String firstName = "Antoine1020";
		mockery.checking(new Expectations() {
			{
				oneOf(firstNameRulesMock).firstNameIsAlphabeticOnly(with(equal(firstName)));
				will(returnValue(buildError("firstName", "REGISTRATION_009")));
			}
		});
		
		TargetTrakValidationError result = firstNameRulesMock.firstNameIsAlphabeticOnly(firstName);
		assertEquals("Registration 009 should be error", result.getErrorMessage(), "REGISTRATION_009");
	}
	
}