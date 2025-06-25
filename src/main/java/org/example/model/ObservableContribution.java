package org.example.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableContribution {
    private final StringProperty dni;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty program;

    public ObservableContribution(String dni, String firstName, String lastName, String program) {
        this.dni = new SimpleStringProperty(dni);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.program = new SimpleStringProperty(program);
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

    public String getProgram() {
        return program.get();
    }
}
