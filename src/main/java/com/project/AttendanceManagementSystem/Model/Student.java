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
public class Student {
    
    private long ID;
    private String LastName;
    private String FirstName;
    private String Street;
    private String City;
    private String Province;
    private String Postal;
    private Date BirthDate;
    private String Email;
    
    public Student(long ID, String LastName, String FirstName, String Street, String City, String Province, String Postal, Date BirthDate, String Email){
        this.ID=ID;
        this.LastName=LastName;
        this.FirstName=FirstName;
        this.Street=Street;
        this.City=City;
        this.Province=Province;
        this.Postal=Postal;
        this.BirthDate=BirthDate;
        this.Email=Email;
    }

    /**
     * @return the ID
     */
    public long getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return the Street
     */
    public String getStreet() {
        return Street;
    }

    /**
     * @param Street the Street to set
     */
    public void setStreet(String Street) {
        this.Street = Street;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * @return the Province
     */
    public String getProvince() {
        return Province;
    }

    /**
     * @param Province the Province to set
     */
    public void setProvince(String Province) {
        this.Province = Province;
    }

    /**
     * @return the Postal
     */
    public String getPostal() {
        return Postal;
    }

    /**
     * @param Postal the Postal to set
     */
    public void setPostal(String Postal) {
        this.Postal = Postal;
    }

    /**
     * @return the BirthDate
     */
    public Date getBirthDate() {
        return BirthDate;
    }

    /**
     * @param BirthDate the BirthDate to set
     */
    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
}
