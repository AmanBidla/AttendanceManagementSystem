/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.RestController;

import com.project.AttendanceManagementSystem.DAO.Student_AttendanceDao;
import com.project.AttendanceManagementSystem.DAO.Student_CoursesDao;
import com.project.AttendanceManagementSystem.Model.Student_Attendance;
import com.project.AttendanceManagementSystem.Model.Student_Courses;
import java.util.Date;
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
public class Student_CoursesRest {
    
    private final Student_CoursesDao StudentCoursesDao;
    
    private Logger logger = Logger.getLogger(CourseRest.class.toString());
    
    @Autowired
    public Student_CoursesRest(Student_CoursesDao StudentCoursesDao) {
        this.StudentCoursesDao = StudentCoursesDao;
    }
    
    @RequestMapping(value = "/StudentCourses/CoursesByStudent/{id}", method = RequestMethod.GET)
    public List<Student_Courses> FindStudentCourses(@PathVariable long id){
        return StudentCoursesDao.FindStudentCourses(id);      
    }
    
    @RequestMapping(value = "/StudentCourses/StudentByCourse/{code}", method = RequestMethod.GET)
    public List<Student_Courses> FindStudentsOfCourse(@PathVariable int code){        
        return StudentCoursesDao.FindStudentsOfCourse(code);
    }
    
    @RequestMapping(value = "/StudentCourses/StudentCourse/{id}/{code}", method = RequestMethod.GET)
    public Student_Courses FindStudentCoursesByComp(@PathVariable(name = "id") long id,@PathVariable(name = "code") int code){
        return StudentCoursesDao.FindStudentCoursesByComp(id, code);      
    }
        
    @CrossOrigin
    @RequestMapping(value = "/StudentCourses/CreateStudentCourse", method = RequestMethod.PUT)
    public Student_Courses CreateStudentCourses(@RequestBody Student_Courses studentsCourse){        
        return StudentCoursesDao.CreateStudentCourses(studentsCourse);
    }
      
        
}
