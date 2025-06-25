package org.ciudaddelosninos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.ciudaddelosninos.App;
import org.ciudaddelosninos.SQLConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SponsorProfileController {

    @FXML private Label dniLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label addressLabel;
    @FXML private Label emailLabel;
    @FXML private Label facebookLabel;
    @FXML private Label landlineLabel;
    @FXML private Label mobileLabel;
    @FXML private Label birthDateLabel;
    @FXML private Label occupationLabel;
    @FXML private Label cuilCuitLabel;

    @FXML private Label errorLabel;

    private static String dni;

    public static void setDni(String dni) {
        SponsorProfileController.dni = dni;
    }

    @FXML
    public void goBack() throws IOException {
        App.changeScene("show_sponsors", "Padrinos");
    }

    @FXML
    public void initialize(){
        fetchSponsor();
    }

    private void fetchSponsor() {
        String query = "SELECT * FROM ciudaddelosninos.donante WHERE dni_donante = ?";

        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query)){
            statement.setString(1, dni);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                dniLabel.setText("DNI: " + result.getString("dni_donante"));
                firstNameLabel.setText("Nombre: " + result.getString("nombre"));
                lastNameLabel.setText("Apellido: " + result.getString("apellido"));
                addressLabel.setText("Dirección: " + result.getString("direccion"));
                emailLabel.setText("Email: " + result.getString("email"));
                facebookLabel.setText("Facebook: " + result.getString("facebook"));
                landlineLabel.setText("Telefono fijo: " + result.getString("tel_fijo"));
                mobileLabel.setText("Telefono celular: " + result.getString("tel_celular"));
                birthDateLabel.setText("Fecha de nacimiento: " + result.getString("fecha_nac"));
                occupationLabel.setText("Ocupación: " + result.getString("ocupacion"));
                cuilCuitLabel.setText("CUIL/CUIT: " + result.getString("cuil_cuit"));
            }
        } catch (SQLException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }
    }
}
