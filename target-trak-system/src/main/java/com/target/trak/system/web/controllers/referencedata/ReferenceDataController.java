package com.target.trak.system.web.controllers.referencedata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.security.context.UserContext;
import com.target.trak.system.service.ReferenceDataService;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.service.dto.referencedata.ReferenceDataSearchCriteriaDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.web.views.ui.common.NameValuePair;
import com.target.trak.system.web.views.ui.common.UIValidationError;
import com.target.trak.system.web.views.ui.refdata.ReferenceDataModel;

@Controller
public class ReferenceDataController {

	@Qualifier("referenceDataService")
	@Autowired
	private ReferenceDataService referenceDataService;

	@Qualifier("userContext")
	@Autowired
	private UserContext securityUserContext;

	@Qualifier("messageSource")
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/refdata/getReferenceDataTypes.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getReferenceDataTypes() {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiResponse response = referenceDataService.getReferenceDataTypes();
		List<NameValuePair> list = convertToNameValuePairs(response.getReferenceDataList());
		jsonResponse.put("referenceDataTypes", list);
		jsonResponse.put("success", Boolean.TRUE);
		return jsonResponse;
	}

	@RequestMapping(value = "/refdata/getReferenceData.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getPagedReferenceData(@RequestParam int page, @RequestParam int start, @RequestParam int limit) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setSearchCriteria(buildCriteria(null, page, start, limit));
		ReferenceDataApiResponse response;

		try {
			response = referenceDataService.getReferenceDataByCriteria(request);
			jsonResponse.put("data", buildReferenceDataModel(response.getReferenceDataList()));
			jsonResponse.put("success", Boolean.TRUE);
			jsonResponse.put("totalSize", response.getTotalSize());
		} catch (TargetTrakException e) {
			response = new ReferenceDataApiResponse();
			response.setSuccess(Boolean.FALSE);
		}
		return jsonResponse;
	}

	@RequestMapping(value = "/refdata/searchReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> searchForReferenceData(@RequestParam String referenceDataType, @RequestParam int page, @RequestParam int start, @RequestParam int limit) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setSearchCriteria(buildCriteria(referenceDataType, page, start, limit));
		ReferenceDataApiResponse response;

		try {
			response = referenceDataService.getReferenceDataByCriteria(request);
			jsonResponse.put("data", buildReferenceDataModel(response.getReferenceDataList()));
			jsonResponse.put("success", Boolean.TRUE);
			jsonResponse.put("totalSize", response.getTotalSize());
		} catch (TargetTrakException e) {
			response = new ReferenceDataApiResponse();
			response.setSuccess(Boolean.FALSE);
		}
		return jsonResponse;
	}

	@RequestMapping(value = "/refdata/updateReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> updateReferenceData(@RequestParam Long id, @RequestParam String type, @RequestParam String label, @RequestParam String value) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setReferenceDataDto(buildReferenceDataDto(id, type, label, value));
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();

		try {
			response = referenceDataService.updateReferenceData(request);
			boolean success = response.isSuccess();

			if (!success) {
				jsonResponse.put("errors", convertValidationErrors(response.getErrors()));
				jsonResponse.put("message", response.getMessage());
			}
			jsonResponse.put("success", success);
		} catch (TargetTrakException e) {
			response.setSuccess(Boolean.FALSE);
		}
		return jsonResponse;
	}

	@RequestMapping(value = "/refdata/deleteReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> deleteReferenceData(@RequestParam Long referenceDataId) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setReferenceDataDto(buildReferenceDataDto(referenceDataId, null, null, null));
		ReferenceDataApiResponse response = null;
		try {
			response = referenceDataService.deleteReferenceData(request);
			boolean success = response.isSuccess();

			if (!success) {
				jsonResponse.put("errors", convertValidationErrors(response.getErrors()));
				jsonResponse.put("message", response.getMessage());
			}
			jsonResponse.put("success", success);
		} catch (TargetTrakException e) {
			response = new ReferenceDataApiResponse();
			response.setSuccess(Boolean.FALSE);
		}
		jsonResponse.put("success", Boolean.TRUE);
		return jsonResponse;
	}
	
	@RequestMapping(value = "/refdata/createReferenceData.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> createReferenceData(@RequestParam String type, @RequestParam String label, @RequestParam String value) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		ReferenceDataApiRequest request = new ReferenceDataApiRequest();
		request.setReferenceDataDto(buildCreateReferenceDataDto(type, label, value));
		ReferenceDataApiResponse response = null;
		try {
			response = referenceDataService.createReferenceData(request);
			boolean success = response.isSuccess();

			if (!success) {
				jsonResponse.put("errors", convertValidationErrors(response.getErrors()));
				jsonResponse.put("message", response.getMessage());
			}
			jsonResponse.put("success", success);
		} catch (TargetTrakException e) {
			response = new ReferenceDataApiResponse();
			response.setSuccess(Boolean.FALSE);
		}
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
	
	private List<UIValidationError> convertValidationErrors(List<TargetTrakValidationError> validationErrors) {
		List<UIValidationError> errors = new ArrayList<UIValidationError>();
		if (validationErrors != null && !validationErrors.isEmpty()) {
			String msg = null;
			for (TargetTrakValidationError validationError : validationErrors) {
				msg = messageSource.getMessage(validationError.getErrorMessage(), new Object[]{}, Locale.US);
				errors.add(new UIValidationError(validationError.getFieldName(), msg));
			}
		}
		return errors;
	}

	private ReferenceDataDto buildReferenceDataDto(final Long id, final String type, final String label, final String value) {
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setId(id);
		dto.setType(type);
		dto.setLabel(label);
		dto.setValue(value);
		dto.setLastUpdatedBy(securityUserContext.getCurrentUser().getUsername());
		dto.setLastUpdatedDateTime(Calendar.getInstance());
		return dto;
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

	private List<NameValuePair> convertToNameValuePairs(List<ReferenceDataDto> dtos) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair nvp = null;
		for (ReferenceDataDto dto : dtos) {
			nvp = new NameValuePair(dto.getType(), dto.getType());
			list.add(nvp);
		}
		return list;
	}

	private ReferenceDataSearchCriteriaDto buildCriteria(String referenceDataType, int page, int start, int limit) {
		ReferenceDataSearchCriteriaDto criteria = new ReferenceDataSearchCriteriaDto();
		criteria.setReferenceDataType(referenceDataType);
		criteria.setPage(page);
		criteria.setStart(start);
		criteria.setEnd(limit);
		return criteria;
	}
}