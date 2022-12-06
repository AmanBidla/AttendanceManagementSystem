/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Service;

import com.project.AttendanceManagementSystem.DAO.CourseDao;
import com.project.AttendanceManagementSystem.DAO.Staff_CoursesDao;
import com.project.AttendanceManagementSystem.Model.Course;
import com.project.AttendanceManagementSystem.Model.Staff_Courses;
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
public class Staff_CoursesService implements Staff_CoursesDao {

    private Logger logger = Logger.getLogger(ScheduleService.class.toString());
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public Staff_CoursesService(DataSource dataSource) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }    
    
    @Override
    public List<Staff_Courses> FindInstructorCourses(long InstructorID) throws DataAccessException {
       List<Staff_Courses> StaffCourses;  
       try{
        StaffCourses = jdbcTemplate.query("SELECT * FROM Staff_Courses WHERE InstructorID=?", (rs, rowNum) -> new Staff_Courses(
                rs.getLong("InstructorID"),
                rs.getInt("CourseCode")
                ),InstructorID);       
        return StaffCourses;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public List<Staff_Courses> FindCourseInstructor(int CourseCode) throws DataAccessException {
       List<Staff_Courses> StaffCourses;  
       try{
        StaffCourses = jdbcTemplate.query("SELECT * FROM Staff_Courses WHERE CourseCode=?", (rs, rowNum) -> new Staff_Courses(
                rs.getLong("InstructorID"),
                rs.getInt("CourseCode")
                ),CourseCode);       
        return StaffCourses;
       }
       catch(DataAccessException ex){
           throw ex;
       }    
    }
    
    @Override
    public String DeleteStaffCoursesByID(long InstructorID) throws DataAccessException {
       try{ 
        jdbcTemplate.update("DELETE FROM Staff_Courses WHERE InstructorID=?", new Object[]{InstructorID});

            return "Instructor ID: "+InstructorID+" from Staff Details Deleted";
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }
    
     @Override
    public String DeleteStaffCoursesByIDCourse(long InstructorID, int CourseCode) throws DataAccessException {
       try{ 
        jdbcTemplate.update("DELETE FROM Staff_Courses WHERE InstructorID=? AND CourseCode=?", new Object[]{InstructorID, CourseCode});

            return "Instructor ID's: "+InstructorID+" Course: "+CourseCode+" deleted from Staff Details";
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public List<Staff_Courses> UpdateStaffCourses(Staff_Courses StaffCourses) throws DataAccessException {
        List<Staff_Courses> list = new ArrayList();
        try {
            int course=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Course WHERE Code=?", new Object[] {StaffCourses.getCourseCode()}, Integer.class);   
            if(course>0){
            jdbcTemplate.update("INSERT INTO Staff_Courses (InstructorID, CourseCode)"
                    + "VALUES (?, ?)",
                    new Object[]{StaffCourses.getInstructorID(), StaffCourses.getCourseCode()});
            }
            list = FindInstructorCourses(StaffCourses.getInstructorID());
            return list;
           
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public Staff_Courses CreateStaffCourses(Staff_Courses StaffCourses) throws DataAccessException {
        try {
               int course=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Course WHERE Code=?", new Object[] {StaffCourses.getCourseCode()}, Integer.class);   
               if(course>0){
                jdbcTemplate.update("INSERT INTO Staff_Courses (InstructorID, CourseCode)"
                        + "VALUES (?, ?)",
                        new Object[]{StaffCourses.getInstructorID(), StaffCourses.getCourseCode()});
            
                return StaffCourses;
                }
            }
             catch (DataAccessException ex) {
                throw ex;
            }
        Staff_Courses emptyStaffCourses = new Staff_Courses();
        return emptyStaffCourses;
    }
    
}
