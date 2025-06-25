package org.example.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.example.App;
import org.example.SQLConnection;
import org.example.model.ObservableSponsor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowSponsorsController {

    @FXML TableView<ObservableSponsor> table;
    @FXML TableColumn<ObservableSponsor, String> dniColumn;
    @FXML TableColumn<ObservableSponsor, String> firstNameColumn;
    @FXML TableColumn<ObservableSponsor, String> lastNameColumn;

    @FXML Label errorLabel;

    @FXML
    public void goBack() throws IOException {
        App.goBack();
    }

    @FXML
    public void initialize(){
        dniColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDni()));
        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));

        dniColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        firstNameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        lastNameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.33));

        table.setRowFactory(tv -> {
            TableRow<ObservableSponsor> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    ObservableSponsor sponsor = row.getItem();
                    showSponsorProfile(sponsor);
                }
            });
            return row;
        });

        fetchSponsors();
    }

    private void fetchSponsors(){
        String query = "SELECT dni_donante, nombre, apellido FROM ciudaddelosninos.donante";

        try (Statement statement = SQLConnection.getConnection().createStatement();
             ResultSet result = statement.executeQuery(query)) {

            ObservableList<ObservableSponsor> sponsors = FXCollections.observableArrayList();

            while (result.next()) {
                sponsors.add(new ObservableSponsor(
                        result.getString("dni_donante"),
                        result.getString("nombre"),
                        result.getString("apellido")
                ));
            }

            table.setItems(sponsors);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showSponsorProfile(ObservableSponsor sponsor) {
        try {
            SponsorProfileController.setDni(sponsor.getDni());
            App.changeScene("sponsor_profile", sponsor.getFirstName() + " " + sponsor.getLastName());
        } catch (IOException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
