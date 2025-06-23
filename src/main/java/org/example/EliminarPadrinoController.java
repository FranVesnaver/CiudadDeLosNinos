package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarPadrinoController {

    @FXML private TextField campoDni;

    @FXML private Label errorLabel;

    @FXML
    public void volver() throws IOException {
        App.volver();
    }

    @FXML
    public void eliminarPadrino() throws IOException {
        String dniAEliminar = campoDni.getText();

        String query = "DELETE FROM ciudadDeLosNinos.donante WHERE dni_donante = ?";

        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query)) {
            statement.setString(1, dniAEliminar);
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
