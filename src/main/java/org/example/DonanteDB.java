package org.example;

import java.sql.Date;

public class DonanteDB {
    String dniDonante;
    String direccion;
    String nombre;
    String apellido;
    String email;
    String facebook;
    String telFijo;
    String telCelular;
    java.sql.Date fechaNac;
    String ocupacion;
    String cuilCuit;

    public DonanteDB(){}

    public DonanteDB(String dniDonante, String direccion, String nombre, String apellido, String email, String facebook, String telFijo, String telCelular, Date fechaNac, String ocupacion, String cuilCuit) {
        this.dniDonante = dniDonante;
        this.direccion = direccion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.facebook = facebook;
        this.telFijo = telFijo;
        this.telCelular = telCelular;
        this.fechaNac = fechaNac;
        this.ocupacion = ocupacion;
        this.cuilCuit = cuilCuit;
    }

    public String getDniDonante() {return dniDonante;}
    public String getDireccion() {return direccion;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getEmail() {return email;}
    public String getFacebook() {return facebook;}
    public String getTelFijo() {return telFijo;}
    public String getTelCelular() {return telCelular;}
    public Date getFechaNac() {return fechaNac;}
    public String getOcupacion() {return ocupacion;}
    public String getCuilCuit() {return cuilCuit;}

    public void setDniDonante(String dniDonante) {this.dniDonante = dniDonante;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setEmail(String email) {this.email = email;}
    public void setFacebook(String facebook) {this.facebook = facebook;}
    public void setTelFijo(String telFijo) {this.telFijo = telFijo;}
    public void setTelCelular(String telCelular) {this.telCelular = telCelular;}
    public void setFechaNac(Date fechaNac) {this.fechaNac = fechaNac;}
    public void setOcupacion(String ocupacion) {this.ocupacion = ocupacion;}
    public void setCuilCuit(String cuilCuit) {this.cuilCuit = cuilCuit;}
}
