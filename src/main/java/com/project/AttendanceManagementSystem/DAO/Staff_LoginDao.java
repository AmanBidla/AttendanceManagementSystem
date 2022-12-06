/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.DAO;


import com.project.AttendanceManagementSystem.Model.Staff_Login;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface Staff_LoginDao {
    
    String FindInstructorLogin(Staff_Login StaffLogin) throws DataAccessException;
    String CreateInstructorLogin(Staff_Login StaffLogin) throws DataAccessException;
    String UpdateInstructorLogin(Staff_Login StaffLogin) throws DataAccessException;
    String DeleteInstructorLogin(long InstructorID) throws DataAccessException;       
}
