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
public class Student_Courses {
    
    private long StudentID;
    private int CourseCode;
    private int TotalAttendance;
    private int TotalLectures;
    
    public Student_Courses(long StudentID, int CourseCode, int TotalAttendance, int TotalLectures){
        this.StudentID=StudentID;
        this.CourseCode=CourseCode;
        this.TotalAttendance=TotalAttendance;
        this.TotalLectures=TotalLectures;
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
     * @return the TotalAttendance
     */
    public int getTotalAttendance() {
        return TotalAttendance;
    }

    /**
     * @param TotalAttendance the TotalAttendance to set
     */
    public void setTotalAttendance(int TotalAttendance) {
        this.TotalAttendance = TotalAttendance;
    }

    /**
     * @return the TotalLectures
     */
    public int getTotalLectures() {
        return TotalLectures;
    }

    /**
     * @param TotalLectures the TotalLectures to set
     */
    public void setTotalLectures(int TotalLectures) {
        this.TotalLectures = TotalLectures;
    }
}
