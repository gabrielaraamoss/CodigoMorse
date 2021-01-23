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
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

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
    
    Node<String> nodo;
    private Circle vertice;
    private Line arista;
    private Text data;
    private BTree<String> tree;
    private Queue<String> codigoMorse;
    private String caracter;
    private AudioClip Sonidopunto;
    private AudioClip Sonidoraya;
    private final double radio=20;
    private final String rojo = "#ee2222";
    private final String azul= "#2293f5";
    private final String fontStyle ="-fx-font:26 Arial;";
    private final LinkedList<Circle> nodosPintado = new LinkedList<>();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        Sonidopunto  = new AudioClip(Paths.get("punto.mp3").toUri().toString());
        Sonidoraya = new AudioClip(Paths.get("raya.mp3").toUri().toString());
        double y = lienzo.getLayoutY();
        double x = (lienzo.getPrefWidth()/2)+40;
        tree = BTree.crearArbolMorse();
        nodo = tree.getRoot();
        drawTree(x, y, nodo);
    }    

    @FXML
    private void bajarArbol(ActionEvent event) {
        removeNodes();
        String palabra = textoEntrada.getText().toUpperCase();
        textResultado.setText("");
        new Thread(() -> {
            for (int i = 0; i < palabra.length(); i++) {
                char letra = palabra.charAt(i);
                codigoMorse = BTree.codificarMorse(letra,tree);
                try {
                    while (!codigoMorse.isEmpty()) {
                        caracter = codigoMorse.poll();
                        if (caracter.equals(".")) {
                            drawLineRunLater(nodo,nodo.getRight(),azul);
                            nodo = nodo.getRight();
                            Sonidopunto.play();
                            Thread.sleep(1000);
                            removeLineRunLater();
                        } else {
                            drawLineRunLater(nodo,nodo.getLeft(),rojo);
                            nodo = nodo.getLeft();
                            Sonidoraya.play();
                            Thread.sleep(1300);
                            removeLineRunLater();
                        }
                    }
                    addLetraRunLater(letra);
                    drawNodeRunLater(nodo);
                    nodo = tree.getRoot();
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
        
    }

    private void drawTree(double xInicial, double yInicial, BTree.Node<String> nodo){
        drawNode(xInicial, yInicial, nodo);
        double distancia;
        if (nodo.getLeft() != null) {
            distancia = (tree.size(nodo.getLeft())+1)*13;
            drawLine(xInicial, yInicial, -distancia);
            drawTree(xInicial - distancia, yInicial + 60, nodo.getLeft());
        }
        if (nodo.getRight() != null) {
            distancia = (tree.size(nodo.getRight())+1)*10;
            drawLine(xInicial, yInicial, distancia);
            drawTree(xInicial + distancia, yInicial +60, nodo.getRight());
        }
    }
    
    private void drawLineRunLater(Node<String> nodoInicial, Node<String>nodoFinal,String color){
        Platform.runLater(()->{
            arista = new Line(nodoInicial.getPosicionX(), nodoInicial.getPosicionY()+radio, 
                    nodoFinal.getPosicionX(), nodoFinal.getPosicionY()-radio);
            arista.setStroke(Paint.valueOf(color));
            arista.setStrokeWidth(3);
            lienzo.getChildren().add(arista);
        });
    }
    
    private void drawLine(double x , double y ,double distancia){
        arista = new Line(x, y+radio, x + distancia, y + 60-radio);
            arista.setStroke(Paint.valueOf("white"));
            arista.setStrokeWidth(3);
            lienzo.getChildren().add(arista);
    }
    
    
    private void  removeLineRunLater(){
        Platform.runLater(()->{
            lienzo.getChildren().remove(arista);
        });
    }
    
    private void drawNodeRunLater(Node<String> nodo){
        Platform.runLater(()->{
            vertice = new Circle(nodo.getPosicionX(),nodo.getPosicionY(), radio, 
                    Paint.valueOf("white"));
            vertice.setStroke(Paint.valueOf("red"));
            vertice.setStrokeWidth(3);
            data = new Text(nodo.getPosicionX() - 8, nodo.getPosicionY()+7, 
                    nodo.getData());
            data.setStyle(fontStyle);
            lienzo.getChildren().add(vertice);
            lienzo.getChildren().add(data);
            nodosPintado.add(vertice);
        });
    }
    
    private void drawNode(double x ,double y , Node<String> nodo){
        vertice = new Circle(x, y, radio, Paint.valueOf("#cc525a"));
        nodo.setPosicionX(x);
        nodo.setPosicionY(y);
        vertice.setStroke(Paint.valueOf("black"));
        vertice.setStrokeWidth(3);
        data = new Text(x - 8, y+7, nodo.getData());
        data.setStyle(fontStyle);
        lienzo.getChildren().add(vertice);
        lienzo.getChildren().add(data);
    }
    
    private  void addLetraRunLater(char letra){
        Platform.runLater(()->{
            textResultado.setText(textResultado.getText()+
                            letra+" ");
        });
    }
    
    private void removeNodes(){
        nodosPintado.forEach(item -> {
            lienzo.getChildren().remove(item);
        });
            
    }

}
