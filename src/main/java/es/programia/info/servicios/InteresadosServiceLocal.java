/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.programia.info.servicios;

import es.programia.excepciones.GestionSolicitudesException;
import es.programia.info.entidades.*;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Local
@TransactionManagement(TransactionManagementType.CONTAINER)
public interface InteresadosServiceLocal {
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Interesado buscarInteresadoPorCriterio(String nombre, String apellido, String empresa) throws GestionSolicitudesException;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    void CrearNuevoInteresado(Interesado interesado) throws GestionSolicitudesException;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    void modificarInteresado(Interesado interesado) throws GestionSolicitudesException;
}
