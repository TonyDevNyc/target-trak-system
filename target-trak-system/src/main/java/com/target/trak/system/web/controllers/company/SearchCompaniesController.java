package com.target.trak.system.web.controllers.company;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.web.forms.CompanySearchForm;

@Controller
public class SearchCompaniesController {

	private TargetTrakService<CompanyApiRequest, CompanyApiResponse> companiesByCriteriaService;

	@RequestMapping(value = "/company/searchCompanies.json", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> searchCompanies(@RequestBody CompanySearchForm companySearchForm, @RequestParam int page, @RequestParam int start, @RequestParam int limit) {
		System.out.println("Search Companies called");
		return null;
	}

	public void setCompaniesByCriteriaService(TargetTrakService<CompanyApiRequest, CompanyApiResponse> companiesByCriteriaService) {
		this.companiesByCriteriaService = companiesByCriteriaService;
	}
}
