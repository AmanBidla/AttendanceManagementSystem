/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Service;

import com.project.AttendanceManagementSystem.DAO.Staff_LoginDao;
import com.project.AttendanceManagementSystem.Model.Staff_Login;
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
public class Staff_LoginService implements Staff_LoginDao {
    
    public static boolean Authentication = false;
    
    private Logger logger = Logger.getLogger(ScheduleService.class.toString());
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public Staff_LoginService(DataSource dataSource) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }    
    
    @Override
    public String FindInstructorLogin(Staff_Login StaffLogin) throws DataAccessException {
        String inputPassword=StaffLogin.getPassword();
        long inputUser=StaffLogin.getInstructorID();
        try {
           StaffLogin = jdbcTemplate.queryForObject("SELECT * FROM Staff_Login WHERE InstructorID=?", (resultSet, rowNum) -> new Staff_Login(
                    resultSet.getLong("InstructorId"),
                    resultSet.getString("Password")
            ),
            StaffLogin.getInstructorID());
            
           if(inputUser==StaffLogin.getInstructorID()){
                if(inputPassword.equals(StaffLogin.getPassword())){
                    Authentication=true;
                    return "Authentication Success";
                }
                else if(StaffLogin.getPassword()==null){
                    return "Create";
                }
                else if(StaffLogin.getPassword()!=inputPassword){
                    return "Authentication Failed";
                }
                else{
                    return "Authentication Operation Failed";
                }
            }
           else if(StaffLogin.getInstructorID()!=inputUser){
               return "Instructor ID: "+StaffLogin.getInstructorID()+" does'nt exist";
           }
           else{
              return "Error fetching Instructor Login Details";
           }
        } catch (DataAccessException ex) {
            return "Failed";
        }
    }

    @Override
    public String CreateInstructorLogin(Staff_Login StaffLogin) throws DataAccessException {
        long inputuser = StaffLogin.getInstructorID();
        try {
            String InstructorLogin = FindInstructorLogin(StaffLogin);
            if(InstructorLogin=="Failed"){
                return "Login cannot be created for "+inputuser+" Please enter valid ID";
            }
            else if(InstructorLogin == "Create"){
                    UpdateInstructorLogin(StaffLogin);
               /* jdbcTemplate.update("INSERT INTO Staff_Login (InstructorID, Password)"
                        + "VALUES (?, ?)",
                        new Object[]{StaffLogin.getInstructorID(), StaffLogin.getPassword()});*/
            
                String login= FindInstructorLogin(StaffLogin);
                if("Authentication Success".equals(login) || "Authentication Failed".equals(login)){
                    return "Login for Instructor ID: "+StaffLogin.getInstructorID()+" Created";
                }
                else{
                    return "Error Creating Instructor Login Details";
                }
            }

        } catch (DataAccessException ex) {
            throw ex;
        }
        return "Instructor Login already Exists";
    }

    @Override
    public String UpdateInstructorLogin(Staff_Login StaffLogin) throws DataAccessException {
       try{ 
        jdbcTemplate.update("UPDATE Staff_Login SET Password = ? WHERE InstructorID = ?",
                                    new Object[] 
                                    {StaffLogin.getPassword(),StaffLogin.getInstructorID()});
            String login=FindInstructorLogin(StaffLogin);
            if("Authentication Success".equals(login) || "Authentication Failed".equals(login)){
                return "Login for Instructor ID: "+StaffLogin.getInstructorID()+" Updated";
            }
            else if("Instructor ID: "+StaffLogin.getInstructorID()+" does'nt exist"==login){
                return "Please enter valid ID";
            }
            else{
                return "Error Updating Instructor Login Details";
            }
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public String DeleteInstructorLogin(long InstructorID) throws DataAccessException {
       try{ 
        jdbcTemplate.update("DELETE FROM Staff_Login WHERE InstructorID=?", new Object[]{InstructorID});

            return "Login Details for Instructor: "+InstructorID+" Deleted";
       }
       catch(DataAccessException ex){
            throw ex;
        }
    }
    
}
