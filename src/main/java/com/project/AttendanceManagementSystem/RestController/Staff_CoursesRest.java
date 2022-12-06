/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.RestController;

import com.project.AttendanceManagementSystem.DAO.Staff_CoursesDao;
import com.project.AttendanceManagementSystem.Model.Staff_Courses;
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
public class Staff_CoursesRest {
    
     private final Staff_CoursesDao Staff_Coursesdao;
    
    private Logger logger = Logger.getLogger(Staff_CoursesRest.class.toString());
    
    @Autowired
    public Staff_CoursesRest(Staff_CoursesDao Staff_Coursesdao) {
        this.Staff_Coursesdao = Staff_Coursesdao;
    }
    
    @RequestMapping(value = "/StaffCourses/CourseByInstructor/{id}", method = RequestMethod.GET)
    public List<Staff_Courses> FindInstructorCourses(@PathVariable long id){
        return Staff_Coursesdao.FindInstructorCourses(id);      
    }
    
    @RequestMapping(value = "/StaffCourses/InstructorByCourse/{code}", method = RequestMethod.GET)
    public List<Staff_Courses> FindCourseInstructor(@PathVariable int code){       
        return Staff_Coursesdao.FindCourseInstructor(code);
    }
        
    @CrossOrigin
    @RequestMapping(value = "/StaffCourses/UpdateStaffCourse", method = RequestMethod.PUT)
    public List<Staff_Courses> UpdateStaffCourses(@RequestBody Staff_Courses StaffCourses){        
        return Staff_Coursesdao.UpdateStaffCourses(StaffCourses);
    }

    @CrossOrigin
    @RequestMapping(value = "/StaffCourses/CreateStaffCourse", method = RequestMethod.PUT)
    public Staff_Courses CreateStaffCourses(@RequestBody Staff_Courses StaffCourses){        
        return Staff_Coursesdao.CreateStaffCourses(StaffCourses);
    }
    
    @RequestMapping(value = "/StaffCourses/DeleteInstructorsCourse/{id}", method = RequestMethod.DELETE)
    public String DeleteStaffCoursesByID(@PathVariable long id){
        return Staff_Coursesdao.DeleteStaffCoursesByID(id);
    }
    
    @RequestMapping(value = "/StaffCourses/DeleteCourseInstructor/{id}/{code}", method = RequestMethod.DELETE)
    public String DeleteStaffCoursesByIDCourse(@PathVariable(name="id") long id, @PathVariable(name="code") int code){
        return Staff_Coursesdao.DeleteStaffCoursesByIDCourse(id, code);
    }
    
}
