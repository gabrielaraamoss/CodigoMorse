/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author gabrielaramos
 */
public class Paciente {
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private Sintoma sintoma;

    public Paciente(String nombre, String apellido, int edad, char genero, Sintoma sintoma) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
    }
    
    
    
}
