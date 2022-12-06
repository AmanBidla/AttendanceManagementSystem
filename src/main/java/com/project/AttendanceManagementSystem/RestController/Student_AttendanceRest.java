/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.RestController;

import com.project.AttendanceManagementSystem.DAO.Student_AttendanceDao;
import com.project.AttendanceManagementSystem.Model.Student_Attendance;
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
public class Student_AttendanceRest {
    
    private final Student_AttendanceDao StudentAttendancedao;
    
    private Logger logger = Logger.getLogger(CourseRest.class.toString());
    
    @Autowired
    public Student_AttendanceRest(Student_AttendanceDao StudentAttendancedao) {
        this.StudentAttendancedao = StudentAttendancedao;
    }
    
    @RequestMapping(value = "/Attendance/StudentAttendanceByDate/{id}/{date}", method = RequestMethod.GET)
    public List<Student_Attendance> FindStudentAttendanceByDate(@PathVariable(name = "id") long id, @PathVariable(name = "date") Date date){
        return StudentAttendancedao.FindStudentAttendanceByDate(id, date);      
    }
    
    @RequestMapping(value = "/Attendance/AttendanceByDate/{date}", method = RequestMethod.GET)
    public List<Student_Attendance> FindAllStudentsAttendanceByDate(@PathVariable Date date){        
        return StudentAttendancedao.FindAllStudentsAttendanceByDate(date);
    }
    
    @RequestMapping(value = "/Attendance/AttendanceByCourse/{id}/{code}", method = RequestMethod.GET)
    public List<Student_Attendance> FindAttendanceRecordByCourse(@PathVariable(name = "id") long id,@PathVariable(name = "code") int code){
        return StudentAttendancedao.FindAttendanceRecordByCourse(id, code);      
    }
    
    @RequestMapping(value = "/Attendance/AttendanceByCourseAttendance/{id}/{code}/{attendance}", method = RequestMethod.GET)
    public List<Student_Attendance> FindAttendanceRecordByCourseAttendance(@PathVariable(name = "id") long id, @PathVariable(name = "code") int code, @PathVariable(name = "attendance") String attendance){
        return StudentAttendancedao.FindAttendanceRecordByCourseAttendance(id, code, attendance);      
    }
    
    @RequestMapping(value = "/Attendance/AttendanceByAttendance/{id}/{attendance}", method = RequestMethod.GET)
    public List<Student_Attendance> FindAttendanceRecordByAttendance(@PathVariable(name = "id") long id,@PathVariable(name = "attendance") String attendance){
        return StudentAttendancedao.FindAttendanceRecordByAttendance(id, attendance);      
    }
    
    @RequestMapping(value = "/Attendance/AttendanceByRecord/{id}/{code}/{date}", method = RequestMethod.GET)
    public Student_Attendance FindAttendanceRecord(@PathVariable(name = "id") long id,@PathVariable(name = "code") int code,@PathVariable(name = "date") Date date){
        return StudentAttendancedao.FindAttendanceRecord(id, code, date);      
    }
    
    @RequestMapping(value = "/Attendance/AttendanceByCourseDate/{code}/{date}", method = RequestMethod.GET)
    public List<Student_Attendance> FindAttendanceRecordByCourseDate(@PathVariable(name = "code") int code,@PathVariable(name = "date") Date date){
        return StudentAttendancedao.FindAttendanceRecordByCourseDate(code, date);      
    }
        
    @CrossOrigin
    @RequestMapping(value = "/Attendance/UpdateAttendanceRecord", method = RequestMethod.PUT)
    public List<Student_Attendance> UpdateAttendanceRecord(@RequestBody List<Student_Attendance> studentattendance){        
        return StudentAttendancedao.UpdateAttendanceRecord(studentattendance);
    }
    
    @RequestMapping(value = "/Attendance/DeleteRecordsByDate/{date}", method = RequestMethod.DELETE)
    public String DeleteAttendanceRecordByDate(@PathVariable Date date){
        return StudentAttendancedao.DeleteAttendanceRecordByDate(date);
    }        
}
