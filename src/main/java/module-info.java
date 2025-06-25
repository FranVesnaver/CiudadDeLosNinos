module org.ciudaddelosninos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.ciudaddelosninos to javafx.fxml;
    exports org.ciudaddelosninos;
    exports org.ciudaddelosninos.repository;
    opens org.ciudaddelosninos.repository to javafx.fxml;
    exports org.ciudaddelosninos.model;
    opens org.ciudaddelosninos.model to javafx.fxml;
    exports org.ciudaddelosninos.controller;
    opens org.ciudaddelosninos.controller to javafx.fxml;
}
