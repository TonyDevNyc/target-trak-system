package com.target.trak.system.validations;

import java.util.List;

public interface TargetTrakValidator<T> {

	public List<TargetTrakValidationError> validate(T object) throws TargetTrakValidationException;
}
