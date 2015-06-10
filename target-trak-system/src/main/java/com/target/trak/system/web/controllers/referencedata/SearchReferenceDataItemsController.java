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
import com.target.trak.system.service.dto.referencedata.ReferenceDataSearchCriteriaDto;
import com.target.trak.system.util.DateUtil;
import com.target.trak.system.web.views.ui.models.ReferenceDataModel;

@Controller
public class SearchReferenceDataItemsController {

	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> referenceDataByCriteriaService;
	
	@RequestMapping(value = "/refdata/searchReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> searchForReferenceData(@RequestParam String referenceDataType, @RequestParam String status, @RequestParam int page, @RequestParam int start, @RequestParam int limit) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setSearchCriteria(buildCriteria(referenceDataType, status, page, start, limit));
		ReferenceDataApiResponse response = referenceDataByCriteriaService.processRequest(request);
		jsonResponse.put("data", buildReferenceDataModel(response.getReferenceDataList()));
		jsonResponse.put("success", response.isSuccess());
		jsonResponse.put("totalSize", response.getTotalSize());
		return jsonResponse;
	}
	
	@RequestMapping(value = "/refdata/getReferenceData.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getPagedReferenceData(@RequestParam int page, @RequestParam int start, @RequestParam int limit) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setSearchCriteria(buildCriteria(null, null, page, start, limit));
		ReferenceDataApiResponse response = referenceDataByCriteriaService.processRequest(request);
		jsonResponse.put("data", buildReferenceDataModel(response.getReferenceDataList()));
		jsonResponse.put("success", response.isSuccess());
		jsonResponse.put("totalSize", response.getTotalSize());
		return jsonResponse;
	}
	
	private ReferenceDataSearchCriteriaDto buildCriteria(String referenceDataType, String status, int page, int start, int limit) {
		ReferenceDataSearchCriteriaDto criteria = new ReferenceDataSearchCriteriaDto();
		criteria.setReferenceDataType(referenceDataType);
		criteria.setPage(page);
		criteria.setStart(start);
		criteria.setEnd(limit);
		criteria.setStatus(status);
		return criteria;
	}
	
	private List<ReferenceDataModel> buildReferenceDataModel(List<ReferenceDataDto> dtos) {
		List<ReferenceDataModel> models = new ArrayList<ReferenceDataModel>();
		ReferenceDataModel model = null;
		for (ReferenceDataDto dto : dtos) {
			model = new ReferenceDataModel();
			model.setId(dto.getId());
			model.setType(dto.getType());
			model.setLabel(dto.getLabel());
			model.setStatus(dto.getStatus());
			model.setValue(dto.getValue());
			model.setCreatedBy(dto.getCreatedBy());
			model.setCreatedDateTime(DateUtil.convertDateToIso8601(dto.getCreatedDateTime()));
			model.setLastUpdatedBy(dto.getLastUpdatedBy());
			model.setLastUpdatedDateTime(DateUtil.convertDateToIso8601(dto.getLastUpdatedDateTime()));
			models.add(model);
		}
		return models;
	}
	
	public void setReferenceDataByCriteriaService(TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> referenceDataByCriteriaService) {
		this.referenceDataByCriteriaService = referenceDataByCriteriaService;
	}
}