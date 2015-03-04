package com.target.trak.system.security.service;

import com.target.trak.system.security.dto.credentials.ForgotPasswordApiRequest;
import com.target.trak.system.security.dto.credentials.ForgotPasswordApiResponse;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;

public interface ForgotPasswordService {

	public ForgotPasswordApiResponse handleForgotPassword(final ForgotPasswordApiRequest request) throws TargetTrakSecurityException;
}
