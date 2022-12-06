/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.DAO;

import com.project.AttendanceManagementSystem.Model.Schedule;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface ScheduleDao {
    
    List<Schedule>  FindScheduleByCourse(int CourseCode) throws DataAccessException;
    List<Schedule>  FindScheduleByDay(String day) throws DataAccessException;
    Schedule CreateNewSchedule(Schedule schedule) throws DataAccessException;
    Schedule UpdateSchedule(Schedule schedule) throws DataAccessException;
    String DeleteScheduleByDayID(String day, int CourseCode) throws DataAccessException;
    String DeleteScheduleByID(int CourseCode) throws DataAccessException;
}
