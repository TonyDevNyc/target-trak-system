package com.target.trak.system.security.service;

import com.target.trak.system.security.dto.registration.RegistrationApiRequest;
import com.target.trak.system.security.dto.registration.RegistrationApiResponse;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;

public interface RegistrationService {

	public RegistrationApiResponse registerUser(final RegistrationApiRequest request) throws TargetTrakSecurityException;

}
