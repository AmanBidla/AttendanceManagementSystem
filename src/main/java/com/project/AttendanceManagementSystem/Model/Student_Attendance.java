/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.AttendanceManagementSystem.Model;

import java.util.Date;

/**
 *
 * @author Aman Bidla
 */
public class Student_Attendance {
    
    private long StudentID;
    private int CourseCode;
    private Date AttendanceDate;
    private String Attendance;
    
    public Student_Attendance(long StudentID, int CourseCode, Date AttendanceDate, String Attendance){
        this.StudentID=StudentID;
        this.CourseCode=CourseCode;
        this.AttendanceDate=AttendanceDate;
        this.Attendance=Attendance;
     }

    /**
     * @return the StudentID
     */
    public long getStudentID() {
        return StudentID;
    }

    /**
     * @param StudentID the StudentID to set
     */
    public void setStudentID(long StudentID) {
        this.StudentID = StudentID;
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

    /**
     * @return the AttendanceDate
     */
    public Date getAttendanceDate() {
        return AttendanceDate;
    }

    /**
     * @param AttendanceDate the AttendanceDate to set
     */
    public void setAttendanceDate(Date AttendanceDate) {
        this.AttendanceDate = AttendanceDate;
    }

    /**
     * @return the Attendance
     */
    public String getAttendance() {
        return Attendance;
    }

    /**
     * @param Attendance the Attendance to set
     */
    public void setAttendance(String Attendance) {
        this.Attendance = Attendance;
    }
    
}
