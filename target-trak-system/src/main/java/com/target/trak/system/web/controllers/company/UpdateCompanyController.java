package com.target.trak.system.web.controllers.company;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.security.context.UserContext;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.dto.company.CompanyDto;

@Controller
public class UpdateCompanyController {

	private TargetTrakService<CompanyApiRequest, CompanyApiResponse> service;
	
	private UserContext securityUserContext;
	
	@RequestMapping(value = "/company/updateCompany.json", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody Map<String, Object> updateCompany(
			@RequestParam Long id,
			@RequestParam String name,
			@RequestParam String addressLine1,
			@RequestParam String addressLine2,
			@RequestParam String city,
			@RequestParam String state,
			@RequestParam String zipcode,
			@RequestParam String country) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		CompanyApiRequest request = new CompanyApiRequest();
		request.setCompany(buildCompanyDto(id, name, addressLine1, addressLine2, city, state, zipcode, country));
		CompanyApiResponse response = service.processRequest(request);
		jsonResponse.put("success", response.isSuccess());
		return jsonResponse;
	}
	
	private CompanyDto buildCompanyDto(final Long id, final String name, String addressLine1, String addressLine2, String city, String state, String zipcode, String country) {
		CompanyDto dto = new CompanyDto();
		dto.setId(id == 0L ? null : id);
		dto.setName(StringUtils.isEmpty(name) ? null : name);
		dto.setAddressLine1(StringUtils.isEmpty(addressLine1) ? null : addressLine1);
		dto.setAddressLine2(StringUtils.isEmpty(addressLine2) ? null : addressLine2);
		dto.setCity(StringUtils.isEmpty(city) ? null : city);
		dto.setState(StringUtils.isEmpty(state) ? null : state);
		dto.setZipcode(StringUtils.isEmpty(zipcode) ? null : zipcode);
		dto.setCountry(StringUtils.isEmpty(country) ? null : country);
		dto.setCreatedBy(securityUserContext.getCurrentUser().getUsername());
		dto.setLastUpdatedBy(securityUserContext.getCurrentUser().getUsername());
		return dto;
	}

	public void setService(TargetTrakService<CompanyApiRequest, CompanyApiResponse> service) {
		this.service = service;
	}

	public void setSecurityUserContext(UserContext securityUserContext) {
		this.securityUserContext = securityUserContext;
	}
}