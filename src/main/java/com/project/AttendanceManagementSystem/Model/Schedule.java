/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Model;

/**
 *
 * @author Aman Bidla
 */
public class Schedule {
    
    private String Days;
    private int CourseCode;
    
    public Schedule(String Days, int CourseCode){
        this.Days=Days;
        this.CourseCode=CourseCode;
    }

    public Schedule() {
    }

    /**
     * @return the Days
     */
    public String getDays() {
        return Days;
    }

    /**
     * @param Days the Days to set
     */
    public void setDays(String Days) {
        this.Days = Days;
    }

    /**
     * @return the CourseCode
     */
    public int getCourseCode() {
        return CourseCode;
    }

    /**
     * @param CourseCode the CourseCode to set
     */
    public void setCourseCode(int CourseCode) {
        this.CourseCode = CourseCode;
    }
    
}
