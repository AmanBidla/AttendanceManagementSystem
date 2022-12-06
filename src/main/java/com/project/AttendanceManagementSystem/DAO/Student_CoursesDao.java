/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.DAO;

import com.project.AttendanceManagementSystem.Model.Student_Courses;
import com.project.AttendanceManagementSystem.Model.Student_Attendance;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface Student_CoursesDao {
    
    List<Student_Courses> FindStudentCourses(long StudentID) throws DataAccessException;
    List<Student_Courses> FindStudentsOfCourse(int CourseCode) throws DataAccessException;    
    Student_Courses FindStudentCoursesByComp(long StudentID, int CourseCode) throws DataAccessException;
    Student_Courses CreateStudentCourses(Student_Courses studentsCourse) throws DataAccessException;
    Student_Courses UpdateStudentCourses(long StudentID, int CourseCode) throws DataAccessException;
    List<Student_Courses> UpdateStudentAttendance(List<Student_Attendance> studenattendance) throws DataAccessException;
}
