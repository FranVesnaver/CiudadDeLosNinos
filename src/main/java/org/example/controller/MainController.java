package org.example.controller;

import javafx.fxml.FXML;
import org.example.App;

import java.io.IOException;

public class MainController {

    @FXML
    public void addSponsor() throws IOException {

        App.changeScene("add_sponsor", "Insertar un padrino");
    }

    @FXML
    private void deleteSponsor() throws IOException {
        App.changeScene("delete_sponsor", "Inserte el DNI a eliminar");
    }

    @FXML
    private void addContribution() throws IOException {
        App.changeScene("add_contribution", "Insertar aporte");
    }

    @FXML
    private void showSponsors() throws IOException {
        App.changeScene("show_sponsors", "Padrinos");
    }

    @FXML
    private void showContributions() throws IOException {
        App.changeScene("show_contributions", "Aportes");
    }
}
