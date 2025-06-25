package org.example.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableSponsor {
    private final StringProperty dni;
    private final StringProperty firstName;
    private final StringProperty lastName;

    public ObservableSponsor(String dni, String firstName, String lastName) {
        this.dni = new SimpleStringProperty(dni);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getDni() {
        return dni.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }
}
