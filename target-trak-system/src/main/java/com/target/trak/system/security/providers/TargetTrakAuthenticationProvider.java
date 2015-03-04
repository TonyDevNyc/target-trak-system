package com.target.trak.system.security.providers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.target.trak.system.security.domain.TargetTrakUser;
import com.target.trak.system.security.service.impl.TargetTrakUserDetailsServiceImpl;

@Component("targetTrakAuthenticationProvider")
public class TargetTrakAuthenticationProvider implements AuthenticationProvider {
	
	@Qualifier("targetTrakUserDetailsService")
	@Autowired
	private TargetTrakUserDetailsServiceImpl userDetailsService;
	
	@Qualifier("encoder")
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName().toUpperCase();
		final String password = (String) authentication.getCredentials();
		
		final UserDetails user = (TargetTrakUser) userDetailsService.loadUserByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("Username: ["+username+"] not found");
		}
		
		if (!encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Invalid Credentials");
		}
		
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
