module com.espol.proyectoarboles1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.espol.proyectoarboles1 to javafx.fxml;
    exports com.espol.proyectoarboles1;
    opens com.espol.controller to javafx.fxml;
    exports com.espol.controller;
    opens com.espol.model to javafx.fxml;
    exports com.espol.model;
}
