package com.emporganizer.dao.employee;

import java.util.List;

import com.emporganizer.dao.RootDAO;
import com.emporganizer.models.employee.Shift;

public interface ShiftDAO extends RootDAO{
	public List<Shift> getShifts(int employeeId);
}
