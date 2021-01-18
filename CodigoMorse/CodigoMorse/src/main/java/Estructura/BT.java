/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author gabrielaramos
 */
public class BT<E> {

    private Node<E> root;
    private int current;

    private static class Node<E> {

        private E data;
        private Node<E> left;
        private Node<E> right;

        public Node(E data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(E element, E parent) {
        if (element == null) {
            return false;
        }
        Node<E> child = new Node<>(element);
        if (isEmpty() && parent == null) {
            root = child;
            current++;
            return true;
        }
        Node<E> nParent = searchNode(parent);
        return addingNode(child, nParent);
    }

    private boolean addingNode(Node<E> child, Node<E> nParent) {
        if (searchNode(child.data) == null && nParent != null) {
            if (nParent.left == null) {
                nParent.left = child;
                current++;
                return true;
            } else if (nParent.right == null) {
                nParent.right = child;
                current++;
                return true;
            }
        }
        return false;
    }

    private Node<E> searchNode(E data) {
        return searchNode(data, root);
    }

    private Node<E> searchNode(E data, Node<E> p) {
        if (p == null) {
            return p;
        } else if (p.data.equals(data)) {
            return p;
        } else {
            Node<E> nleft = searchNode(data, p.left);
            if (nleft != null) {
                return nleft;
            }
            return searchNode(data, p.right);
        }
    }

    public boolean remove(E data) {
        if (data == null) {
            return false;
        }
        Node<E> parent = searchParent(data);
        if (parent == null) {
            return false;
        } else if (parent.left != null && parent.left.data.equals(data)) {
            parent.left = null;
        } else {
            parent.right = null;
        }
        current--;
        return true;
    }

    private Node<E> searchParent(E data) {
        return searchParent(data, root);
    }

    private Node<E> searchParent(E data, Node<E> n) {
        if (n == null) {
            return n;
        } else if ((n.left != null && n.left.data.equals(data)) || (n.right != null && n.right.data.equals(data))) {
            return n;
        } else {
            Node<E> nleft = searchParent(data, n.left);
            if (nleft != null) {
                return nleft;
            }
            return searchParent(data, n.right);
        }
    }

    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        return contains(root, element);
    }

    private boolean contains(Node<E> n, E element) {
        if (n == null) {
            return false;
        } else if (n.data.equals(element)) {
            return true;
        }
        return contains(n.left, element) || contains(n.right, element);
    }

    public int size() {
        return size(root);
    }

    private int size(Node<E> n) {
        if (n == null) {
            return 0;
        }
        return 1 + size(n.left) + size(n.right);
    }


    private void postOrden(Node<E> n) {
        if (n != null) {
            postOrden(n.left);
            postOrden(n.right);
            System.out.println(n.data);

        }
    }

    private void preOrden(Node<E> n) {
        if (n != null) {
            System.out.println(n.data);
            preOrden(n.left);
            preOrden(n.right);
        }
    }

    private void inOrden(Node<E> n) {
        if (n != null) {
            inOrden(n.left);
            System.out.println(n.data);
            inOrden(n.right);
        }
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
    	PriorityQueue<LinkedList<String>> cola = new PriorityQueue<LinkedList<String>>((LinkedList<String> e1, LinkedList<String> e2) -> e1.size() - e2.size());
        
        File f = new File(archivo);
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                LinkedList<String> lista = new LinkedList<>();
            	String[] info = sc.nextLine().split(":");
                lista.add(info[0]);
                for (String s : info[1].split("\\|")) {
                    lista.add(s);
                }
                cola.offer(lista);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return cola;
    }

    public static BT<String> crearArbolMorse() {
        BT<String> arbol = new BT<>();        
        
    	PriorityQueue<LinkedList<String>> datos = read("traducciones.txt");
    	if(datos == null)
            return null;
        
        arbol.root= new Node<>("INICIO");
        while(!datos.isEmpty()) {
        	LinkedList<String> codigo = datos.poll();
        	String letra = codigo.removeFirst();
                add(codigo,new Node<>(letra),arbol.root);
        }
        return arbol;
    }
    
    private static boolean add( LinkedList<String> value,Node<String> letra, Node<String> p) {
    	ListIterator<String> it = value.listIterator();

    	while(it.hasNext()) {
    		 String s = it.next();
    		 if(!it.hasNext()) {
    			 if(s.equals(".")) p.right=letra;
    			 else p.left =letra;

    		 } else {
    			 if(s.equals(".")) p = p.right;
    			 else p = p.left;
    		 }
    	}
    	return true;
    }

    public static String codificarMorse(String palabra) {
    	BT<String> arbol = crearArbolMorse();
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < palabra.length(); i++) {
            sb = search( arbol.root.left, arbol.root.right,palabra.toUpperCase().charAt(i), sb);
    	}
    	return sb.toString();
    }
    

    
    private static StringBuilder search( Node<String> nIzq,Node<String> nDere,char letra, StringBuilder sb) {
    	if(searchNode(String.valueOf(letra), nIzq) != null) {
    		sb.append("-");
    		search( nIzq.left, nIzq.right,letra, sb);
    	}else if(searchNode(String.valueOf(letra), nDere) != null) {
    		sb.append(".");
    		search( nDere.left, nDere.right,letra, sb);
    	}
    	return sb;
    }
    
    
 
        private static Node<String> searchNode(String s, Node<String> p) {
        if (p == null) {
            return p;
        } else if (p.data.equals(s)) {
            return p;
        } else {
            Node<String> nleft = searchNode(s, p.left);
            if (nleft != null) {
                return nleft;
            }
            return searchNode(s, p.right);
        }
    }

}