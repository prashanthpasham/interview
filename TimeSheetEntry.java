package com.timesheet;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeSheetEntry {
	public static void main(String[] args) {
		List<String> holidays = Arrays.asList("15012023", "26012023");
		String[] weekends = { "SAT", "SUN" };
        List<String> leaves = Arrays.asList("27012023","28012023");
		// generate last three months
		List<String> monthLs = new ArrayList<String>();
		DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("MMM-yyyy");
		DateTimeFormatter datePattern2 = DateTimeFormatter.ofPattern("MMyyyy");
		DateTimeFormatter datePattern3 = DateTimeFormatter.ofPattern("dd-MMM-yy");
		DateTimeFormatter datePattern4 = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDate today = LocalDate.now();
		for (int k = 0; k < 3; k++) {
			LocalDate sel = today.minusMonths(k);
			monthLs.add(datePattern.format(sel) + "@"
					+ datePattern2.format(sel));
		}
		String selectedMon = monthLs.get(0).split("@")[1];
		System.out.println("selectedMon>>"+selectedMon);
		YearMonth ym = YearMonth.of(Integer.parseInt(selectedMon.substring(2,
				selectedMon.length())), Integer.parseInt(selectedMon.substring(
				0, 2)));
		LocalDate selectedMonth = ym.atDay(1);
		TemporalAdjuster ta = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
		LocalDate selMonth = selectedMonth.with(ta);
		LocalDate endOfMonth = ym.atEndOfMonth();
		LocalDate weekStart = selMonth;
		List<String> weekLs = new ArrayList<String>();
		while (!weekStart.isAfter(endOfMonth)) {
			LocalDate week = weekStart.plusDays(6);
			weekLs.add(datePattern3.format(weekStart) + ":"
					+ datePattern3.format(week));
			weekStart = weekStart.plusWeeks(1);
		}
       System.out.println("Week List for selected month :: ");
		for (String wk : weekLs) {
			System.out.println(wk);
		}
		// add day wise timesheet
		String[] selectedWeek = weekLs.get(weekLs.size()-1).split(":");
		LocalDate selWkStart = LocalDate.parse(selectedWeek[0], datePattern3);
		LocalDate selWkEnd = LocalDate.parse(selectedWeek[1], datePattern3);
		List<TimeSheet> timeSheetDay = new ArrayList<TimeSheet>();
		do {
			LocalDate day = selWkStart.plusDays(1);
			// System.out.println(datePattern3.format(day));
			TimeSheet sheet = new TimeSheet();
			sheet.setTimeSheetDate(day);
			sheet.setMonth(datePattern2.format(day));
			if(day.isAfter(today)){
				sheet.setDisableEntry(true);
			}
			if (weekends.length > 0) {
				for (String wkEnd : weekends) {
					//System.out.println(day.getDayOfWeek().name().substring(0,3));
					if (wkEnd.equals(day.getDayOfWeek().name().substring(0,3))) {
						sheet.setStatus("W");
						break;
					}
				}
			} 
			if (!holidays.isEmpty()) {
				for (String holiday : holidays) {
					if (holiday.equals(datePattern4.format(day))) {
						sheet.setStatus("H");
						break;
					}
				}
			} 
			
			if (!leaves.isEmpty()) {
				for (String leave : leaves) {
					if (leave.equals(datePattern4.format(day))) {
						sheet.setStatus("L");
						break;
					}
				}
			}
			timeSheetDay.add(sheet);
			selWkStart = day;
		} while (!selWkStart.isAfter(selWkEnd));
		
		for(TimeSheet ts : timeSheetDay){
			System.out.println("month::"+ts.getMonth()+","+"day::"+datePattern3.format(ts.getTimeSheetDate())+
					"Status::"+ts.getStatus()+" disable::"+ts.isDisableEntry());
		}

	}
}
