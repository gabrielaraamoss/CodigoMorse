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
public class Medico extends Usuario{
    private String especialidad;
    private Puesto puesto;

    public Medico(String nombres, String apellidos, String especialidad) {
        super(nombres,apellidos);
        this.especialidad = especialidad;
    }
      
}
