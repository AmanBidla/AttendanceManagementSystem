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
public class Course {
    
    private int Code;
    private String CourseName;
    
    public Course(int Code, String CourseName){
        this.Code=Code;
        this.CourseName=CourseName;
    }

    public Course() {
    
    }

    /**
     * @return the Code
     */
    public int getCode() {
        return Code;
    }

    /**
     * @param Code the Code to set
     */
    public void setCode(int Code) {
        this.Code = Code;
    }

    /**
     * @return the CourseName
     */
    public String getCourseName() {
        return CourseName;
    }

    /**
     * @param CourseName the CourseName to set
     */
    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }
}
