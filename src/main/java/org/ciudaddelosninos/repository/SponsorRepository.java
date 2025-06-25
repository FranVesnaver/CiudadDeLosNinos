package org.ciudaddelosninos.repository;

import java.sql.Date;

public class SponsorRepository {
    String dni;
    String address;
    String firstName;
    String lastName;
    String email;
    String facebook;
    String landline;    //telefono fijo
    String mobile;      //telefono celular
    java.sql.Date birthDate;
    String occupation;
    String cuilCuit;

    public SponsorRepository(){}

    public SponsorRepository(String dni, String address, String firstName, String lastName, String email, String facebook, String landline, String mobile, Date birthDate, String occupation, String cuilCuit) {
        this.dni = dni;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.facebook = facebook;
        this.landline = landline;
        this.mobile = mobile;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.cuilCuit = cuilCuit;
    }

    public String getDni() {return dni;}
    public String getAddress() {return address;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getFacebook() {return facebook;}
    public String getLandline() {return landline;}
    public String getMobile() {return mobile;}
    public Date getBirthDate() {return birthDate;}
    public String getOccupation() {return occupation;}
    public String getCuilCuit() {return cuilCuit;}

    public void setDni(String dni) {this.dni = dni;}
    public void setAddress(String address) {this.address = address;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setFacebook(String facebook) {this.facebook = facebook;}
    public void setLandline(String landline) {this.landline = landline;}
    public void setMobile(String mobile) {this.mobile = mobile;}
    public void setBirthDate(Date birthDate) {this.birthDate = birthDate;}
    public void setOccupation(String occupation) {this.occupation = occupation;}
    public void setCuilCuit(String cuilCuit) {this.cuilCuit = cuilCuit;}
}
