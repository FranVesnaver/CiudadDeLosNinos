package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerAportesController {

    @FXML private TableView<Aporte> tablaAportes;

    @FXML private TableColumn<Aporte, String> colDni;
    @FXML private TableColumn<Aporte, String> colNombre;
    @FXML private TableColumn<Aporte, String> colApellido;
    @FXML private TableColumn<Aporte, String> colPrograma;

    @FXML private Label errorLabel;

    @FXML
    public void volver() throws IOException {
        App.volver();
    }

    @FXML
    public void initialize(){
        colDni.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDniDonante()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colApellido.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido()));
        colPrograma.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrograma()));

        colDni.prefWidthProperty().bind(tablaAportes.widthProperty().multiply(0.2));
        colNombre.prefWidthProperty().bind(tablaAportes.widthProperty().multiply(0.2));
        colApellido.prefWidthProperty().bind(tablaAportes.widthProperty().multiply(0.2));
        colPrograma.prefWidthProperty().bind(tablaAportes.widthProperty().multiply(0.4));

        verAportes();
    }

    private void verAportes(){
        String query = "SELECT donante.dni_donante, donante.nombre, donante.apellido, programa.nombre AS nombre_programa " +
                "FROM ciudadDeLosNinos.donante " +
                "INNER JOIN ciudadDeLosNinos.aporta USING (dni_donante) " +
                "INNER JOIN ciudadDeLosNinos.programa USING (id_programa) " +
                "ORDER BY donante.apellido";

        try (Statement statement = SQLConnection.getConnection().createStatement();
             ResultSet result = statement.executeQuery(query)) {

            ObservableList<Aporte> aportes = FXCollections.observableArrayList();

            while (result.next()) {
                aportes.add(new Aporte (
                        result.getString("dni_donante"),
                        result.getString("nombre"),
                        result.getString("apellido"),
                        result.getString("nombre_programa")
                ));
            }

            tablaAportes.setItems(aportes);

        } catch (SQLException e) {
            errorLabel.setText("ERORR: " + e.getMessage());
        }
    }
}
