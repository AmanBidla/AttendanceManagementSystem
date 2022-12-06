/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Service;


import com.project.AttendanceManagementSystem.DAO.ScheduleDao;
import com.project.AttendanceManagementSystem.Model.Schedule;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aman Bidla
 */
@Service
public class ScheduleService implements ScheduleDao{
    
    private Logger logger = Logger.getLogger(ScheduleService.class.toString());
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public ScheduleService(DataSource dataSource) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<Schedule> FindScheduleByCourse(int CourseCode) throws DataAccessException {
       List<Schedule> schedule = new ArrayList();
        try{     
            schedule = jdbcTemplate.query("SELECT * FROM Schedule WHERE CourseCode=?",(resultSet, rowNum) -> new Schedule(
                resultSet.getString("Days"),
                resultSet.getInt("CourseCode")
                ),
                CourseCode);       
        return schedule;
       }
       catch(DataAccessException ex){
           throw ex;
        }   
    }

    @Override
    public List<Schedule> FindScheduleByDay(String day) throws DataAccessException {
        List<Schedule> schedule = new ArrayList();
        try {
            schedule = jdbcTemplate.query("SELECT * FROM Schedule WHERE Days=?", (resultSet, rowNum) -> new Schedule(
                    resultSet.getString("Days"),
                    resultSet.getInt("CourseCode")
            ),
            day);
            return schedule;
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public Schedule CreateNewSchedule(Schedule schedule) throws DataAccessException {        
        try {         
           int course=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Course WHERE Code=?", new Object[] {schedule.getCourseCode()}, Integer.class);   
           if(course>0){ 
                jdbcTemplate.update("INSERT INTO Schedule (Days, CourseCode)"
                        + "VALUES (?, ?)",
                        new Object[]{schedule.getDays(), schedule.getCourseCode()});
                schedule = jdbcTemplate.queryForObject("SELECT * FROM Schedule WHERE Days=?", (resultSet, rowNum) -> new Schedule(
                        resultSet.getString("Days"),
                        resultSet.getInt("CourseCode")
                ),
                schedule.getDays());
                return schedule;
             }
            } catch (DataAccessException ex) {
                throw ex;
            }
            Schedule emptyschedule = new Schedule();
            return emptyschedule;
    }

    @Override
    public Schedule UpdateSchedule(Schedule schedule) throws DataAccessException {
      try{ 
        int course=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Course WHERE Code=?", new Object[] {schedule.getCourseCode()}, Integer.class);   
        if(course>0){  
            jdbcTemplate.update("UPDATE Schedule SET CourseCode = ? WHERE Days = ? AND CourseCode=?",
                                        new Object[] 
                                        {schedule.getCourseCode(),schedule.getDays(),schedule.getCourseCode()});
                schedule = jdbcTemplate.queryForObject("SELECT * FROM Schedule WHERE Days=?", (resultSet, rowNum) -> new Schedule(
                        resultSet.getString("Days"),
                        resultSet.getInt("CourseCode")
                ),
                schedule.getDays());
                return schedule;
        }
      }
      catch(DataAccessException ex){
            throw ex;
       }
        Schedule emptyschedule = new Schedule();
        return emptyschedule;
    }

    @Override
    public String DeleteScheduleByDayID(String day, int CourseCode) throws DataAccessException {
       try{ 
        jdbcTemplate.update("DELETE FROM Schedule WHERE Days=? AND CourseCode=?", new Object[]{day,CourseCode});

            return "Course: "+CourseCode+" on "+day+" Deleted";
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public String DeleteScheduleByID(int CourseCode) throws DataAccessException {
       try{ 
        jdbcTemplate.update("DELETE FROM Schedule WHERE CourseCode=?", new Object[]{CourseCode});

            return "Course: "+CourseCode+" Schedule Deleted";
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }
    
}
