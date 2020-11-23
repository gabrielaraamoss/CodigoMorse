package ec.edu.espol.model;

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
public class Puesto {
    private int codigo;
    private List<Turno> turnos;
    private Medico medico;

    public Puesto(int codigo, List<Turno> turnos, Medico medico) {
        this.codigo = codigo;
        this.turnos = turnos;
        this.medico = medico;
    }
    
    
    
}
