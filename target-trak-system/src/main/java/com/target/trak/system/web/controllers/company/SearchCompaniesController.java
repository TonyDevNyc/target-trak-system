package com.target.trak.system.web.controllers.company;

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
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.service.dto.company.CompanySearchCriteriaDto;
import com.target.trak.system.util.DateUtil;
import com.target.trak.system.web.views.ui.models.CompanyModel;

@Controller
public class SearchCompaniesController {

	private TargetTrakService<CompanyApiRequest, CompanyApiResponse> companiesByCriteriaService;

	@RequestMapping(value = "/company/getCompanies.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getCompanies(@RequestParam int page, @RequestParam int start, @RequestParam int limit) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		CompanyApiRequest request = buildRequest(null, null, null, null, page, start, limit);
		CompanyApiResponse response = companiesByCriteriaService.executeRequest(request);
		jsonResponse.put("data", buildUiCompanies(response.getCompanies()));
		jsonResponse.put("success", response.isSuccess());
		return jsonResponse;
	}

	@RequestMapping(value = "/company/searchCompaniesByCriteria.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> searchCompaniesByCriteria(@RequestParam String name, @RequestParam String city, @RequestParam String state, @RequestParam String country, @RequestParam int page, @RequestParam int start, @RequestParam int limit) {
		Map<String, Object> jsonResponse = new HashMap<String, Object>();
		CompanyApiRequest request = buildRequest(name, city, state, country, page, start, limit);
		CompanyApiResponse response = companiesByCriteriaService.executeRequest(request);
		jsonResponse.put("data", buildUiCompanies(response.getCompanies()));
		jsonResponse.put("success", response.isSuccess());
		return jsonResponse;
	}

	public List<CompanyModel> buildUiCompanies(final List<CompanyDto> dtos) {
		List<CompanyModel> list = new ArrayList<CompanyModel>();
		if (dtos != null && !dtos.isEmpty()) {
			CompanyModel company = null;
			for (CompanyDto dto : dtos) {
				company = new CompanyModel();
				company.setId(dto.getId());
				company.setName(dto.getName());
				company.setAddressLine1(dto.getAddressLine1());
				company.setAddressLine2(dto.getAddressLine2());
				company.setCity(dto.getCity());
				company.setState(dto.getState());
				company.setZipcode(dto.getZipcode());
				company.setCountry(dto.getCountry());
				company.setCreatedBy(dto.getCreatedBy());
				company.setCreateDateTime(DateUtil.convertDateToIso8601(dto.getCreateDateTime()));
				company.setLastUpdatedBy(dto.getLastUpdatedBy());
				company.setLastUpdatedDateTime(DateUtil.convertDateToIso8601(dto.getLastUpdatedDateTime()));
				list.add(company);
			}
		}
		return list;
	}

	public CompanyApiRequest buildRequest(String name, String city, String state, String country, int page, int start, int limit) {
		CompanyApiRequest request = new CompanyApiRequest();
		CompanySearchCriteriaDto dto = new CompanySearchCriteriaDto();
		dto.setCountry(country);
		dto.setName(name);
		dto.setState(state);
		dto.setCity(city);
		dto.setPage(page);
		dto.setStart(start);
		dto.setEnd(limit);
		request.setSearchCriteria(dto);
		return request;
	}

	public void setCompaniesByCriteriaService(TargetTrakService<CompanyApiRequest, CompanyApiResponse> companiesByCriteriaService) {
		this.companiesByCriteriaService = companiesByCriteriaService;
	}
}