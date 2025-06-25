package org.example.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.App;
import org.example.SQLConnection;
import org.example.model.ObservableContribution;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowContributionsController {

    @FXML private TableView<ObservableContribution> table;

    @FXML private TableColumn<ObservableContribution, String> dniColumn;
    @FXML private TableColumn<ObservableContribution, String> firstNameColumn;
    @FXML private TableColumn<ObservableContribution, String> lastNameColumn;
    @FXML private TableColumn<ObservableContribution, String> programColumn;

    @FXML private Label errorLabel;

    @FXML
    public void goBack() throws IOException {
        App.goBack();
    }

    @FXML
    public void initialize(){
        dniColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDni()));
        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        programColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProgram()));

        dniColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        firstNameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        lastNameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        programColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4));

        showContributions();
    }

    private void showContributions(){
        String query = "SELECT donante.dni_donante, donante.nombre, donante.apellido, programa.nombre AS nombre_programa " +
                "FROM ciudadDeLosNinos.donante " +
                "INNER JOIN ciudadDeLosNinos.aporta USING (dni_donante) " +
                "INNER JOIN ciudadDeLosNinos.programa USING (id_programa) " +
                "ORDER BY donante.apellido";

        try (Statement statement = SQLConnection.getConnection().createStatement();
             ResultSet result = statement.executeQuery(query)) {

            ObservableList<ObservableContribution> contributions = FXCollections.observableArrayList();

            while (result.next()) {
                contributions.add(new ObservableContribution(
                        result.getString("dni_donante"),
                        result.getString("nombre"),
                        result.getString("apellido"),
                        result.getString("nombre_programa")
                ));
            }

            table.setItems(contributions);

        } catch (SQLException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }
    }
}
