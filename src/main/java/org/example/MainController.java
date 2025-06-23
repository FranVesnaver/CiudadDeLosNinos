package org.example;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {

    @FXML
    public void insertarPadrino() throws IOException {

        App.changeScene("insertar_padrino", "Insertar un padrino");
    }

    @FXML
    private void eliminarPadrino() throws IOException {
        App.changeScene("eliminar_padrino", "Inserte el DNI a eliminar");
    }

    @FXML
    private void verAportes() throws IOException {
        App.changeScene("ver_aportes", "Aportes");
    }
}
