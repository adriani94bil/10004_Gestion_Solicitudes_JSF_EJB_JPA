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
import java.util.logging.Level;
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
        interesado=new Interesado();
        editable=false;
        visible=false;
        accionModificar=true;
        accionNuevo=true;
    }
    
    //Acciones
    public void buscar(){
        try {
            Interesado interesadoBusc=new Interesado();
            interesadoBusc=servicio.buscarInteresadoPorCriterio(this.interesado.getNombre(), this.interesado.getApellidos() , this.interesado.getEmpresa());
            setEditable(false);
            setVisible(true);
            // Guardo el interesado buscado dentro de la variable interesado
            interesado=interesadoBusc;
            
        } catch (GestionSolicitudesException ex) {
            Logger.getLogger(GestionSolicitudesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modoNuevo(){
        try {
            Interesado interesadoBusc=new Interesado();
            interesadoBusc=servicio.buscarInteresadoPorCriterio(this.interesado.getNombre(), this.interesado.getApellidos() , this.interesado.getEmpresa());
            setEditable(true);
            setVisible(true);
            // Guardo el interesado buscado dentro de la variable interesado
            interesado=interesadoBusc;
            
        } catch (GestionSolicitudesException ex) {
            Logger.getLogger(GestionSolicitudesManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cancelar(){
        setEditable(false);
        setVisible(false);
    }
    public void grabar(){
        
    }
    public void editar(){
        
    }
}
