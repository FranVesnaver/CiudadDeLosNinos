package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.SQLConnection;
import org.example.repository.SponsorRepository;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddSponsorController {

    @FXML private TextField dniField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker birthDateField;
    @FXML private TextField addressField;
    @FXML private TextField emailField;
    @FXML private TextField facebookField;
    @FXML private TextField landlineField;
    @FXML private TextField mobileField;
    @FXML private TextField occupationField;
    @FXML private TextField cuilCuitField;

    @FXML private Label errorLabel;

    @FXML
    public void goBack() throws IOException {
        App.goBack();
    }

    @FXML
    public void addSponsor(){
        SponsorRepository padrino;
        try {
            padrino = generateSponsor();

            String query = "INSERT INTO ciudadDeLosNinos.donante (dni_donante, direccion, nombre, apellido, email, facebook, tel_fijo, tel_celular, fecha_nac, ocupacion, cuil_cuit) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query)) {
                statement.setString(1, padrino.getDni());
                statement.setString(2, padrino.getAddress());
                statement.setString(3, padrino.getFirstName());
                statement.setString(4, padrino.getLastName());
                statement.setString(5, padrino.getEmail());
                statement.setString(6, padrino.getFacebook());
                statement.setString(7, padrino.getLandline());
                statement.setString(8, padrino.getMobile());
                statement.setDate(9, padrino.getBirthDate());
                statement.setString(10, padrino.getOccupation());
                statement.setString(11, padrino.getCuilCuit());

                statement.executeUpdate();
                App.goBack();
            } catch (SQLException | IOException e) {
                errorLabel.setText("ERORR: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }
    }

    private SponsorRepository generateSponsor() throws IllegalArgumentException{

        String input;

        String dni;
        input = validateRequiredField(dniField);
        if (input.isEmpty() || !(input.matches("^[0-9]+$"))) {
            throw new IllegalArgumentException("Este campo no puede estar vacío y solo debe tener digitos (0-9).");
        } else {
            dni = input;
        }

        String firstName = validateRequiredField(firstNameField);

        String lastName = validateRequiredField(lastNameField);

        String address = validateRequiredField(addressField);

        String email;
        input = validateRequiredField(emailField);
        if (!input.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Email no válido (debe tener formato email@example.com)");
        } else {
            email = input;
        }

        String facebook = validateRequiredField(facebookField);

        String landline = validateRequiredField(landlineField);

        String mobile = validateRequiredField(mobileField);

        Date birthDate;
        LocalDate inputDate = birthDateField.getValue();
        if (inputDate == null) {
            throw new IllegalArgumentException("Ingrese una fecha válida");
        } else {
            String dateString = inputDate.toString();
            birthDate = java.sql.Date.valueOf(dateString);
        }

        String occupation = validateRequiredField(occupationField);

        String cuilCuit = validateRequiredField(cuilCuitField);

        return new SponsorRepository(dni,address,firstName,lastName,email,facebook,landline,mobile,birthDate,occupation,cuilCuit);
    }

    private static String validateRequiredField(TextField field) {
        String input = field.getText();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Este campo no puede estar vacío.");
        }
        return input;
    }
}
