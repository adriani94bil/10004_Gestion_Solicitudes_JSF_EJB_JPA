/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.programia.info.web;

import es.programia.excepciones.GestionSolicitudesException;
import es.programia.info.entidades.Interesado;
import es.programia.info.servicios.InteresadosServiceLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author user
 */
@Named(value = "gestionSolicitudesMB")
@SessionScoped
public class GestionSolicitudesManagedBean implements Serializable {

    private Interesado interesado;
    
    private boolean editable;
    private boolean visible;
    private boolean accionModificar;
    private boolean accionNuevo;
    
    @EJB
    private InteresadosServiceLocal servicio;
    
    private static Logger log= Logger.getAnonymousLogger();

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isAccionModificar() {
        return accionModificar;
    }

    public void setAccionModificar(boolean accionModificar) {
        this.accionModificar = accionModificar;
    }

    public boolean isAccionNuevo() {
        return accionNuevo;
    }

    public void setAccionNuevo(boolean accionNuevo) {
        this.accionNuevo = accionNuevo;
    }

    
    

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        GestionSolicitudesManagedBean.log = log;
    }
    
    
    
    public GestionSolicitudesManagedBean() {
        
    }
    @PostConstruct
    public void inicializar(){
        interesado= new Interesado();
        editable=false;
        visible=false;
        accionModificar=false;
        accionNuevo=false;
    }
    
    //Acciones
    public void buscar(){
        try {
            setInteresado(servicio.buscarInteresadoPorCriterio(this.interesado.getNombre(), this.interesado.getApellidos() , this.interesado.getEmpresa()));
//            this.interesado=servicio.buscarInteresadoPorCriterio(this.interesado.getNombre(), this.interesado.getApellidos() , this.interesado.getEmpresa());
            setEditable(false);
            setVisible(true);
            setAccionModificar(true);
            
        } catch (GestionSolicitudesException ex) {
            log.info("No se encontro usuario");
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo encontrar"+ex.getMessage()) );
        }
    }
    public void modoNuevo(){
            setEditable(false);
            setVisible(true);
            setAccionNuevo(true);
    }
    public void cancelar(){
        inicializar();
    }
    public void grabar(){
        try {
            this.interesado.setFechaAlta(new Date(System.currentTimeMillis()));
            this.interesado.setVersion(1);
            servicio.CrearNuevoInteresado(interesado);
        } catch (GestionSolicitudesException ex) {
            Logger.getLogger(GestionSolicitudesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo grabar"+ex.getMessage()) );
        }
    }
    public void editar(){
        try {
            interesado.setFechaAlta(new Date(System.currentTimeMillis()));
            interesado.setVersion(1);
            servicio.modificarInteresado(interesado);
        } catch (GestionSolicitudesException ex) {
            Logger.getLogger(GestionSolicitudesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext fc= FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("No se pudo editar"+ex.getMessage()) );
        }
    }
}
