package ec.edu.espol.gui;

import ec.edu.espol.model.Sintoma;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        scene = new Scene(loadFXML("VentanaFXML"), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        System.out.println(Sintoma.leer("sintomas.ser"));
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public static void setRoot(FXMLLoader fxmlLoader) throws IOException {
        scene.setRoot(fxmlLoader.load());
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    
    public static FXMLLoader loadFXMLoad(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    
    

    public static void main(String[] args) {
        launch();
    }

}