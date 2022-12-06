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
public class Staff_Login {
    
    private long InstructorID;
    private String Password;
    
    public Staff_Login(long InstructorID, String Password){
        this.InstructorID=InstructorID;
        this.Password=Password;
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
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }
}
