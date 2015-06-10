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
import com.target.trak.system.util.DateUtil;
import com.target.trak.system.web.views.ui.models.ReferenceDataModel;

@Controller
public class GetReferenceDataByTypeController {

	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> referenceDataService;

	@RequestMapping(value = "/refdata/getReferenceDataByType.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getReferenceDataByType(@RequestParam String type, @RequestParam String label, @RequestParam String value) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		ReferenceDataApiResponse response = referenceDataService.processRequest(request);
		jsonResponse.put("success", response.isSuccess());
		jsonResponse.put("data", buildReferenceDataModel(response.getReferenceDataList()));
		return jsonResponse;
	}
	
	private List<ReferenceDataModel> buildReferenceDataModel(List<ReferenceDataDto> dtos) {
		List<ReferenceDataModel> models = new ArrayList<ReferenceDataModel>();
		ReferenceDataModel model = null;
		for (ReferenceDataDto dto : dtos) {
			model = new ReferenceDataModel();
			model.setId(dto.getId());
			model.setType(dto.getType());
			model.setLabel(dto.getLabel());
			model.setValue(dto.getValue());
			model.setCreatedBy(dto.getCreatedBy());
			model.setCreatedDateTime(DateUtil.convertDateToIso8601(dto.getCreatedDateTime()));
			model.setLastUpdatedBy(dto.getLastUpdatedBy());
			model.setLastUpdatedDateTime(DateUtil.convertDateToIso8601(dto.getLastUpdatedDateTime()));
			models.add(model);
		}
		return models;
	}
}