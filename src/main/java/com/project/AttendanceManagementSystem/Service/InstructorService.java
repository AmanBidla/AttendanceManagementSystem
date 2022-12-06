/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Service;

import com.project.AttendanceManagementSystem.DAO.CourseDao;
import com.project.AttendanceManagementSystem.DAO.InstructorDao;
import com.project.AttendanceManagementSystem.DAO.Staff_CoursesDao;
import com.project.AttendanceManagementSystem.Model.Course;
import com.project.AttendanceManagementSystem.Model.Instructor;
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
public class InstructorService implements InstructorDao{
    
    private Logger logger = Logger.getLogger(InstructorService.class.toString());
    
    private Staff_CoursesDao StaffCoursesdao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public InstructorService(DataSource dataSource, Staff_CoursesDao StaffCoursesdao) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.StaffCoursesdao = StaffCoursesdao;
    }
    
    @Override
    public Instructor FindInstructorByID(long InstructorID) throws DataAccessException {
       try{     
        Instructor instructor = jdbcTemplate.queryForObject("SELECT * FROM Instructor WHERE ID=?",(resultSet, rowNum) -> new Instructor(
                resultSet.getLong("ID"),
                resultSet.getString("LastName"),
                resultSet.getString("FirstName"),
                resultSet.getString("Email")
                ),
                InstructorID);       
        return instructor;
       }
       catch(DataAccessException ex){
           throw ex;
        }
    }

    @Override
    public Instructor FindInstructorByFirstName(String firstname) throws DataAccessException {
       try{     
        Instructor instructor = jdbcTemplate.queryForObject("SELECT * FROM Instructor WHERE FirstName=?",(resultSet, rowNum) -> new Instructor(
                resultSet.getLong("ID"),
                resultSet.getString("LastName"),
                resultSet.getString("FirstName"),
                resultSet.getString("Email")
                ),
                firstname);       
        return instructor;
       }
       catch(DataAccessException ex){
           throw ex;
        }    
    }

    @Override
    public Instructor FindInstructorByLastName(String lastname) throws DataAccessException {
       try{     
        Instructor instructor = jdbcTemplate.queryForObject("SELECT * FROM Instructor WHERE LastName=?",(resultSet, rowNum) -> new Instructor(
                resultSet.getLong("ID"),
                resultSet.getString("LastName"),
                resultSet.getString("FirstName"),
                resultSet.getString("Email")
                ),
                lastname);       
        return instructor;
       }
       catch(DataAccessException ex){
           throw ex;
        }
    }

    @Override
    public Instructor FindInstructorByName(String firstname, String lastname) throws DataAccessException {
       try{     
        Instructor instructor = jdbcTemplate.queryForObject("SELECT * FROM Instructor WHERE FirstName=? AND LastName=?",(resultSet, rowNum) -> new Instructor(
                resultSet.getLong("ID"),
                resultSet.getString("LastName"),
                resultSet.getString("FirstName"),
                resultSet.getString("Email")
                ),
                firstname, lastname);       
        return instructor;
       }
       catch(DataAccessException ex){
           throw ex;
        }}

    @Override
    public List<Instructor> ListOfInstructors() throws DataAccessException {
     try{ 
        List<Instructor> Instructors; 
            Instructors = jdbcTemplate.query("SELECT * FROM Instructor",(resultSet, rowNum) -> new Instructor(
                resultSet.getInt("ID"),
                resultSet.getString("LastName"),
                resultSet.getString("FirstName"),
                resultSet.getString("Email")
                ));
        return Instructors;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public Instructor CreateNewInstructor(Instructor instructor) throws DataAccessException {
       try{ 
        jdbcTemplate.update("INSERT INTO Instructor (ID, LastName, FirstName, Email)"
                              + "VALUES (?, ?, ?, ?)", 
                                new Object[] 
                                {instructor.getID(), instructor.getLastName(), instructor.getFirstName(), instructor.getEmail()});
        instructor = FindInstructorByName(instructor.getFirstName(), instructor.getLastName());
        return instructor;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public Instructor UpdateInstructor(Instructor instructor) throws DataAccessException {
      try{ 
        jdbcTemplate.update("UPDATE Instructor SET ID = ?, LastName = ?, FirstName = ?, Email = ? WHERE ID = ?", 
                                    new Object[] 
                                    {instructor.getID(), instructor.getLastName(), instructor.getFirstName(), instructor.getEmail(), instructor.getID()});
            instructor=FindInstructorByID(instructor.getID());
            return instructor;
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public String DeleteInstructorByID(long InstructorID) throws DataAccessException {
      try{ 
        jdbcTemplate.update("DELETE FROM Instructor WHERE ID=?", new Object[]{InstructorID});
            
        StaffCoursesdao.DeleteStaffCoursesByID(InstructorID);
            return "Instructor ID: "+InstructorID+" Deleted";
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }
    
}
