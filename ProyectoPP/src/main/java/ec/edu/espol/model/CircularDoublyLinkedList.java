/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import javafx.scene.Node;


/**
 *
 * @author gabrielaramos
 */
public class CircularDoublyLinkedList {
    private Node first;
    private Node last;
    private int current;

    public CircularDoublyLinkedList(Node first, Node last, int current) {
        this.first = first;
        this.last = last;
        this.current = current;
    }

    
}
