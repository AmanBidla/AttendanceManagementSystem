/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.DAO;


import com.project.AttendanceManagementSystem.Model.Staff_Courses;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface Staff_CoursesDao {
    
    List<Staff_Courses> FindInstructorCourses(long InstructorID) throws DataAccessException;
    List<Staff_Courses> FindCourseInstructor(int CourseCode) throws DataAccessException;
    List<Staff_Courses> UpdateStaffCourses(Staff_Courses StaffCourses) throws DataAccessException;
    Staff_Courses CreateStaffCourses(Staff_Courses StaffCourses) throws DataAccessException;
    String DeleteStaffCoursesByID(long InstructorID) throws DataAccessException;
    String DeleteStaffCoursesByIDCourse(long InstructorID, int CourseCode) throws DataAccessException;

}
