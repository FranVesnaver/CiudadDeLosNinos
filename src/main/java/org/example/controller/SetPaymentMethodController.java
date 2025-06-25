package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.SQLConnection;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetPaymentMethodController {

    @FXML private TextField cardNumberField;
    @FXML private ComboBox<String> visaMastercard;
    @FXML private TextField cardholderNameField;
    @FXML private DatePicker expirationDateField;

    @FXML private Label errorLabel;

    private Integer paymentNumber;

    public Integer getPaymentNumber() {
        return paymentNumber;
    }


    @FXML
    public void initialize(){
        visaMastercard.getItems().addAll("Visa", "Mastercard");
    }

    @FXML
    private void addPaymentMethod(){
        String query1 = "INSERT INTO ciudaddelosninos.MedioDePago (nombre_titular) VALUES (?) RETURNING nro_pago";
        String query2 = "INSERT INTO ciudaddelosninos.Credito (nro_pago, fecha_vto, nombre_tarjeta, nro_tarjeta, nombre_titular)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement1 = SQLConnection.getConnection().prepareStatement(query1);
             PreparedStatement statement2 = SQLConnection.getConnection().prepareStatement(query2))
        {
            if (expirationDateField.getValue() == null) throw new IOException("Ingrese una fecha válida");
            if (cardNumberField.getText().isEmpty() || cardNumberField == null)  throw new IOException("Ingrese un número de tarjeta válido");
            if (cardholderNameField.getText().isEmpty() || cardholderNameField == null)  throw new IOException("Ingrese el nombre del titular");
            if (visaMastercard.getValue() == null) throw new IOException("Seleccione un sistema de pago");

            statement1.setString(1, cardholderNameField.getText());

            ResultSet result = statement1.executeQuery();
            if (result.next())
                paymentNumber = result.getInt("nro_pago");

            statement2.setInt(1, paymentNumber);
            statement2.setDate(2, Date.valueOf(expirationDateField.getValue()));
            statement2.setString(3, visaMastercard.getValue());
            statement2.setString(4, cardNumberField.getText());
            statement2.setString(5, cardholderNameField.getText());

            statement2.executeUpdate();

            ((Stage) cardNumberField.getScene().getWindow()).close();
        } catch (SQLException | IOException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }
    }

    @FXML
    private void cancel(){
        this.paymentNumber = null;
        ((Stage) cardNumberField.getScene().getWindow()).close();
    }
}
