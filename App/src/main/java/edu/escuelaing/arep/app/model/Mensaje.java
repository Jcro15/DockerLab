package edu.escuelaing.arep.app.model;

import java.util.Date;

/**
 * Clase que representa el mensaje de un usuario
 */
public class Mensaje {

    private String mensaje;
    private Date fecha;

    public Mensaje(String mensaje) {
        this.mensaje=mensaje;
        this.fecha=new Date();
    }
    public Mensaje(String mensaje,Date fecha){
        this.mensaje=mensaje;
        this.fecha=fecha;
    }

    /**
     * retorna el contenido del mensaje
     * @return el contenido del mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * establece el contenido del mensaje
     * @param mensaje el contenido del mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * retorna la fecha en la que se publico el mensaje
     * @return la fecha en la que se publico el mensaje
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * establece la fecha en la que se publico el mensaje
     * @param fecha la fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
