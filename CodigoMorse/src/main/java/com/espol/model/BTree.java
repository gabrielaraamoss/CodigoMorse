/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author gabrielaramos
 */
public class BTree<E> {

    private Node<E> root;

    public static class Node<E> {

        private E data;
        private Node<E> left;
        private Node<E> right;
        private double posicionX;
        private double posicionY;
        
        public Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public double getPosicionX() {
            return posicionX;
        }

        public void setPosicionX(double posicionX) {
            this.posicionX = posicionX;
        }

        public double getPosicionY() {
            return posicionY;
        }

        public void setPosicionY(double posicionY) {
            this.posicionY = posicionY;
        }  
    }
    
    public Node<E> getRoot() {
        return root;
    }
    
    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    public int size(Node<E> n) {
        if (n == null) {
            return 0;
        }
        return 1 + size(n.left) + size(n.right);
    }
    
    public void anchura() {
        if (!isEmpty()) {
            Queue<Node<E>> cola = new LinkedList<>();
            cola.offer(root);
            while (!cola.isEmpty()) {
                Node<E> n = cola.poll();
                System.out.print(n.data + " ");
                if (n.left != null) {
                    cola.offer(n.left);
                }
                if (n.right != null) {
                    cola.offer(n.right);
                }
            }
            System.out.println("");
        }
    }

    public static PriorityQueue<LinkedList<String>> read(String archivo) {
    	PriorityQueue<LinkedList<String>> cola = new PriorityQueue<>((LinkedList<String> e1, LinkedList<String> e2) -> e1.size() - e2.size());
        File f = new File(archivo);
        try (Scanner sc = new Scanner(f);) {
            while (sc.hasNextLine()) {
                LinkedList<String> lista = new LinkedList<>();
            	String[] info = sc.nextLine().split(":");
                lista.add(info[0]);
                lista.addAll(Arrays.asList(info[1].split("\\|")));
                cola.offer(lista);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return cola;
    }

    public static BTree<String> crearArbolMorse() {
        BTree<String> arbol = new BTree<>();        
    	PriorityQueue<LinkedList<String>> datos = read("codigosMorse");
    	if(datos.isEmpty())
            return null;   
        arbol.root= new Node<>(" ");
        while(!datos.isEmpty()) {
        	LinkedList<String> codigo = datos.poll();
        	String letra = codigo.removeFirst();
                Node<String> l = new Node<>(letra);
                addNode(arbol.root,l,codigo);
        }
        return arbol;
    }
    
    
    private static boolean addNode(  Node<String> p, Node<String> letra,LinkedList<String> lista) {
    	if(letra == null && p == null ) return false;
        if(lista.isEmpty()) return false;
        Iterator<String> it = lista.iterator();
    	while(it.hasNext()) {
    		String s = it.next();
    		if(!it.hasNext()) {
                    if(s.equals(".")) p.right=letra;
            	    else p.left =letra;
    		}
                if(s.equals(".")) p = p.right;
                else p = p.left;	 
    	}
    	return true;
    }

    public static Queue<String> codificarMorse(char letra,BTree<String> arbol) {
        Queue<String> cola = new LinkedList<>();
        if(!arbol.isEmpty()){
            search( arbol.root.left, arbol.root.right,letra, cola);
        }
    	return cola;
    }
    
    private static Node<String> searchNode(String s, Node<String> p) {
        if (p == null) {
            return p;
        } else if (p.data.equals(s)) {
            return p;
        } else {
            if (searchNode(s, p.left) != null) {
                return searchNode(s, p.left);
            }
            return searchNode(s, p.right);
        }
    }
           
    private static void  search( Node<String> nIzq,Node<String> nDere,char letra, Queue<String> cola) {
        String s = String.valueOf(letra);
    	if(searchNode(s, nDere) != null) {
    		cola.offer(".");
    		search( nDere.left, nDere.right,letra, cola);
    	}
    	else if(searchNode(s, nIzq) != null) {
    		cola.offer("-");
    		search( nIzq.left, nIzq.right,letra, cola);
        }
        
    }    
 
}