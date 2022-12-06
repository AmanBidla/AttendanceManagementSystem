/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.DAO;

import com.project.AttendanceManagementSystem.Model.Course;
import com.project.AttendanceManagementSystem.Model.Instructor;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface InstructorDao {
    
    Instructor FindInstructorByID(long InstructorID) throws DataAccessException;
    Instructor FindInstructorByFirstName(String firstname) throws DataAccessException;
    Instructor FindInstructorByLastName(String lastname) throws DataAccessException;
    Instructor FindInstructorByName(String firstname, String lastname) throws DataAccessException;    
    List<Instructor> ListOfInstructors() throws DataAccessException;
    Instructor CreateNewInstructor(Instructor instrutcor) throws DataAccessException;
    Instructor UpdateInstructor(Instructor instructor) throws DataAccessException;
    String DeleteInstructorByID(long InstructorID) throws DataAccessException;
}
