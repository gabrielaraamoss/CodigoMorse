/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gabrielaramos
 */
public class Sintoma  implements Serializable{
    private String nombre;
    private int prioridad;

    public Sintoma(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    

    public static void guardar(ArrayList<Sintoma> arraylist,String archivo){
        try(ObjectOutputStream es = new ObjectOutputStream(new FileOutputStream(archivo))){
            try{
                if (arraylist.size()<=0){
                    throw new ErrorEmptyList(arraylist.size());
                }else{             
                    es.writeObject(arraylist);
                    es.close();
                }
                
            }catch(ErrorEmptyList e){
                System.out.println(e);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    
    }
    
    public static ArrayList<Sintoma> leer(String archivo) throws ClassNotFoundException{
        ArrayList<Sintoma> sintomas=new ArrayList<>();
        try(ObjectInputStream es = new ObjectInputStream(new FileInputStream(archivo))){
            sintomas=(ArrayList<Sintoma>)es.readObject();
            es.close();

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        } 
        return sintomas;
      
    }        

      
    @Override
    public String toString() {
        return nombre + "|" + prioridad;
    }
    

    
}
