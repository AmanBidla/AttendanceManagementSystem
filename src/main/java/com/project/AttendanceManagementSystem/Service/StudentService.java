/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Service;

import com.project.AttendanceManagementSystem.DAO.StudentDao;
import com.project.AttendanceManagementSystem.Model.Student;
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
public class StudentService implements StudentDao{

    private Logger logger = Logger.getLogger(InstructorService.class.toString());
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public StudentService(DataSource dataSource) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Student FindStudentByID(long StudentID) throws DataAccessException {
        try {
            Student student = jdbcTemplate.queryForObject("SELECT * FROM Student WHERE ID=?", (resultSet, rowNum) -> new Student(
                    resultSet.getLong("ID"),
                    resultSet.getString("LastName"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("Street"),
                    resultSet.getString("City"),
                    resultSet.getString("Province"),
                    resultSet.getString("Postal"),
                    resultSet.getDate("BirthDate"),
                    resultSet.getString("Email")
            ),
            StudentID);
            return student;
        } catch (DataAccessException ex) {
            throw ex;
        } 
    }

    @Override
    public Student FindStudentByFirstName(String firstname) throws DataAccessException {
        try {
            Student student = jdbcTemplate.queryForObject("SELECT * FROM Student WHERE FirstName=?", (resultSet, rowNum) -> new Student(
                    resultSet.getLong("ID"),
                    resultSet.getString("LastName"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("Street"),
                    resultSet.getString("City"),
                    resultSet.getString("Province"),
                    resultSet.getString("Postal"),
                    resultSet.getDate("BirthDate"),
                    resultSet.getString("Email")
            ),
            firstname);
            return student;
        } catch (DataAccessException ex) {
            throw ex;
        } 
    }

    @Override
    public Student FindStudentByLastName(String lastname) throws DataAccessException {
        try {
            Student student = jdbcTemplate.queryForObject("SELECT * FROM Student WHERE LastName=?", (resultSet, rowNum) -> new Student(
                    resultSet.getLong("ID"),
                    resultSet.getString("LastName"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("Street"),
                    resultSet.getString("City"),
                    resultSet.getString("Province"),
                    resultSet.getString("Postal"),
                    resultSet.getDate("BirthDate"),
                    resultSet.getString("Email")
            ),
            lastname);
            return student;
        } catch (DataAccessException ex) {
            throw ex;
        } 
    }

    @Override
    public Student FindStudentByName(String firstname, String lastname) throws DataAccessException {
        try {
            Student student = jdbcTemplate.queryForObject("SELECT * FROM Student WHERE FirstName=? AND LastName=?", (resultSet, rowNum) -> new Student(
                    resultSet.getLong("ID"),
                    resultSet.getString("LastName"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("Street"),
                    resultSet.getString("City"),
                    resultSet.getString("Province"),
                    resultSet.getString("Postal"),
                    resultSet.getDate("BirthDate"),
                    resultSet.getString("Email")
            ),
            firstname, lastname);
            return student;
        } catch (DataAccessException ex) {
            throw ex;
        } 
    }

    @Override
    public List<Student> ListOfStudents() throws DataAccessException {
       try{ 
        List<Student> Students; 
            Students = jdbcTemplate.query("SELECT * FROM Student",(resultSet, rowNum) -> new Student(
                    resultSet.getLong("ID"),
                    resultSet.getString("LastName"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("Street"),
                    resultSet.getString("City"),
                    resultSet.getString("Province"),
                    resultSet.getString("Postal"),
                    resultSet.getDate("BirthDate"),
                    resultSet.getString("Email")
            ));
        return Students;
       }
       catch(DataAccessException ex){
           throw ex;
       }    
    }

    @Override
    public Student CreateNewStudent(Student student) throws DataAccessException {
        try {
            jdbcTemplate.update("INSERT INTO Student (ID, LastName, FirstName, Street, City, Province, Postal, BirthDate, Email)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?)",
                    new Object[]{student.getID(), student.getLastName(), student.getFirstName(), student.getStreet(), student.getCity(), student.getProvince(), student.getPostal(), student.getBirthDate(), student.getEmail()});
            student = FindStudentByID(student.getID());
            return student;
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public Student UpdateStudent(Student student) throws DataAccessException {
        try {
            jdbcTemplate.update("UPDATE Student SET ID = ?, LastName = ?, FirstName = ?, Street = ?, City = ?, Province = ?, Postal = ?, BirthDate = ?, Email = ? WHERE ID = ?",
                    new Object[]{student.getID(), student.getLastName(), student.getFirstName(), student.getStreet(), student.getCity(), student.getProvince(), student.getPostal(), student.getBirthDate(), student.getEmail(), student.getID()});
               student = FindStudentByID(student.getID());
            return student;
        } catch (DataAccessException ex) {
            throw ex;
       }
    }

    @Override
    public String DeleteStudentByID(long StudentID) throws DataAccessException {
        try {
            jdbcTemplate.update("DELETE FROM Student WHERE ID=?", new Object[]{StudentID});

            return "Student ID: " + StudentID + " Deleted";
        } catch (DataAccessException ex) {
            throw ex;
        }
    }
    
}
