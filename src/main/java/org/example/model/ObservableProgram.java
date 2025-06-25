package org.example.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableProgram {
    private StringProperty id;
    private StringProperty name;
    private StringProperty description;

    public ObservableProgram(String id, String name, String description){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
    }

    public ObservableProgram(String id, String name){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
    }
    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }
}
