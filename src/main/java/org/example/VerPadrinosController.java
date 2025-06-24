package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerPadrinosController {

    @FXML TableView<ObservableDonante> tabla;
    @FXML TableColumn<ObservableDonante, String> colDni;
    @FXML TableColumn<ObservableDonante, String> colNombre;
    @FXML TableColumn<ObservableDonante, String> colApellido;

    @FXML Label errorLabel;

    @FXML
    public void volver() throws IOException {
        App.volver();
    }

    @FXML
    public void initialize(){
        colDni.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDni()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colApellido.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido()));

        colDni.prefWidthProperty().bind(tabla.widthProperty().multiply(0.33));
        colNombre.prefWidthProperty().bind(tabla.widthProperty().multiply(0.33));
        colApellido.prefWidthProperty().bind(tabla.widthProperty().multiply(0.33));

        tabla.setRowFactory(tv -> {
            TableRow<ObservableDonante> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    ObservableDonante donante = row.getItem();
                    abrirFichaDonante(donante);
                }
            });
            return row;
        });

        recuperarPadrinos();
    }

    /*
    La idea es que la tabla de padrinos muestre solo dni, nombre y apellido
    luego se puede hacer click en cada entrada individual para acceder
    a la ficha personal de cada donante con toda su información
     */
    private void recuperarPadrinos(){
        String query = "SELECT dni_donante, nombre, apellido FROM ciudaddelosninos.donante";

        try (Statement statement = SQLConnection.getConnection().createStatement();
             ResultSet result = statement.executeQuery(query)) {

            ObservableList<ObservableDonante> donantes = FXCollections.observableArrayList();

            while (result.next()) {
                donantes.add(new ObservableDonante(
                        result.getString("dni_donante"),
                        result.getString("nombre"),
                        result.getString("apellido")
                ));
            }

            tabla.setItems(donantes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void abrirFichaDonante(ObservableDonante donante) {
        try {
            FichaController.setDniDonante(donante.getDni());
            App.changeScene("ficha", donante.getNombre() + " " + donante.getApellido());
        } catch (IOException e) {
            errorLabel.setText(e.getMessage());
        }
    }
}
