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
public class UpdateReferenceDataItemController {

	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> updateReferenceDataService;
	
	private UserContext securityUserContext;
	
	@RequestMapping(value = "/refdata/updateReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> updateReferenceData(@RequestParam Long id, @RequestParam String type, @RequestParam String label, @RequestParam String value, @RequestParam String status) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setReferenceDataDto(buildReferenceDataDto(id, type, label, value, status));
		ReferenceDataApiResponse response = updateReferenceDataService.processRequest(request);
		jsonResponse.put("success", response.isSuccess());
		return jsonResponse;
	}
	
	private ReferenceDataDto buildReferenceDataDto(final Long id, final String type, final String label, final String value, final String status) {
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setId(id);
		dto.setType(type);
		dto.setLabel(label);
		dto.setValue(value);
		dto.setStatus(status);
		dto.setLastUpdatedBy(securityUserContext.getCurrentUser().getUsername());
		dto.setLastUpdatedDateTime(Calendar.getInstance());
		return dto;
	}

	public void setUpdateReferenceDataService(TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> updateReferenceDataService) {
		this.updateReferenceDataService = updateReferenceDataService;
	}

	public void setSecurityUserContext(UserContext securityUserContext) {
		this.securityUserContext = securityUserContext;
	}
}