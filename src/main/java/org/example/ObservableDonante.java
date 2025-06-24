package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableDonante {
    private final StringProperty dni;
    private final StringProperty nombre;
    private final StringProperty apellido;

    public ObservableDonante(String dni, String nombre, String apellido) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
    }

    public String getDni() {
        return dni.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getApellido() {
        return apellido.get();
    }
}
