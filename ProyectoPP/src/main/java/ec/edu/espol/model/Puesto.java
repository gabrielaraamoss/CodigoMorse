package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabrielaramos
 */
public class Puesto implements Serializable {
    private int codigo;
    private List<Turno> turnos;
    private Medico medico;

    public Puesto(int codig, Medico medico) {
        this.codigo = codigo;
        this.turnos = new ArrayList<>();
        this.medico = medico;
    }

 public static void guardar(ArrayList<Puesto> arraylist,String archivo){
        try(ObjectOutputStream es = new ObjectOutputStream(new FileOutputStream(archivo))){
            try{
                if (arraylist.size()<=0){
                    throw new ErrorEmptyList(arraylist.size());
                }else{             
                    es.writeObject(arraylist);
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
    
    
    public static ArrayList<Puesto> leer(String archivo) throws ClassNotFoundException{
        ArrayList<Puesto> puestos=new ArrayList<>();
        try(ObjectInputStream es = new ObjectInputStream(new FileInputStream(archivo))){
            puestos=(ArrayList<Puesto>)es.readObject();

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        } 
        return puestos;
      
    }           
    
    
}
