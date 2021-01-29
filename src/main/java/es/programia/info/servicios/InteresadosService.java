/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.programia.info.servicios;

import es.programia.excepciones.GestionSolicitudesException;
import es.programia.info.entidades.Interesado;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InteresadosService implements InteresadosServiceLocal{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Interesado buscarInteresadoPorCriterio(String nombre, String apellidos, String empresa) throws GestionSolicitudesException {
        Query query=em.createNamedQuery("Interesado.findByNombreApellidosEmpresa");
        if (nombre==null) {
            nombre="%";
        }
        if (apellidos==null) {
            apellidos="%";
        }
        if (empresa==null) {
            empresa="%";
        }
        
        query.setParameter("nombre", nombre);
        query.setParameter("apellidos", apellidos);
        query.setParameter("empresa", empresa);
        
        query.setMaxResults(1);
        
        List<Interesado> interesados= query.getResultList();
        
        if (interesados==null || interesados.size()==0) {
            throw new GestionSolicitudesException("sin resultados");
        }
        
        return interesados.get(0);
    }

    @Override
    public void CrearNuevoInteresado(Interesado interesado) throws GestionSolicitudesException {
        em.persist(interesado);
    }

    @Override
    public void modificarInteresado(Interesado interesado) throws GestionSolicitudesException {
        try{
            Interesado i= em.find(Interesado.class, interesado.getIdInteresado());
            if (i==null) {
                throw new GestionSolicitudesException("interesado not found");
            }
            em.merge(interesado);
        
        }catch(OptimisticLockException ole){
            throw new GestionSolicitudesException("Ya ha sido modificado por otro usuario");
        }
        
        
    }
    
}
