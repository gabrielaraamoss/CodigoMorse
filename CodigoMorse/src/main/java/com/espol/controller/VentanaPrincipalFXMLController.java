/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.controller;

import com.espol.model.BTree;
import com.espol.model.BTree.Node;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author dilan
 */
public class VentanaPrincipalFXMLController implements Initializable {

    @FXML
    private AnchorPane lienzo;
    @FXML
    private TextField textoEntrada;
    @FXML
    private Label textResultado;
    
    private double x;
    private double y;
    Node<String> nodo;
    private Circle vertice;
    private Line arista;
    private Text data;
    private double distancia;
    private BTree<String> tree;
    private String palabra;
    private  int tamañaPalabra;
    private Queue<String> codigoMorse;
    private String caracter;
    private  Media punto;
    private Media raya;
    private MediaPlayer player;
    private double radio;
    private String rojo = "#ee2222";
    private String azul= "#2293f5";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        punto = new Media(Paths.get("punto.mp3").toUri().toString());
        raya = new Media(Paths.get("raya.mp3").toUri().toString());
        y = lienzo.getLayoutY();
        x = (lienzo.getPrefWidth()/2)+40;
        tree = BTree.crearArbolMorse();
        nodo = tree.getRoot();
        drawTree(x, y, nodo);

        
    }    

    @FXML
    private void bajarArbol(ActionEvent event) {
        palabra = textoEntrada.getText().toUpperCase();
        tamañaPalabra = palabra.length();
        textResultado.setText("");
        new Thread(() -> {
            for (int i = 0; i < tamañaPalabra; i++) {
                char letra = palabra.charAt(i);
                codigoMorse = BTree.codificarMorse(letra);
                try {
                    while (!codigoMorse.isEmpty()) {
                        caracter = codigoMorse.poll();
                        if (caracter.equals(".")) {
                            drawLine(nodo,nodo.getRight(),azul);
                            nodo = nodo.getRight(); 
                            player = new MediaPlayer(punto);
                            player.play();
                            Thread.sleep(1000);
                            removeLine();
                        } else {
                            drawLine(nodo,nodo.getLeft(),rojo);
                            nodo = nodo.getLeft(); 
                            player = new MediaPlayer(raya);
                            player.play();
                            Thread.sleep(1000);
                            removeLine();
                        }
                    }
                    addLetra(letra);
                    nodo = tree.getRoot();
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }

        }).start();
    }

    private void drawTree(double xInicial, double yInicial, BTree.Node<String> nodo){
        radio = 20;
        vertice = new Circle(xInicial, yInicial, radio, Paint.valueOf("#cc525a"));
        nodo.setPosicionX(xInicial);
        nodo.setPosicionY(yInicial);
        vertice.setStroke(Paint.valueOf("black"));
        vertice.setStrokeWidth(3);
        data = new Text(xInicial - 5, yInicial+3, nodo.getData());
        lienzo.getChildren().add(vertice);
        lienzo.getChildren().add(data);
        if (nodo.getLeft() != null) {
            distancia = (tree.size(nodo.getLeft())+1)*13;
            arista = new Line(xInicial, yInicial+radio, xInicial - distancia, yInicial + 60-radio);
            arista.setStroke(Paint.valueOf("white"));
            arista.setStrokeWidth(3);
            data = new Text(xInicial, yInicial, nodo.getData());
            lienzo.getChildren().add(arista);
            drawTree(xInicial - distancia, yInicial + 60, nodo.getLeft());
        }
        if (nodo.getRight() != null) {
            distancia = (tree.size(nodo.getRight())+1)*10;
            arista = new Line(xInicial, yInicial+radio, xInicial + distancia, yInicial + 60-radio);
            arista.setStroke(Paint.valueOf("white"));
            arista.setStrokeWidth(3);
            lienzo.getChildren().add(arista);
            drawTree(xInicial + distancia, yInicial +60, nodo.getRight());
        }
    }
    
    private void drawLine(Node<String> nodoInicial, Node<String>nodoFinal,String color){
        Platform.runLater(()->{
            arista = new Line(nodoInicial.getPosicionX(), nodoInicial.getPosicionY()+radio, 
                    nodoFinal.getPosicionX(), nodoFinal.getPosicionY()-radio);
            arista.setStroke(Paint.valueOf(color));
            arista.setStrokeWidth(3);
            lienzo.getChildren().add(arista);
        });
    }
    
    
    private void  removeLine(){
        Platform.runLater(()->{
            lienzo.getChildren().remove(arista);
        });
    }
    
    private  void addLetra(char letra){
        Platform.runLater(()->{
            textResultado.setText(textResultado.getText()+
                            letra+" ");
        });
    }

}
