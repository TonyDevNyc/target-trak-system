package com.target.trak.system.web.controllers.matters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.web.views.ui.dashboards.DailyOpenMatters;

@Controller
public class GetWeeklyOpenMattersChartController {

	@RequestMapping(value = "/matters/getWeeklyOpenMatters.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getMatterStatusBreakdown() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("success", Boolean.TRUE);
		data.put("data", buildMockData());
		return data;
	}
	
	private List<DailyOpenMatters> buildMockData() {
		List<DailyOpenMatters> list = new ArrayList<DailyOpenMatters>();
		list.add(new DailyOpenMatters("Mon (4/17)", 10));
		list.add(new DailyOpenMatters("Tue (4/18)", 20));
		list.add(new DailyOpenMatters("Wed (4/19)", 8));
		list.add(new DailyOpenMatters("Thur (4/20)", 55));
		list.add(new DailyOpenMatters("Fri (4/21)", 19));
		list.add(new DailyOpenMatters("Sat (4/22)", 56));
		list.add(new DailyOpenMatters("Sun (4/23)", 9));
		return list;
	}
}
