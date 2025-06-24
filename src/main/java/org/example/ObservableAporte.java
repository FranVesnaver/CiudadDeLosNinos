package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableAporte {
    private final StringProperty dniDonante;
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty programa;

    public ObservableAporte(String dniDonante, String nombre, String apellido, String programa) {
        this.dniDonante = new SimpleStringProperty(dniDonante);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.programa = new SimpleStringProperty(programa);
    }

    public String getDniDonante() {
        return dniDonante.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getApellido() {
        return apellido.get();
    }

    public String getPrograma() {
        return programa.get();
    }
}
