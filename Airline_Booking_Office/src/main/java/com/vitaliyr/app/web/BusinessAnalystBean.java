package com.vitaliyr.app.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.context.annotation.Scope;

import com.vitaliyr.app.service.BusinessAnalyticService;
import com.vitaliyr.app.service.Report;

@Named("ba")
@Scope("request")
public class BusinessAnalystBean implements Serializable {

	@Inject
	BusinessAnalyticService businessAnalyticService;

	private static final long serialVersionUID = 1L;

	List<Report> dateReport;
	List<Report> destinationReport;
	double totalSum;
	CartesianChartModel dateChart;
	CartesianChartModel destinationChart;

	private java.util.Date startDate = new java.util.Date();
	private java.util.Date endDate;
	private String desCity;

	public void getDateReport(ActionEvent e) {
		dateReport = businessAnalyticService.getDateReport(startDate, endDate);
		totalSum = businessAnalyticService.getTotalSum(startDate, endDate);

		dateChart = new CartesianChartModel();
		ChartSeries tickets = new ChartSeries();
		tickets.setLabel("Tickets");
		SimpleDateFormat dtFrm = new SimpleDateFormat("dd.MM.yyyy");
		for (Report r : dateReport) {
			tickets.set(dtFrm.format(r.getFdate()), r.getTicketCount());
		}
		dateChart.addSeries(tickets);
	}

	public void getDestinationReport(ActionEvent e) {
		destinationReport = businessAnalyticService.getDestinationReport(
				startDate, endDate);
		totalSum = businessAnalyticService.getTotalSum(startDate,
				endDate);

		destinationChart = new CartesianChartModel();
		ChartSeries tickets = new ChartSeries();
		tickets.setLabel("Tickets");
		for (Report r : destinationReport) {
			tickets.set(r.getDesCity(), r.getTicketCount());
		}
		destinationChart.addSeries(tickets);
	}

	public List<Report> getDateReport() {
		return dateReport;
	}

	public void setDateReport(List<Report> dateReport) {
		this.dateReport = dateReport;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}

	public CartesianChartModel getDateChart() {
		return dateChart;
	}

	public void setDateChart(CartesianChartModel dateChart) {
		this.dateChart = dateChart;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public String getDesCity() {
		return desCity;
	}

	public void setDesCity(String desCity) {
		this.desCity = desCity;
	}

	public List<Report> getDestinationReport() {
		return destinationReport;
	}

	public void setDestinationReport(List<Report> destinationReport) {
		this.destinationReport = destinationReport;
	}

	public CartesianChartModel getDestinationChart() {
		return destinationChart;
	}

	public void setDestinationChart(CartesianChartModel destinationChart) {
		this.destinationChart = destinationChart;
	}

	public BusinessAnalystBean() {
	}
}
