/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.Medico;
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
public class RegistroMedicoFXMLController implements Initializable {


    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private ComboBox cbxS;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> items=new ArrayList<>();
        items.add("Cardiologo");
        items.add("Pediatra");
        items.add("Traumatologo");
        items.add("Medicina General");
        items.add("Neurologo");
        cbxS.setItems(FXCollections.observableArrayList(items));
    }    
    
    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader fxmlloader1 = App.loadFXMLoad("PrincipalFXML");
            App.setRoot(fxmlloader1);

        }catch (IOException ex) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "ERROR");
            alerta.show();
        }            
        
        
    }

    @FXML
    private void registroP(MouseEvent event) {
        try {
            FXMLLoader fxmlloader2 = App.loadFXMLoad("VentanaFXML");
            App.setRoot(fxmlloader2);
            VentanaFXMLController controlador=fxmlloader2.getController();

            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
                a.show();
            }           
    }

    @FXML
    private void puesto(MouseEvent event) {
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
                else if(cbxS.getValue()==null){
                    Alert a3=new Alert(Alert.AlertType.INFORMATION,"SELECCIONE");
                    a3.show();
                
                }
                
            Alert a=new Alert(Alert.AlertType.INFORMATION,"INGRESE DATOS");
            a.show();
          
            }
        else{
            Medico medico = new Medico(nombre.getText(),apellido.getText(),cbxS.getValue().toString());
            try{
                ArrayList<Usuario> medicos = Medico.leer("medicos.ser");
                medicos.add(medico);
                Medico.guardar(medicos,"medicos.ser");
            }
            catch(ClassNotFoundException e){
                ArrayList<Usuario> usuarios = new ArrayList();
                usuarios.add(medico);
                Medico.guardar(usuarios,"medicos.ser");
            }
            
        }           
        nombre.clear();
        apellido.clear();
        cbxS.setValue("");        
  
        
    }

}
