/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.programia.info.servicios;

import es.programia.excepciones.GestionSolicitudesException;
import es.programia.info.entidades.*;
import javax.ejb.Local;

@Local
public interface InteresadosServiceLocal {
    public Interesado buscarInteresadoPorCriterio(String nombre, String apellido, String empresa) throws GestionSolicitudesException;
    void CrearNuevoInteresado(Interesado interesado) throws GestionSolicitudesException;
    void modificarInteresado(Interesado interesado) throws GestionSolicitudesException;
}
