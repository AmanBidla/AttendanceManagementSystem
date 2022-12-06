/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.DAO;

import com.project.AttendanceManagementSystem.Model.Course;
import com.project.AttendanceManagementSystem.Model.Schedule;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface CourseDao {
    
    Course FindCourseByID(int CourseCode) throws DataAccessException;
    Course FindCourseByName(String coursename) throws DataAccessException;
    List<Course> ListOfCourses() throws DataAccessException;
    Course UpdateCourse(Course course) throws DataAccessException;    
    Course CreateNewCourse(String coursename) throws DataAccessException;
    List<Object> FindCourseSchedule(int CourseCode) throws DataAccessException;
    String DeleteCourseByID(int CourseCode) throws DataAccessException;

}
