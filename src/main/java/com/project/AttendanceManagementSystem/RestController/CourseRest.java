/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.RestController;

import com.project.AttendanceManagementSystem.Model.Course;
import com.project.AttendanceManagementSystem.DAO.CourseDao;
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
public class CourseRest {
    
    private final CourseDao Coursedao;
    
    private Logger logger = Logger.getLogger(CourseRest.class.toString());
    
    @Autowired
    public CourseRest(CourseDao Coursedao) {
        this.Coursedao = Coursedao;
    }
    
    @RequestMapping(value = "/Courses/CourseByID/{id}", method = RequestMethod.GET)
    public Course FindCourserByID(@PathVariable int id){
        return Coursedao.FindCourseByID(id);      
    }
    
    @RequestMapping(value = "/Courses/CourseByName/{coursename}", method = RequestMethod.GET)
    public Course FindCourseByName(@PathVariable String coursename){        
        return Coursedao.FindCourseByName(coursename);
    }
    
    @RequestMapping(value = "/Courses/AllCourses", method = RequestMethod.GET)
    public List<Course> ListOfCourses(){
        return Coursedao.ListOfCourses();      
    }
    
    @CrossOrigin
    @RequestMapping(value = "/Courses/UpdateCourse", method = RequestMethod.PUT)
    public Course UpdateCourse(@RequestBody Course course){        
        return Coursedao.UpdateCourse(course);
    }

    @RequestMapping(value = "/Courses/NewCourse/{coursename}", method = RequestMethod.GET)
    public Course CreateNewCourse(@PathVariable String coursename){        
        return Coursedao.CreateNewCourse(coursename);
    }

    @RequestMapping(value = "/Courses/CourseSchedule/{CourseCode}", method = RequestMethod.GET)
    public List<Object> FindCourseSchedule(@PathVariable int CourseCode){        
        return Coursedao.FindCourseSchedule(CourseCode);
    }
    
    @RequestMapping(value = "/Courses/DeleteCourse/{CourseCode}", method = RequestMethod.DELETE)
    public String DeleteCourseByID(@PathVariable int CourseCode){
        return Coursedao.DeleteCourseByID(CourseCode);
    }    
}
