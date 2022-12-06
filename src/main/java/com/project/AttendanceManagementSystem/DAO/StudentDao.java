/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.DAO;

import com.project.AttendanceManagementSystem.Model.Student;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface StudentDao {
    
    Student FindStudentByID(long StudentID) throws DataAccessException;
    Student FindStudentByFirstName(String firstname) throws DataAccessException;
    Student FindStudentByLastName(String lastname) throws DataAccessException;
    Student FindStudentByName(String firstname, String lastname) throws DataAccessException;
    List<Student> ListOfStudents() throws DataAccessException;
    Student CreateNewStudent(Student student) throws DataAccessException;
    Student UpdateStudent(Student student) throws DataAccessException;
    String DeleteStudentByID(long StudentID) throws DataAccessException;

}
