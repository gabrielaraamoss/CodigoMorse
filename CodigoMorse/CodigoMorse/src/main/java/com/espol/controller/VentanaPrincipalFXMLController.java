/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.controller;

import com.espol.model.BTree;
import com.espol.model.BTree.Node;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    
    private double x;
    private double y;
    private Circle vertice;
    private Line arista;
    private Text data;
    private double distancia;
    private BTree<String> tree;

    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        double y = lienzo.getLayoutY();
        double x = lienzo.getPrefWidth()/2;
        tree = BTree.crearArbolMorse();
        Node<String> nodo = tree.getRoot();
        drawTree(x, y, nodo);
        
        
    }    

    @FXML
    private void bajarArbol(ActionEvent event) {
    }
    
    private void drawTree(double xInicial, double yInicial, BTree.Node<String> nodo) {
        vertice = new Circle(xInicial, yInicial, 17, Paint.valueOf("blue"));
        data = new Text(xInicial - 5, yInicial, nodo.getData());
        lienzo.getChildren().add(vertice);
        lienzo.getChildren().add(data);
        if (nodo.getLeft() != null) {
            distancia = tree.size(nodo.getLeft())+1;
            arista = new Line(xInicial, yInicial+17, xInicial - distancia * 10, yInicial + 60);
            arista.setStroke(Paint.valueOf("red"));
            arista.setStrokeWidth(3);
            data = new Text(xInicial, yInicial, nodo.getData());
            lienzo.getChildren().add(arista);
            drawTree(xInicial - distancia * 10, yInicial + 60, nodo.getLeft());
        }

        if (nodo.getRight() != null) {
            distancia = tree.size(nodo.getRight())+1;
            arista = new Line(xInicial, yInicial+17, xInicial + distancia * 10, yInicial + 60);
            arista.setStroke(Paint.valueOf("red"));
            arista.setStrokeWidth(3);
            lienzo.getChildren().add(arista);
            drawTree(xInicial + distancia * 10, yInicial + 60, nodo.getRight());
        }
    }

}
