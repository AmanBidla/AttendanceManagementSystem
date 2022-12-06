/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.RestController;

import com.project.AttendanceManagementSystem.DAO.InstructorDao;
import com.project.AttendanceManagementSystem.Model.Instructor;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aman Bidla
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api/")
public class InstructorRest {
 
    private final InstructorDao Instructordao;
    
    private Logger logger = Logger.getLogger(InstructorRest.class.toString());
    
    @Autowired
    public InstructorRest(InstructorDao Instructordao) {
        this.Instructordao = Instructordao;
    }
    
    @RequestMapping(value = "/Instructor/InstructorByID/{id}", method = RequestMethod.GET)
    public Instructor FindInstructorByID(@PathVariable long id){
        return Instructordao.FindInstructorByID(id);      
    }
    
    @RequestMapping(value = "/Instructor/InstructorByFirstname/{firstname}", method = RequestMethod.GET)
    public Instructor FindInstructorByFirstName(@PathVariable String firstname){        
        return Instructordao.FindInstructorByFirstName(firstname);
    }
    
    @RequestMapping(value = "/Instructor/InstructorByLastname/{lastname}", method = RequestMethod.GET)
    public Instructor FindInstructorByLastName(@PathVariable String lastname){        
        return Instructordao.FindInstructorByLastName(lastname);
    }
    
    @RequestMapping(value = "/Instructor/InstructorByName/{name}", method = RequestMethod.GET)
    public Instructor FindInstructorByName(@PathVariable String name){
        String[] nameArray = name.split(" ");
        String FirstName = nameArray[0];
        String LastName = nameArray[1];
        return Instructordao.FindInstructorByName(FirstName, LastName);
    }
    
    @RequestMapping(value = "/Instructor/AllInstructors", method = RequestMethod.GET)
    public List<Instructor> ListOfInstructors(){
        return Instructordao.ListOfInstructors();      
    }
    
    @CrossOrigin
    @RequestMapping(value = "/Instructor/UpdateInstructor", method = RequestMethod.PUT)
    public Instructor UpdateInstructor(@RequestBody Instructor instructor){        
        return Instructordao.UpdateInstructor(instructor);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/Instructor/NewInstructor", method = RequestMethod.PUT)
    public Instructor CreateNewInstructor(@RequestBody Instructor instructor){        
        return Instructordao.CreateNewInstructor(instructor);
    }

    @RequestMapping(value = "/Instructor/DeleteInstructor/{id}", method = RequestMethod.DELETE)
    public String DeleteInstructorByID(@PathVariable long id){
        return Instructordao.DeleteInstructorByID(id);
    }    
}
