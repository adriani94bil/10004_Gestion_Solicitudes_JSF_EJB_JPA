/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.programia.excepciones;

import java.io.Serializable;
import javax.ejb.ApplicationException;


@ApplicationException(rollback=true)
public class GestionSolicitudesException extends Exception implements Serializable{

    public GestionSolicitudesException(String string) {
        super(string);
    }

    public GestionSolicitudesException(String string, Throwable cause) {
        super(string, cause);
    }
    
    
    
}
