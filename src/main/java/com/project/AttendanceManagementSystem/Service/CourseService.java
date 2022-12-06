/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Service;

import com.project.AttendanceManagementSystem.DAO.CourseDao;
import com.project.AttendanceManagementSystem.DAO.ScheduleDao;
import com.project.AttendanceManagementSystem.DAO.Staff_CoursesDao;
import com.project.AttendanceManagementSystem.Model.Course;
import com.project.AttendanceManagementSystem.Model.Schedule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CourseService implements CourseDao {
    
    private Logger logger = Logger.getLogger(CourseService.class.toString());
    
    private ScheduleDao Scheduledao;
    private Staff_CoursesDao StaffCourses;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public CourseService(DataSource dataSource,ScheduleDao Scheduledao, Staff_CoursesDao StaffCourses) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.Scheduledao = Scheduledao;
        this.StaffCourses = StaffCourses;
    }
    
    @Override
    public Course FindCourseByID(int CourseCode) throws DataAccessException {
       try{     
        Course course = jdbcTemplate.queryForObject("SELECT * FROM Course WHERE Code=?",(resultSet, rowNum) -> new Course(
                resultSet.getInt("Code"),
                resultSet.getString("CourseName")
                ),
                CourseCode);       
        return course;
       }
       catch(DataAccessException ex){
           throw ex;
        }
    }

    @Override
    public Course FindCourseByName(String coursename) throws DataAccessException {
       try{ 
        Course course = jdbcTemplate.queryForObject("SELECT * FROM Course WHERE CourseName=?",(resultSet, rowNum) -> new Course(
                resultSet.getInt("Code"),
                resultSet.getString("CourseName")
                ),
                coursename);       
        return course;
       }
       catch(DataAccessException ex){
            throw ex;
       }
    }

    @Override
    public List<Course> ListOfCourses() throws DataAccessException {
        List<Course> Courses;
        try{
            Courses = jdbcTemplate.query("SELECT * FROM Course",(resultSet, rowNum) -> new Course(
                resultSet.getInt("Code"),
                resultSet.getString("CourseName")
                ));
        return Courses;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public Course UpdateCourse(Course course) throws DataAccessException {
       try{ 
        jdbcTemplate.update("UPDATE Course SET CourseName = ? WHERE Code = ?", 
                                    new Object[] 
                                    {course.getCourseName(), course.getCode()});
            course=FindCourseByID(course.getCode());
            return course;
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public Course CreateNewCourse(String coursename) throws DataAccessException {
       try{ 
        jdbcTemplate.update("INSERT INTO Course (CourseName)"
                              + "VALUES (?)", 
                                new Object[] 
                                {coursename});
        Course course = FindCourseByName(coursename);
        return course;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public List<Object> FindCourseSchedule(int CourseCode) throws DataAccessException {
       List<Object> list = new ArrayList();
      try{
       jdbcTemplate.query("SELECT * FROM Course inner join Schedule ON Course.Code=Schedule.CourseCode WHERE CourseCode=?",(resultSet, rowNum) -> {
            Course course= new Course();
            Schedule schedule = new Schedule();
            course.setCode(resultSet.getInt("Code"));
            course.setCourseName(resultSet.getString("CourseName"));
            schedule.setDays(resultSet.getString("Days"));
            schedule.setCourseCode(resultSet.getInt("CourseCode"));
            list.add(course);
            list.add(schedule);;
            return list;
        },
        CourseCode);
        return list;
      }
      catch(DataAccessException ex){
        throw ex;
      }
    }

    @Override
    public String DeleteCourseByID(int CourseCode) throws DataAccessException {
        try {
            jdbcTemplate.update("DELETE FROM Course WHERE Code=?", new Object[]{CourseCode});
            Scheduledao.DeleteScheduleByID(CourseCode);
            StaffCourses.DeleteStaffCoursesByID(CourseCode);
            return "Course Code: " + CourseCode + " Deleted";
        } catch (DataAccessException ex) {
            throw ex;
        }
    }
    
}
