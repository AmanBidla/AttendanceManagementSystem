/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.RestController;

import com.project.AttendanceManagementSystem.DAO.ScheduleDao;
import com.project.AttendanceManagementSystem.Model.Schedule;
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
public class ScheduleRest {

    private final ScheduleDao Scheduledao;
    
    private Logger logger = Logger.getLogger(ScheduleRest.class.toString());
    
    @Autowired
    public ScheduleRest(ScheduleDao Scheduledao) {
        this.Scheduledao = Scheduledao;
    }
    
    @RequestMapping(value = "/Schedule/ScheduleByCourseID/{id}", method = RequestMethod.GET)
    public List<Schedule> FindScheduleByCourse(@PathVariable int id){
        return Scheduledao.FindScheduleByCourse(id);      
    }
    
    @RequestMapping(value = "/Schedule/ScheduleByDay/{day}", method = RequestMethod.GET)
    public List<Schedule> FindScheduleByDay(@PathVariable String day){        
        return Scheduledao.FindScheduleByDay(day);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/Schedule/UpdateSchedule", method = RequestMethod.PUT)
    public Schedule UpdateSchedule(@RequestBody Schedule schedule){        
        return Scheduledao.UpdateSchedule(schedule);
    }

    @CrossOrigin
    @RequestMapping(value = "/Schedule/NewSchedule", method = RequestMethod.PUT)
    public Schedule CreateNewSchedule(@RequestBody Schedule schedule){        
        return Scheduledao.CreateNewSchedule(schedule);
    }

    @RequestMapping(value = "/Schedule/DeleteSchedule", method = RequestMethod.POST)
    public String DeleteScheduleByDayID(@RequestBody Schedule schedule){
        return Scheduledao.DeleteScheduleByDayID(schedule.getDays(), schedule.getCourseCode());
    }    
    
    @RequestMapping(value = "/Schedule/DeleteScheduleByCourseID/{CourseCode}", method = RequestMethod.DELETE)
    public String DeleteScheduleByID(@PathVariable int CourseCode){
        return Scheduledao.DeleteScheduleByID(CourseCode);
    }
}
