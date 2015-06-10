package com.target.trak.system.web.controllers.referencedata;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.security.context.UserContext;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;

@Controller
public class CreateReferenceDataItemController {

	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> createReferenceDataService;

	private UserContext securityUserContext;

	@RequestMapping(value = "/refdata/createReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> createReferenceData(@RequestParam String type, @RequestParam String label, @RequestParam String value, @RequestParam String status) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setReferenceDataDto(buildCreateReferenceDataDto(type, label, value, status));
		ReferenceDataApiResponse response = createReferenceDataService.processRequest(request);
		jsonResponse.put("success", response.isSuccess());
		return jsonResponse;
	}

	private ReferenceDataDto buildCreateReferenceDataDto(String type, String label, String value, String status) {
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setType(type);
		dto.setLabel(label);
		dto.setValue(value);
		dto.setStatus(status);

		Calendar currentTime = Calendar.getInstance();
		String currentUser = securityUserContext.getCurrentUser().getUsername();
		dto.setCreatedBy(currentUser);
		dto.setCreatedDateTime(currentTime);
		dto.setLastUpdatedBy(currentUser);
		dto.setLastUpdatedDateTime(currentTime);
		return dto;
	}

	public void setCreateReferenceDataService(TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> createReferenceDataService) {
		this.createReferenceDataService = createReferenceDataService;
	}

	public void setSecurityUserContext(UserContext securityUserContext) {
		this.securityUserContext = securityUserContext;
	}
}