package com.target.trak.system.web.controllers.referencedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.security.context.UserContext;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.web.views.ui.common.NameValuePair;

@Controller
public class GetReferenceDataTypesController {

	@Qualifier("userContext")
	@Autowired
	private UserContext securityUserContext;
	
	@Qualifier("referenceDataTypesService")
	@Autowired
	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> referenceDataTypesService;

	@RequestMapping(value = "/refdata/getReferenceDataTypes.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, Object> getReferenceDataTypes() {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiResponse response = referenceDataTypesService.executeRequest(null);
		List<NameValuePair> list = convertToNameValuePairs(response.getReferenceDataList());
		jsonResponse.put("referenceDataTypes", list);
		jsonResponse.put("success", response.isSuccess());
		return jsonResponse;
	}
	
	private List<NameValuePair> convertToNameValuePairs(List<ReferenceDataDto> dtos) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair nvp = null;
		for (ReferenceDataDto dto : dtos) {
			nvp = new NameValuePair(dto.getType(), dto.getType());
			list.add(nvp);
		}
		return list;
	}	
}