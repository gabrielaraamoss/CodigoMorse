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
public class Turno {
    private String codigo;
    private Puesto puesto;
    private Paciente paciente;

    public Turno(String codigo, Puesto puesto, Paciente paciente) {
        this.codigo = codigo;
        this.puesto = puesto;
        this.paciente = paciente;
    }
    
    
}
