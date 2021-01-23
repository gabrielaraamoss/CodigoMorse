module com.espol.proyectoarboles1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;
    
    opens com.espol.proyectoarboles1 to javafx.fxml;
    exports com.espol.proyectoarboles1;
    opens com.espol.controller to javafx.fxml;
    opens com.espol.model to javafx.fxml;
}
