/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.programia.info.web;

import es.programia.info.entidades.Interesado;
import es.programia.info.servicios.InteresadosServiceLocal;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "gestionSolicitudesMB")
@ViewScoped
public class GestionSolicitudesManagedBean implements Serializable {

    private Interesado interesado;
    
    @EJB
    private InteresadosServiceLocal servicio;
    
    private static Logger log= Logger.getAnonymousLogger();

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        GestionSolicitudesManagedBean.log = log;
    }
    
    
    
    public GestionSolicitudesManagedBean() {
        interesado=new Interesado();
    }
    

    
}
