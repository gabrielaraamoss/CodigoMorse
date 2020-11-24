module ec.edu.espol.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.gui to javafx.fxml;
    exports ec.edu.espol.gui;
}
