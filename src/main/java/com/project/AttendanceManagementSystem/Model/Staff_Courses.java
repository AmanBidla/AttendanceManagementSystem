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
public class Staff_Courses {
    
    private long InstructorID;
    private int CourseCode;
    
    public Staff_Courses(long InstructorID, int CourseCode){
        this.InstructorID=InstructorID;
        this.CourseCode=CourseCode;
    }

    public Staff_Courses() {
    
    }

    /**
     * @return the InstructorID
     */
    public long getInstructorID() {
        return InstructorID;
    }

    /**
     * @param InstructorID the InstructorID to set
     */
    public void setInstructorID(long InstructorID) {
        this.InstructorID = InstructorID;
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
