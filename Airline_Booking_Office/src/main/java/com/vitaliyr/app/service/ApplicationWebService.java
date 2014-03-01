package com.vitaliyr.app.service;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vitaliyr.app.entity.Flights;

/*
 * Simple RESTful Web Service available at /webservices/dayflights/
 */

@Controller
public class ApplicationWebService {

	@Inject
	CustomerService customerService;

	 @RequestMapping("/dayflights")
	   public @ResponseBody String showFlights() {

		 List<Flights> list = customerService.getCurrentDayFlights();
		 
		 if (list.isEmpty()) {
			 return "<h2>Sorry, no flights today.</h2>";
		 }
		 
		 String result = "<table style=\"border: 1px solid black; text-align:center; width:600px;\">"
		 		+ "<tr>"
		 		+ "<th>IATA</th>"
		 		+ "<th>Date</th>"
		 		+ "<th>Time</th>"
		 		+ "<th>City</th>"
		 		+ "<th>Airport</th>"
		 		+ "<th>Seats</th>"
		 		+ "<th>Ticket price</th>"
		 		+ "</tr>";
		 StringBuilder data = new StringBuilder(result);
		 SimpleDateFormat dtFrm = new SimpleDateFormat("dd.MM.yyyy");
		 SimpleDateFormat tmFrm = new SimpleDateFormat("HH:mm");
		 
		 for(Flights f: list) {
			 data.append(
					"<tr>"
			 		+ "<td>" + f.getFcode() + "</td>" 
					+ "<td>" + dtFrm.format(f.getFdate()) + "</td>" 
					+ "<td>" + tmFrm.format(f.getFdate()) + "</td>" 
			 		+ "<td>" + f.getDesCity() + "</td>" 
					+ "<td>" + f.getDesPort() + "</td>" 
					+ "<td>" + f.getAvSeats() + "</td>" 
			 		+ "<td>" + f.getTicketPrice() + "</td>" 
					+ "</tr>");
		 }
		 result.concat(data.toString());
		 
		 return data.toString();
	   }
}
