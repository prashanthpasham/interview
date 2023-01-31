package com.timesheet;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeSheet {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String month;
	private double reportingHrs = 0.0;
	private String color;
	private String status = "A";
	private boolean disableEntry;
	private LocalDate timeSheetDate;

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getReportingHrs() {
		return reportingHrs;
	}

	public void setReportingHrs(double reportingHrs) {
		this.reportingHrs = reportingHrs;
	}

	public String getColor() {

		if ("P".equals(this.status))
			color = "white";
		else if ("A".equals(this.status))
			color = "red";
		else if ("L".equals(this.status))
			color = "yellow";
		else if ("H".equals(this.status))
			color = "orange";
		else if ("W".equals(this.status))
			color = "voilet";

		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStatus() {
		if ("A".equals(status) && (startTime != null || endTime != null)) {
			this.status = "P";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDisableEntry() {
		
		if ("L".equals(this.status) || "H".equals(this.status)
				|| "W".equals(this.status))
			disableEntry = true;

		return disableEntry;
	}

	public void setDisableEntry(boolean disableEntry) {
		this.disableEntry = disableEntry;
	}

	public LocalDate getTimeSheetDate() {
		return timeSheetDate;
	}

	public void setTimeSheetDate(LocalDate timeSheetDate) {
		this.timeSheetDate = timeSheetDate;
	}

}
