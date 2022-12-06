/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.DAO;

import com.project.AttendanceManagementSystem.Model.Student;
import com.project.AttendanceManagementSystem.Model.Student_Attendance;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface Student_AttendanceDao {
    
    List<Student_Attendance> FindStudentAttendanceByDate(long StudentID, Date AttendanceDate) throws DataAccessException;
    List<Student_Attendance> FindAllStudentsAttendanceByDate(Date AttendanceDate) throws DataAccessException;
    List<Student_Attendance> FindAttendanceRecordByCourse(long StudentID, int CourseCode) throws DataAccessException;
    List<Student_Attendance> FindAttendanceRecordByCourseAttendance(long StudentID, int CourseCode, String Attendance) throws DataAccessException;
    List<Student_Attendance> FindAttendanceRecordByAttendance(long StudentID, String Attendance) throws DataAccessException;
    List<Student_Attendance> FindAttendanceRecordByCourseDate(int CourseCode, Date AttendanceDate) throws DataAccessException;
    Student_Attendance FindAttendanceRecord(long StudentID, int CourseCode, Date AttendanceDate) throws DataAccessException;  
    List<Student_Attendance> UpdateAttendanceRecord(List<Student_Attendance> studentattendance) throws DataAccessException;
    String DeleteAttendanceRecordByDate(Date AttendanceDate) throws DataAccessException;

}
