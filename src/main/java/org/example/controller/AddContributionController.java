package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.App;
import org.example.SQLConnection;
import org.example.model.ObservableProgram;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddContributionController {

    @FXML private TextField sponsorDniField;
    @FXML private TextField amountField;

    @FXML private ComboBox<String> frequencyComboBox;

    @FXML private MenuButton programsMenu;

    private String programId;
    private Integer paymentNumber;

    @FXML Label errorLabel;

    @FXML
    private void initialize(){
        frequencyComboBox.getItems().addAll("Semestral","Mensual");
        fetchPrograms();
    }

    @FXML
    private void setPaymentMethod() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("views/set_payment_method.fxml"));
        Parent root = loader.load();

        SetPaymentMethodController controller = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Añadir método de pago");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        paymentNumber = controller.getPaymentNumber();
    }

    @FXML
    private void goBack() throws IOException {
        if (paymentNumber != null) rollback(paymentNumber);
        App.goBack();
    }

    @FXML
    private void addContribution(){
        String query = "INSERT INTO ciudaddelosninos.aporta (dni_donante, id_programa, monto, frecuencia, nro_pago) VALUES " +
                "(?,?,?,?,?)";

        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query)) {
//            if (frequencyMenu.getText() == "Frecuencia") throw new IOException("Seleccione una frecuencia");
            if (paymentNumber == null) throw new IOException("Establezca un método de pago");

            statement.setString(1, sponsorDniField.getText());
            statement.setInt(2, Integer.parseInt(programId));
            statement.setDouble(3, Double.parseDouble(amountField.getText()));
            statement.setString(4, frequencyComboBox.getValue());
            statement.setInt(5, paymentNumber);

            statement.executeUpdate();
            App.goBack();

        } catch (SQLException | IOException | NumberFormatException e ) {
            if (e.getMessage().contains("is not present in table"))
                errorLabel.setText("El DNI ingresado no se encuentra entre los donantes, intente primero" +
                        " agregar a la persona como padrino");
            else if (e.getMessage().contains("parse null string"))
                errorLabel.setText("Los campos no pueden quedar vacíos");
            else
                errorLabel.setText("ERORR: " + e.getMessage());
        }
    }

    private void fetchPrograms(){
        String query = "SELECT id_programa, nombre FROM ciudaddelosninos.programa";

        try (Statement statement = SQLConnection.getConnection().createStatement();
             ResultSet result = statement.executeQuery(query)){

            ObservableList<ObservableProgram> programs = FXCollections.observableArrayList();

            while (result.next()) {
                programs.add(new ObservableProgram(
                        result.getString("id_programa"),
                        result.getString("nombre")
                ));
            }

            programsMenu.getItems().clear();

            for (ObservableProgram program : programs) {
                MenuItem menuItem = new MenuItem(program.getName());

                menuItem.setOnAction(e -> {
                    programsMenu.setText(program.getName());
                    programId = program.getId();
                });

                programsMenu.getItems().add(menuItem);
            }

        } catch (SQLException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }
    }

    private void rollback(Integer paymentNumber){
        String query = "DELETE FROM ciudaddelosninos.MedioDePago WHERE nro_pago = ?";

        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query)) {
            statement.setInt(1, paymentNumber);
            statement.executeUpdate();

        } catch (SQLException e) {
            errorLabel.setText("ERORR" + e.getMessage());
        }
    }

}
