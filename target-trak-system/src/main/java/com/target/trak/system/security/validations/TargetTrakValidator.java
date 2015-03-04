package com.target.trak.system.security.validations;

import java.util.List;

public interface TargetTrakValidator<T> {

	public List<SecurityValidationError> validate(T object) throws IllegalArgumentException;
}
