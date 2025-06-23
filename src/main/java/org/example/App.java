package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage mainWindow;

    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = stage;
        Scene scene = new Scene(loadFXML("main"), 400, 520);
        stage.setTitle("Ciudad de los niños");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void changeScene(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml), 400, 520);
        mainWindow.setScene(scene);
        mainWindow.setTitle(title);
    }

    public static void volver() throws IOException {
        changeScene("main", "Ciudad de los niños");
    }

    public static void main(String[] args) {
        try {
            SQLConnection.connect();
            launch();
        } catch (SQLException e) {
            System.out.println("Error: failed connection to the database. " + e.getMessage());
        }
    }

}