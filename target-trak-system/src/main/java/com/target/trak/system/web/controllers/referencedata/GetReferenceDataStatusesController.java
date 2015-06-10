package com.target.trak.system.web.controllers.referencedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.web.views.ui.common.NameValuePair;

@Controller
public class GetReferenceDataStatusesController {

	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> referenceDataService;
	
	@RequestMapping(value = "/refdata/getReferenceDataStatuses.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getReferenceDataStatuses(@RequestParam String referenceDataType) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setType(referenceDataType);
		request.setReferenceDataDto(dto);
		ReferenceDataApiResponse response = referenceDataService.processRequest(request);
		jsonResponse.put("success", response.isSuccess());
		jsonResponse.put("data", convertToNameValuePairs(response.getReferenceDataList()));
		return jsonResponse;
	}
	
	// TODO refactor to utility class
	private List<NameValuePair> convertToNameValuePairs(List<ReferenceDataDto> dtos) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (ReferenceDataDto dto : dtos) {
			list.add(new NameValuePair(dto.getLabel(), dto.getLabel()));
		}
		return list;
	}

	public void setReferenceDataService(TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> referenceDataService) {
		this.referenceDataService = referenceDataService;
	}
}
