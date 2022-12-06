/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Service;

import com.project.AttendanceManagementSystem.DAO.Student_AttendanceDao;
import com.project.AttendanceManagementSystem.DAO.Student_CoursesDao;
import com.project.AttendanceManagementSystem.Model.Student_Attendance;
import com.project.AttendanceManagementSystem.Model.Student_Courses;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aman Bidla
 */
@Service
public class Student_AttendanceService implements Student_AttendanceDao{

    private Logger logger = Logger.getLogger(InstructorService.class.toString());
    
    private Student_CoursesDao StudentCoursesdao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public Student_AttendanceService(DataSource dataSource, Student_CoursesDao StudentCoursesdao ) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.StudentCoursesdao=StudentCoursesdao;
    }

    @Override
    public List<Student_Attendance> FindStudentAttendanceByDate(long StudentID, Date AttendanceDate) throws DataAccessException {
        try {
            List<Student_Attendance> StudentsAttendance; 
            StudentsAttendance = jdbcTemplate.query("SELECT * FROM Student_Attendance WHERE StudentID=? AND AttendanceDate=?", (resultSet, rowNum) -> new Student_Attendance(
                    resultSet.getLong("StudentID"),
                    resultSet.getInt("CourseCode"),
                    resultSet.getDate("AttendanceDate"),
                    resultSet.getString("Attendance")
            ),
            StudentID, AttendanceDate);
            return StudentsAttendance;
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public List<Student_Attendance> FindAllStudentsAttendanceByDate(Date AttendanceDate) throws DataAccessException {
       try{ 
        List<Student_Attendance> StudentsAttendance; 
            StudentsAttendance = jdbcTemplate.query("SELECT * FROM Student_Attendance WHERE AttendanceDate=?",(resultSet, rowNum) -> new Student_Attendance(
                    resultSet.getLong("StudentID"),
                    resultSet.getInt("CourseCode"),
                    resultSet.getDate("AttendanceDate"),
                    resultSet.getString("Attendance")
                  ), AttendanceDate);
        return StudentsAttendance;
       }
       catch(DataAccessException ex){
           throw ex;
       }    
    }

    @Override
    public List<Student_Attendance> FindAttendanceRecordByCourse(long StudentID, int CourseCode) throws DataAccessException {
       try{ 
        List<Student_Attendance> StudentsAttendance; 
            StudentsAttendance = jdbcTemplate.query("SELECT * FROM Student_Attendance WHERE StudentID=? AND CourseCode=?",(resultSet, rowNum) -> new Student_Attendance(
                    resultSet.getLong("StudentID"),
                    resultSet.getInt("CourseCode"),
                    resultSet.getDate("AttendanceDate"),
                    resultSet.getString("Attendance")
                  ), StudentID, CourseCode);
        return StudentsAttendance;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public List<Student_Attendance> FindAttendanceRecordByCourseAttendance(long StudentID, int CourseCode, String Attendance) throws DataAccessException {
       try{ 
        List<Student_Attendance> StudentsAttendance; 
            StudentsAttendance = jdbcTemplate.query("SELECT * FROM Student_Attendance WHERE StudentID=? AND CourseCode=? AND Attendance=?",(resultSet, rowNum) -> new Student_Attendance(
                    resultSet.getLong("StudentID"),
                    resultSet.getInt("CourseCode"),
                    resultSet.getDate("AttendanceDate"),
                    resultSet.getString("Attendance")
                  ), StudentID, CourseCode, Attendance);
        return StudentsAttendance;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public List<Student_Attendance> FindAttendanceRecordByAttendance(long StudentID, String Attendance) throws DataAccessException {
       try{ 
        List<Student_Attendance> StudentsAttendance; 
            StudentsAttendance = jdbcTemplate.query("SELECT * FROM Student_Attendance WHERE StudentID=? AND Attendance=?",(resultSet, rowNum) -> new Student_Attendance(
                    resultSet.getLong("StudentID"),
                    resultSet.getInt("CourseCode"),
                    resultSet.getDate("AttendanceDate"),
                    resultSet.getString("Attendance")
                  ), StudentID, Attendance);
        return StudentsAttendance;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }
    
    @Override
    public Student_Attendance FindAttendanceRecord(long StudentID, int CourseCode, Date AttendanceDate) throws DataAccessException {
       try{ 
        Student_Attendance StudentsAttendance; 
            StudentsAttendance = jdbcTemplate.queryForObject("SELECT * FROM Student_Attendance WHERE StudentID=? AND CourseCode=? AND AttendanceDate=?",(resultSet, rowNum) -> new Student_Attendance(
                    resultSet.getLong("StudentID"),
                    resultSet.getInt("CourseCode"),
                    resultSet.getDate("AttendanceDate"),
                    resultSet.getString("Attendance")
                  ), StudentID, CourseCode, AttendanceDate);
        return StudentsAttendance;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }
    
    @Override
    public List<Student_Attendance> FindAttendanceRecordByCourseDate(int CourseCode, Date AttendanceDate){

        List<Student_Attendance> StudentsAttendance;
        try{        
            StudentsAttendance = jdbcTemplate.query("SELECT * FROM Student_Attendance WHERE CourseCode=? AND AttendanceDate=?",(resultSet, rowNum) -> new Student_Attendance(
                    resultSet.getLong("StudentID"),
                    resultSet.getInt("CourseCode"),
                    resultSet.getDate("AttendanceDate"),
                    resultSet.getString("Attendance")
                  ), CourseCode, AttendanceDate);

           return StudentsAttendance;
       }
       catch(DataAccessException ex){
           throw ex;
       }      
    }
    
    @Override
    public List<Student_Attendance> UpdateAttendanceRecord(List<Student_Attendance> studentattendance) throws DataAccessException {
       
       List<Student_Attendance> ExistingList = FindAttendanceRecordByCourseDate(studentattendance.get(0).getCourseCode(), new java.sql.Date(studentattendance.get(0).getAttendanceDate().getTime()));
       if(ExistingList.size()==0){
        try {
            jdbcTemplate.batchUpdate(
			"INSERT INTO Student_Attendance (StudentID, CourseCode, AttendanceDate, Attendance) VALUES (?, ?, ?, ?)",
			new BatchPreparedStatementSetter() {
                                @Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setLong(1, studentattendance.get(i).getStudentID());
					ps.setInt(2, studentattendance.get(i).getCourseCode());
                                        ps.setDate(3, new java.sql.Date(studentattendance.get(i).getAttendanceDate().getTime()));
                                        ps.setString(4, studentattendance.get(i).getAttendance());
				}
                                @Override
				public int getBatchSize() {
					return studentattendance.size();
				}
			});
            StudentCoursesdao.UpdateStudentAttendance(studentattendance);
            List<Student_Attendance> recentupdate = FindAttendanceRecordByCourseDate(studentattendance.get(0).getCourseCode(), new java.sql.Date(studentattendance.get(0).getAttendanceDate().getTime()));
            return recentupdate;
        } catch (DataAccessException ex) {
            throw ex;
        }
       }
        else{
            return FindAttendanceRecordByCourseDate(studentattendance.get(0).getCourseCode(), new java.sql.Date(studentattendance.get(0).getAttendanceDate().getTime()));
         }
    }
    
    @Override
    public String DeleteAttendanceRecordByDate(Date AttendanceDate){
        try {
            jdbcTemplate.update("DELETE FROM Student_Attendance WHERE AttendanceDate=?", new Object[]{AttendanceDate});
            return " Deleted Attendance Record of Date : " + AttendanceDate;
        } catch (DataAccessException ex) {
            throw ex;
        }    
    }
    
}
