/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.Paciente;
import ec.edu.espol.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gabrielaramos
 */
public class VentanaFXMLController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField edad;
    @FXML
    private ComboBox cbxG;
    @FXML
    private ComboBox cbxS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> items=new ArrayList<>();
        ArrayList<String> items1=new ArrayList<>();
        items.add("M");
        items.add("F");
        cbxG.setItems(FXCollections.observableArrayList(items));
        
        items1.add("Fiebre");
        items1.add("Desmayo");
        items1.add("Resfriado");
        items1.add("Congestión nasal");
        items1.add("Dolor de estómago");
        items1.add("Dolor de cabeza");
        items1.add("Dolor de pecho");
        items1.add("Escalofrío");    
        items1.add("tos");
        items1.add("Infarto");        
        cbxS.setItems(FXCollections.observableArrayList(items1));        
        
        
    }    

    @FXML
    private void regresar(MouseEvent event) {
    }

    @FXML
    private void puesto(MouseEvent event) {
    }

    @FXML
    private void registroM(MouseEvent event) {
        try {
            FXMLLoader fxmlloader2 = App.loadFXMLoad("RegistroMedicoFXML");
            App.setRoot(fxmlloader2);
            RegistroMedicoFXMLController controlador=fxmlloader2.getController();

            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
                a.show();
            }         
 
    }

    @FXML
    private void registrar(MouseEvent event) {
        if(nombre.getText().isEmpty()||apellido.getText().isEmpty()||cbxS.getValue()==null){
                if(nombre.getText().isEmpty()){
                    Alert a1=new Alert(Alert.AlertType.INFORMATION,"INGRESE NOMBRES");
                    a1.show();
                }
                else if(apellido.getText().isEmpty()){
                    Alert a2=new Alert(Alert.AlertType.INFORMATION,"INGRESE APELLIDOS");
                    a2.show();
                }
                
                else if(edad.getText().isEmpty()){
                    Alert a3=new Alert(Alert.AlertType.INFORMATION,"INGRESE EDAD");
                    a3.show();
                }                
                
                else if(cbxG.getValue()==null){
                    Alert a4=new Alert(Alert.AlertType.INFORMATION,"SELECCIONE");
                    a4.show();
                
                }                
                
                
                else if(cbxS.getValue()==null){
                    Alert a5=new Alert(Alert.AlertType.INFORMATION,"SELECCIONE");
                    a5.show();
                
                }
                
            Alert a=new Alert(Alert.AlertType.INFORMATION,"INGRESE DATOS");
            a.show();
          
            }else{
            Paciente paciente = new Paciente(nombre.getText(),apellido.getText(),Integer.parseInt(edad.getText()),cbxG.getValue().toString().charAt(0),cbxS.getValue().toString());
            try{
                ArrayList<Usuario> pacientes = Paciente.leer("pacientes.ser");
                pacientes.add(paciente);
                Paciente.guardar(pacientes,"pacientes.ser");
            }
            catch(ClassNotFoundException e){
                ArrayList<Usuario> usuarios = new ArrayList();
                usuarios.add(paciente);
                Paciente.guardar(usuarios,"pacientes.ser");
            }
            
        }           
        nombre.clear();
        apellido.clear();
        edad.clear();
        cbxG.setValue("");
        cbxS.setValue("");        
        
        
        
    }
    
}
