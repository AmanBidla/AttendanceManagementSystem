/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.RestController;

import com.project.AttendanceManagementSystem.DAO.CourseDao;
import com.project.AttendanceManagementSystem.DAO.StudentDao;
import com.project.AttendanceManagementSystem.Model.Course;
import com.project.AttendanceManagementSystem.Model.Student;
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
public class StudentRest {
    
    private final StudentDao Studentdao;
    
    private Logger logger = Logger.getLogger(CourseRest.class.toString());
    
    @Autowired
    public StudentRest(StudentDao Studentdao) {
        this.Studentdao = Studentdao;
    }
    
    @RequestMapping(value = "/Students/StudentByID/{id}", method = RequestMethod.GET)
    public Student FindStudentByID(@PathVariable long id){
        return Studentdao.FindStudentByID(id);      
    }
    
    @RequestMapping(value = "/Students/StudentByFirstname/{firstname}", method = RequestMethod.GET)
    public Student FindStudentByFirstName(@PathVariable String firstname){        
        return Studentdao.FindStudentByFirstName(firstname);
    }
    
    @RequestMapping(value = "/Students/StudentByLastname/{lastname}", method = RequestMethod.GET)
    public Student FindStudentByLastName(@PathVariable String lastname){        
        return Studentdao.FindStudentByLastName(lastname);
    }
    
    @RequestMapping(value = "/Students/StudentByName/{name}", method = RequestMethod.GET)
    public Student FindStudentByName(@PathVariable String name){ 
        String[] nameArray = name.split(" ");
        String FirstName = nameArray[0];
        String LastName = nameArray[1];
        return Studentdao.FindStudentByName(FirstName, LastName);
    }
    
    @RequestMapping(value = "/Students/AllStudents", method = RequestMethod.GET)
    public List<Student> ListOfStudents(){
        return Studentdao.ListOfStudents();      
    }
    
    @CrossOrigin
    @RequestMapping(value = "/Students/UpdateStudent", method = RequestMethod.PUT)
    public Student UpdateStudent(@RequestBody Student student){        
        return Studentdao.UpdateStudent(student);
    }

    @CrossOrigin
    @RequestMapping(value = "/Students/NewStudent", method = RequestMethod.PUT)
    public Student CreateNewStudent(@RequestBody Student student){        
        return Studentdao.CreateNewStudent(student);
    }

    @RequestMapping(value = "/Students/DeleteStudent/{id}", method = RequestMethod.DELETE)
    public String DeleteStudentByID(@PathVariable long id){
        return Studentdao.DeleteStudentByID(id);
    }    
}
