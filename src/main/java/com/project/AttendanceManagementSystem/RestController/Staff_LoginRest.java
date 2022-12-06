/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.AttendanceManagementSystem.DAO.Staff_LoginDao;
import com.project.AttendanceManagementSystem.Model.Staff_Login;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Aman Bidla
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api/")
public class Staff_LoginRest {
  
     private final Staff_LoginDao Staff_Logindao;
    
    private Logger logger = Logger.getLogger(Staff_CoursesRest.class.toString());
    
    @Autowired
    public Staff_LoginRest(Staff_LoginDao Staff_Logindao) {
        this.Staff_Logindao = Staff_Logindao;
    }
    
    @CrossOrigin
    @RequestMapping(value = "/StaffLogin/LoginByInstructor", method = RequestMethod.POST)
    public String FindInstructorLogin(@RequestBody Staff_Login StaffLogin){
        return Staff_Logindao.FindInstructorLogin(StaffLogin);      
    }
    
    @CrossOrigin
    @RequestMapping(value = "/StaffLogin/CreateLogin", method = RequestMethod.PUT)
    public String CreateInstructorLogin(@RequestBody Staff_Login StaffLogin){       
        return Staff_Logindao.CreateInstructorLogin(StaffLogin);
    }
        
    @CrossOrigin
    @RequestMapping(value = "/StaffLogin/UpdateLogin", method = RequestMethod.PUT)
    public String UpdateInstructorLogin(@RequestBody Staff_Login StaffLogin){        
        return Staff_Logindao.UpdateInstructorLogin(StaffLogin);
    }
    
    @RequestMapping(value = "/StaffCourses/DeleteLogin/{id}", method = RequestMethod.DELETE)
    public String DeleteInstructorLogin(@PathVariable long id){
        return Staff_Logindao.DeleteInstructorLogin(id);
    }    
}
