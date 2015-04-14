package com.target.trak.system.web.controllers.referencedata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.target.trak.system.service.dto.referencedata.ReferenceDataSearchCriteriaDto;
import com.target.trak.system.web.views.ui.models.ReferenceDataModel;

@Controller
public class SearchReferenceDataItemsController {

	@Qualifier("referenceDataByCriteriaService")
	@Autowired
	private TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> referenceDataByCriteriaService;

	@Qualifier("userContext")
	@Autowired
	private UserContext securityUserContext;
	
	@RequestMapping(value = "/refdata/searchReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> searchForReferenceData(@RequestParam String referenceDataType, @RequestParam int page, @RequestParam int start, @RequestParam int limit) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setSearchCriteria(buildCriteria(referenceDataType, page, start, limit));
		ReferenceDataApiResponse response = referenceDataByCriteriaService.executeRequest(request);
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
		request.setSearchCriteria(buildCriteria(null, page, start, limit));
		ReferenceDataApiResponse response = referenceDataByCriteriaService.executeRequest(request);
		jsonResponse.put("data", buildReferenceDataModel(response.getReferenceDataList()));
		jsonResponse.put("success", response.isSuccess());
		jsonResponse.put("totalSize", response.getTotalSize());
		return jsonResponse;
	}
	
	private ReferenceDataSearchCriteriaDto buildCriteria(String referenceDataType, int page, int start, int limit) {
		ReferenceDataSearchCriteriaDto criteria = new ReferenceDataSearchCriteriaDto();
		criteria.setReferenceDataType(referenceDataType);
		criteria.setPage(page);
		criteria.setStart(start);
		criteria.setEnd(limit);
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
			model.setValue(dto.getValue());
			model.setCreatedBy(dto.getCreatedBy());
			model.setCreatedDateTime(convertDateToIso8601(dto.getCreatedDateTime()));
			model.setLastUpdatedBy(dto.getLastUpdatedBy());
			model.setLastUpdatedDateTime(convertDateToIso8601(dto.getLastUpdatedDateTime()));
			models.add(model);
		}
		return models;
	}
	
	private String convertDateToIso8601(final Calendar calendar) {
		if (calendar == null) {
			return "";
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		String iso8601Date = df.format(new Date(calendar.getTimeInMillis()));
		return iso8601Date;
	}

}
