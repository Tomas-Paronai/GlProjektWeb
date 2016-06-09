package com.emporganizer.dao.employee;

import java.sql.Date;
import java.util.List;

import com.emporganizer.dao.RootDAO;
import com.emporganizer.models.employee.Shift;

public interface ShiftDAO extends RootDAO{
	
	public static final int DAYS[] = {-365,-30,-7,7,30,365,0};
	
	public List<Shift> getShifts(int employeeId);
	public List<Shift> getShifts(int employeeId, int period);
	public Date getDatePeriod(int period);
}
