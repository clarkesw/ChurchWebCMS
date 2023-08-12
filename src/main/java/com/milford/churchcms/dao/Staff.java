/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.dao;

import com.milford.churchcms.AppConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Staff implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;
    @Column(columnDefinition="TEXT")
    private String bio;
    
    private String namePreffix;
    private String nameSuffix;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String position;
    private String homeEmail;
    private String workEmail;
    private String homePhone;    
    private String workPhone;
    private String mobilePhone;
    private String mobileCarrier;
    private String prefferedContact;
    private boolean recieveChurchUpdates = false;
    private boolean recievePrayerRequests = false;
    private boolean isAdmin = false;
    private String photo;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = User.class, orphanRemoval = true)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")    
    private User user;
    
    @OneToOne(cascade=CascadeType.ALL, targetEntity = Address.class, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "id")
    private Address homeAddress;

    public Staff(){}
    
    public Staff(Staff staffer){
        setFirstName(staffer.firstName);
        setLastName(staffer.lastName);
        setConFullName();
        this.position = staffer.position;
        this.homeEmail = staffer.homeEmail;
        this.workEmail = staffer.workEmail;
        this.homePhone = staffer.homePhone;
        this.workPhone = staffer.workPhone;
        this.homeAddress = staffer.homeAddress;
        this.mobilePhone = staffer.mobilePhone;
        this.mobileCarrier = staffer.mobileCarrier;
        this.recieveChurchUpdates = staffer.recieveChurchUpdates;
        this.recievePrayerRequests = staffer.recievePrayerRequests;
    }
    
    public Staff(String firstName, String lastName, String position, String homeEmail, String workEmail,
            String homePhone, String workPhone, Address homeAddress, String mobilePhone, String mobileCarrier,
            boolean recieveChurchUpdates, boolean recievePrayerRequests) {
        setFirstName(firstName);
        setLastName(lastName);
        setConFullName();
        this.position = position;
        this.homeEmail = homeEmail;
        this.workEmail = workEmail;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.homeAddress = homeAddress;
        this.mobilePhone = mobilePhone;
        this.mobileCarrier = mobileCarrier;
        this.recieveChurchUpdates = recieveChurchUpdates;
        this.recievePrayerRequests = recievePrayerRequests;
    }

    public String getPrefferedContact() {
        return prefferedContact;
    }

    public void setPrefferedContact(String prefferedContact) {
        this.prefferedContact = prefferedContact;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNamePreffix() {
        return namePreffix;
    }

    public void setNamePreffix(String namePreffix) {
        this.namePreffix = namePreffix;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobileCarrier() {
        return mobileCarrier;
    }

    public void setMobileCarrier(String mobileCarrier) {
        Map carriers = new HashMap(AppConstants.textMessageAddress);
        this.mobileCarrier = (String)carriers.get(mobileCarrier);
    }

    public boolean isRecieveChurchUpdates() {
        return recieveChurchUpdates;
    }

    public void setRecieveChurchUpdates(boolean recieveChurchUpdates) {
        this.recieveChurchUpdates = recieveChurchUpdates;
    }

    public boolean isRecievePrayerRequests() {
        return recievePrayerRequests;
    }

    public void setRecievePrayerRequests(boolean recievePrayerRequests) {
        this.recievePrayerRequests = recievePrayerRequests;
    }
    
    

    public String getBio() {
        return bio;
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
        this.fullName = this.firstName + " " + this.lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
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
        return "Staff{" + "id=" + id + ", namePreffix=" + namePreffix + ", nameSuffix=" + nameSuffix + ", firstName=" + 
                firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", fullName=" + fullName + ", position=" + 
                position + ", homeEmail=" + homeEmail + ", workEmail=" + workEmail + ", homePhone=" + homePhone + ", workPhone=" + 
                workPhone + ", mobilePhone=" + mobilePhone + ", mobileCarrier=" + mobileCarrier + ", recieveChurchUpdates=" + 
                recieveChurchUpdates + ", recievePrayerRequests=" + recievePrayerRequests + ", isAdmin=" + isAdmin + ", photo=" + 
                photo + ", user=" + user + ", homeAddress=" + homeAddress + '}';
    }
}
