package com.target.trak.system.web.controllers.matters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.web.views.ui.dashboards.MatterTask;

@Controller
public class GetOpenMatterTasksController {

	@RequestMapping(value = "/matters/getMatterTasks.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> getMatterStatusBreakdown() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("success", Boolean.TRUE);
		data.put("data", buildMockData());
		return data;
	}
	
	public List<MatterTask> buildMockData() {
		List<MatterTask> list = new ArrayList<MatterTask>();
		list.add(new MatterTask("MTR-12345", "Wilkie Farr LLP.", "James Swanson, Esq.", "Court Filing", "4-11-15", "4-19-15", "Ron Goldy", "4-18-15"));
		list.add(new MatterTask("MTR-12345", "Wilkie Farr LLP.", "Nick Jackson, Esq.",  "Process Service", "4-11-15", "4-19-15", "Christina O'Sullivan", "4-18-15"));
		list.add(new MatterTask("MTR-12345", "Wilkie Farr LLP.",  "Christina McFinnegan", "Person Location", "4-10-15", "4-14-15", "Harlin Parker", "4-13-15"));
		
		list.add(new MatterTask("MTR-21345", "Celino & Barnes LLP.",  "James Todd Smith", "Process Service", "4-19-15", "4-19-15", "Harlin Parker", "4-18-15"));
		list.add(new MatterTask("MTR-21345", "Celino & Barnes LLP.",  "Tony Marra", "Process Service", "4-15-15", "4-19-15", "James Madison", "4-18-15"));
		list.add(new MatterTask("MTR-21345", "Celino & Barnes LLP.",  "Brenda Thomas, Esq.", "Commercial Investigation", "4-17-15", "4-19-15", "Peter Smith", "4-18-15"));
		list.add(new MatterTask("MTR-21345", "Celino & Barnes LLP.",  "", "Estate Settlement", "4-17-15", "4-19-15", "Timothy Smith", "4-18-15"));
		
		list.add(new MatterTask("MTR-10208", "Sullivan & Rahaman LLP.",  "Constantine Jameson", "Apostille Service", "4-12-15", "4-19-15", "Ron Goldy", "4-18-15"));
		list.add(new MatterTask("MTR-10208", "Sullivan & Rahaman LLP.",  "Kobe Bryant", "Commercial Investigation", "4-17-15", "4-19-15", "Harlin Parker", "4-18-15"));
		list.add(new MatterTask("MTR-10208", "Sullivan & Rahaman LLP.",  "Kevin Durant", "Litigation Support", "4-16-15", "4-19-15", "Christina O'Sullivan", "4-18-15"));
		list.add(new MatterTask("MTR-10208", "Sullivan & Rahaman LLP.",  "Bill Conway", "Court Filing", "4-9-15", "4-19-15", "Sergey Brim", "4-18-15"));
		list.add(new MatterTask("MTR-10208", "Sullivan & Rahaman LLP.",  "John Lines", "Process Service", "4-17-15", "4-19-15", "Ron Goldy", "4-18-15"));
		return list;
		
	}

	
}
