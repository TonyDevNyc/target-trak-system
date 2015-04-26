package com.target.trak.system.web.controllers.matters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.web.views.ui.dashboards.MatterStatusChart;

@Controller
public class GetMatterStatusPieChartController {

	@RequestMapping(value = "/matters/getMatterStatusBreakdown.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getMatterStatusBreakdown() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("success", Boolean.TRUE);
		data.put("data", buildPieChartData());
		return data;
	}

	private List<MatterStatusChart> buildPieChartData() {
		List<MatterStatusChart> pieChartList = new ArrayList<MatterStatusChart>();
		pieChartList.add(buildMatterStatusPieChart("Open", "Open", 17.0, 17));
		pieChartList.add(buildMatterStatusPieChart("In-active", "In-active", 15.5, 28));
		pieChartList.add(buildMatterStatusPieChart("Completed", "Completed", 36.5, 108));
		pieChartList.add(buildMatterStatusPieChart("Created", "Created", 15.0, 20));
		pieChartList.add(buildMatterStatusPieChart("In-Progress", "In-Progress", 14.0, 10));
		return pieChartList;
	}

	private MatterStatusChart buildMatterStatusPieChart(String status, String label, double percentage, int count) {
		return new MatterStatusChart(status, label, percentage, count);
	}
}
