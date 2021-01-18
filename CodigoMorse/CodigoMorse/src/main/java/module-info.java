module ec.edu.espol.gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.gui to javafx.fxml;
    exports ec.edu.espol.gui;
}
