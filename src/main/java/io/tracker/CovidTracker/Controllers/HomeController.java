package io.tracker.CovidTracker.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.tracker.CovidTracker.Models.LocationStats;
import io.tracker.CovidTracker.Services.CovidDataService;


@Controller
public class HomeController
{
	@Autowired
	CovidDataService covidDataService;		
	
	
	@GetMapping("/")
	// here model will do some things add some task (work on the data) and that data that then can be displayed or used by html
	public String home(Model model)
	{
		 	List<LocationStats> allStats = covidDataService.getAllStats();
	        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
	        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
	        model.addAttribute("locationStats", allStats);
	        model.addAttribute("totalReportedCases", totalReportedCases);
	        model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("LocationStats",covidDataService.getAllStats());
		return "home";
	}
}
