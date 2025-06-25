package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.SQLConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteSponsorController {

    @FXML private TextField dniField;

    @FXML private Label errorLabel;

    @FXML
    public void goBack() throws IOException {
        App.goBack();
    }

    @FXML
    public void deleteSponsor() throws IOException {
        String dniToDelete = dniField.getText();

        String query = "DELETE FROM ciudadDeLosNinos.donante WHERE dni_donante = ?";

        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query)) {
            statement.setString(1, dniToDelete);
            int rowCount = statement.executeUpdate();

            if (rowCount <= 0) {
                errorLabel.setText("No se encontró el donante");
            } else {
                App.changeScene("main", "Ciudad De Los Niños");
            }
        } catch (SQLException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }

    }
}
