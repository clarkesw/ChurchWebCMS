/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author clarke
 */
@Entity
@Table(name = "STAFF")
public class Staff {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String firstName;
    private String lastName;
    private String fullName;
    private String position;
    private String homeEmail;
    private String workEmail;
    private String homePhone;
    private String workPhone;
    private String photo;
    private String bio;
    
    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")    
    private User user;
    
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Address.class, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Address homeAddress;

    public Staff(){}
    
    public Staff(String firstName, String lastName, String position, String homeEmail, String workEmail,
            String homePhone, String workPhone, Address homeAddress) {
        setFirstName(firstName);
        setLastName(lastName);
        setConFullName();
        this.position = position;
        this.homeEmail = homeEmail;
        this.workEmail = workEmail;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.homeAddress = homeAddress;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public final void setConFullName(){
        this.fullName = this.lastName + ", " + this.firstName;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }

    public String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHomeEmail() {
        return homeEmail;
    }

    public void setHomeEmail(String homeEmail) {
        this.homeEmail = homeEmail;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", fullName=" + fullName + ", position=" + position + ", homeEmail=" + homeEmail + ", workEmail=" + workEmail + ", homePhone=" + homePhone + ", workPhone=" + workPhone + ", photo=" + photo + ", bio=" + bio + ", user=" + user + ", homeAddress=" + homeAddress + '}';
    }

}
