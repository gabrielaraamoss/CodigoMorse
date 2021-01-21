module ec.edu.espol.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;
    

    opens ec.edu.espol.gui to javafx.fxml;
    exports ec.edu.espol.gui;
    
    opens Estructura to javafx.fxml;
    exports Estructura;
}
