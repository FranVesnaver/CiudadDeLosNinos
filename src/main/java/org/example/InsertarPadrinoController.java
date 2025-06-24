package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsertarPadrinoController {

    @FXML private TextField campoDni;
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private DatePicker campoFechaNac;
    @FXML private TextField campoDireccion;
    @FXML private TextField campoEmail;
    @FXML private TextField campoFacebook;
    @FXML private TextField campoTelFijo;
    @FXML private TextField campoTelCelular;
    @FXML private TextField campoOcupacion;
    @FXML private TextField campoCuilCuit;

    @FXML private Label errorLabel;

    @FXML
    public void volver() throws IOException {
        App.volver();
    }

    @FXML
    public void insertarPadrino(){
        DonanteDB padrino;
        try {
            padrino = generarPadrino();

            String query = "INSERT INTO ciudadDeLosNinos.donante (dni_donante, direccion, nombre, apellido, email, facebook, tel_fijo, tel_celular, fecha_nac, ocupacion, cuil_cuit) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query)) {
                statement.setString(1, padrino.getDniDonante());
                statement.setString(2, padrino.getDireccion());
                statement.setString(3, padrino.getNombre());
                statement.setString(4, padrino.getApellido());
                statement.setString(5, padrino.getEmail());
                statement.setString(6, padrino.getFacebook());
                statement.setString(7, padrino.getTelFijo());
                statement.setString(8, padrino.getTelCelular());
                statement.setDate(9, padrino.getFechaNac());
                statement.setString(10, padrino.getOcupacion());
                statement.setString(11, padrino.getCuilCuit());

                statement.executeUpdate();
                App.changeScene("main", "Ciudad de los niños");
            } catch (SQLException | IOException e) {
                errorLabel.setText("ERORR: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }
    }

    private DonanteDB generarPadrino() throws IllegalArgumentException{

        String input;

        String dniDonante;
        input = validarCampoObligatorio(campoDni);
        if (input.isEmpty() || !(input.matches("^[0-9]+$"))) {
            throw new IllegalArgumentException("Este campo no puede estar vacío y solo debe tener digitos (0-9).");
        } else {
            dniDonante = input;
        }

        String nombre = validarCampoObligatorio(campoNombre);

        String apellido = validarCampoObligatorio(campoApellido);

        String direccion = validarCampoObligatorio(campoDireccion);

        String email;
        input = validarCampoObligatorio(campoEmail);
        if (!input.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Email no válido (debe tener formato email@example.com)");
        } else {
            email = input;
        }

        String facebook = validarCampoObligatorio(campoFacebook);

        String telFijo = validarCampoObligatorio(campoTelFijo);

        String telCelular = validarCampoObligatorio(campoTelCelular);

        Date fechaNac;
        LocalDate inputDate = campoFechaNac.getValue();
        if (inputDate == null) {
            throw new IllegalArgumentException("Ingrese una fecha válida");
        } else {
            String dateString = inputDate.toString();
            fechaNac = java.sql.Date.valueOf(dateString);
        }

        String ocupacion = validarCampoObligatorio(campoOcupacion);

        String cuilCuit = validarCampoObligatorio(campoCuilCuit);

        return new DonanteDB(dniDonante,direccion,nombre,apellido,email,facebook,telFijo,telCelular,fechaNac,ocupacion,cuilCuit);
    }

    private static String validarCampoObligatorio(TextField campo) {
        String input = campo.getText();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Este campo no puede estar vacío.");
        }
        return input;
    }
}
