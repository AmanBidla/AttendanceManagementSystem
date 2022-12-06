/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Service;

import com.project.AttendanceManagementSystem.DAO.Student_CoursesDao;
import com.project.AttendanceManagementSystem.Model.Student_Courses;
import com.project.AttendanceManagementSystem.Model.Student_Attendance;
import java.util.ArrayList;
import java.util.Date;
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
public class Student_CoursesService implements Student_CoursesDao{

    private Logger logger = Logger.getLogger(CourseService.class.toString());
        
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public Student_CoursesService(DataSource dataSource) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Student_Courses> FindStudentCourses(long StudentID) throws DataAccessException {
        List<Student_Courses> StudentCourses; 
        try{                    
            StudentCourses = jdbcTemplate.query("SELECT * FROM Student_Courses WHERE StudentID = ?",(resultSet, rowNum) -> new Student_Courses(                
                resultSet.getLong("StudentID"),
                resultSet.getInt("CourseCode"),
                resultSet.getInt("TotalAttendance"),
                resultSet.getInt("TotalLectures")
                ),
                StudentID);       
        return StudentCourses;
       }
       catch(DataAccessException ex){
           throw ex;
        }
    }

    @Override
    public List<Student_Courses> FindStudentsOfCourse(int CourseCode) throws DataAccessException {
        List<Student_Courses> StudentCourses;
        try {
            StudentCourses = jdbcTemplate.query("SELECT * FROM Student_Courses WHERE CourseCode=?", (resultSet, rowNum) -> new Student_Courses(
                resultSet.getLong("StudentID"),
                resultSet.getInt("CourseCode"),
                resultSet.getInt("TotalAttendance"),
                resultSet.getInt("TotalLectures")
                ),
                CourseCode);       
            return StudentCourses;
        } catch (DataAccessException ex) {
            throw ex;
        }
    }
    
    @Override
    public Student_Courses FindStudentCoursesByComp(long StudentID, int CourseCode) throws DataAccessException {
        try {
           Student_Courses StudentCourse = jdbcTemplate.queryForObject("SELECT * FROM Student_Courses WHERE StudentID=? AND CourseCode=?", (resultSet, rowNum) -> new Student_Courses(
                resultSet.getLong("StudentID"),
                resultSet.getInt("CourseCode"),
                resultSet.getInt("TotalAttendance"),
                resultSet.getInt("TotalLectures")
                ),
                StudentID, CourseCode);       
            return StudentCourse;
        } 
        catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public Student_Courses CreateStudentCourses(Student_Courses studentsCourse) throws DataAccessException {
       try{ 
        jdbcTemplate.update("INSERT INTO Student_Courses (StudentID, CourseCode, TotalAttendance, TotalLectures) VALUES (?,?,?,?)", 
                                new Object[] 
                               {studentsCourse.getStudentID(), studentsCourse.getCourseCode(), studentsCourse.getTotalAttendance(), studentsCourse.getTotalLectures()});
        studentsCourse = FindStudentCoursesByComp(studentsCourse.getStudentID(), studentsCourse.getCourseCode() );
        return studentsCourse;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public Student_Courses UpdateStudentCourses(long StudentID, int CourseCode) throws DataAccessException {
       try{
        
        int TotalAttendance=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Student_Attendance WHERE StudentID=? AND CourseCode=? AND Attendance=?", new Object[] {StudentID, CourseCode, "Y"}, Integer.class);   
        int TotalLectures=jdbcTemplate.queryForObject("SELECT COUNT(DISTINCT AttendanceDate) FROM Student_Attendance WHERE CourseCode=?", new Object[] {CourseCode}, Integer.class);   
        
        jdbcTemplate.update("UPDATE Student_Courses SET TotalAttendance = ?, TotalLectures = ? WHERE StudentID = ? AND CourseCode = ?",
                                    new Object[] 
                                    {TotalAttendance, TotalLectures, StudentID, CourseCode});
        
            Student_Courses studentcourses=FindStudentCoursesByComp(StudentID, CourseCode);
            return studentcourses;
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public List<Student_Courses> UpdateStudentAttendance(List<Student_Attendance>studentattendance) throws DataAccessException {
        List<Student_Courses> UpdatedStudentCourses = new ArrayList();
        try{
            Student_Courses StudentCourses;
            for(int i=0; i<studentattendance.size(); i++){
             StudentCourses = UpdateStudentCourses(studentattendance.get(i).getStudentID(), studentattendance.get(i).getCourseCode());            
             UpdatedStudentCourses.add(StudentCourses);
            }
            for(int j=0; j<UpdatedStudentCourses.size(); j++){
                String test = UpdatedStudentCourses.get(j).getCourseCode()+" "+UpdatedStudentCourses.get(j).getStudentID()+" "+UpdatedStudentCourses.get(j).getTotalAttendance()+" "+UpdatedStudentCourses.get(j).getTotalLectures();
                logger.info(test);
            }
            return UpdatedStudentCourses;
        }
        catch(DataAccessException ex){
            throw ex;
        }    
    }
    
}
