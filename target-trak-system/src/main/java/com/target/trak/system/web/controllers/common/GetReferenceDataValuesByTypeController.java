package com.target.trak.system.web.controllers.common;

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
public class GetReferenceDataValuesByTypeController {

	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> service;

	@RequestMapping(value = "/common/getReferenceDataValuesByType.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getReferenceDataValuesByType(@RequestParam String referenceDataType) {
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setType(referenceDataType);
		request.setReferenceDataDto(dto);

		Map<String, Object> map = new HashMap<String, Object>();
		ReferenceDataApiResponse response = service.executeRequest(request);
		map.put("success", Boolean.TRUE);
		map.put("data", convertReferenceDataList(response.getReferenceDataList()));
		return map;
	}

	private List<NameValuePair> convertReferenceDataList(final List<ReferenceDataDto> referenceDataList) {
		if (referenceDataList == null || referenceDataList.isEmpty()) {
			return new ArrayList<NameValuePair>();
		}

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair nvp = null;
		for (ReferenceDataDto referenceData : referenceDataList) {
			nvp = new NameValuePair(referenceData.getLabel(), referenceData.getValue());
			list.add(nvp);
		}
		return list;
	}

	public void setService(TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> service) {
		this.service = service;
	}
}