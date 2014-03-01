package com.vitaliyr.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.vitaliyr.app.dao.BusinessAnalystDAO;

@Named
public class BusinessAnalyticService {
	@Inject
	BusinessAnalystDAO businessAnalystDAO;
	
	@Transactional
	public double getTotalSum(Date startDate, Date endDate) {

		Timestamp st = new Timestamp(startDate.getTime());
		Timestamp nd = new Timestamp(endDate.getTime());

		return businessAnalystDAO.getTotalSum(st, nd);
	}


	@Transactional
	public List<Report> getDateReport(Date startDate, Date endDate) {
		Timestamp st = new Timestamp(startDate.getTime());
		Timestamp nd = new Timestamp(endDate.getTime());

		return businessAnalystDAO.getDateReport(st, nd);
	}

	@Transactional
	public List<Report> getDestinationReport(Date startDate, Date endDate) {
		Timestamp st = new Timestamp(startDate.getTime());
		Timestamp nd = new Timestamp(endDate.getTime());

		return businessAnalystDAO.getDestinationReport(st, nd);
	}

}
