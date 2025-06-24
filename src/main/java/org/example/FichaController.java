package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FichaController {

    @FXML private Label dniLabel;
    @FXML private Label nombreLabel;
    @FXML private Label apellidoLabel;
    @FXML private Label direccionLabel;
    @FXML private Label emailLabel;
    @FXML private Label facebookLabel;
    @FXML private Label telFijoLabel;
    @FXML private Label telCelularLabel;
    @FXML private Label fechaNacimientoLabel;
    @FXML private Label ocupacionLabel;
    @FXML private Label cuilCuitLabel;

    @FXML private Label errorLabel;

    private static String dniDonante;

    public static void setDniDonante(String dni) {
        dniDonante = dni;
    }

    @FXML
    public void volver() throws IOException {
        App.changeScene("ver_padrinos", "Padrinos");
    }

    @FXML
    public void initialize(){
        recuperarInfoDonante();
    }

    private void recuperarInfoDonante() {
        String query = "SELECT * FROM ciudaddelosninos.donante WHERE dni_donante = ?";

        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query)){
            statement.setString(1, dniDonante);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                dniLabel.setText("DNI: " + result.getString("dni_donante"));
                nombreLabel.setText("Nombre: " + result.getString("nombre"));
                apellidoLabel.setText("Apellido: " + result.getString("apellido"));
                direccionLabel.setText("Dirección: " + result.getString("direccion"));
                emailLabel.setText("Email: " + result.getString("email"));
                facebookLabel.setText("Facebook: " + result.getString("facebook"));
                telFijoLabel.setText("Telefono fijo: " + result.getString("tel_fijo"));
                telCelularLabel.setText("Telefono celular: " + result.getString("tel_celular"));
                fechaNacimientoLabel.setText("Fecha de nacimiento: " + result.getString("fecha_nac"));
                ocupacionLabel.setText("Ocupación: " + result.getString("ocupacion"));
                cuilCuitLabel.setText("CUIL/CUIT: " + result.getString("cuil_cuit"));
            }
        } catch (SQLException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }
    }
}
