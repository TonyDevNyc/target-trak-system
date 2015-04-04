package com.target.trak.system.web.controllers.referencedata;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Qualifier("createReferenceDataService")
	@Autowired
	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> createReferenceDataService;
	
	@Qualifier("userContext")
	@Autowired
	private UserContext securityUserContext;
	
	@RequestMapping(value = "/refdata/createReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> createReferenceData(@RequestParam String type, @RequestParam String label, @RequestParam String value) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setReferenceDataDto(buildCreateReferenceDataDto(type, label, value));
		ReferenceDataApiResponse response = createReferenceDataService.executeRequest(request);
		jsonResponse.put("success", response.isSuccess());
		return jsonResponse;
	}
	
	private ReferenceDataDto buildCreateReferenceDataDto(final String type, final String label, final String value) {
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setType(type);
		dto.setLabel(label);
		dto.setValue(value);

		Calendar currentTime = Calendar.getInstance();
		String currentUser = securityUserContext.getCurrentUser().getUsername();
		dto.setCreatedBy(currentUser);
		dto.setCreatedDateTime(currentTime);
		dto.setLastUpdatedBy(currentUser);
		dto.setLastUpdatedDateTime(currentTime);
		return dto;
	}
}
