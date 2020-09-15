package edu.escuelaing.arep.app.services;

import edu.escuelaing.arep.app.model.Mensaje;

import java.util.ArrayList;

/**
 * Interfaz utilizada para conectar con la capa de servicios de la aplicacion, debe proporcionar formas de leer y escribir mensajes
 */
public interface MessageService {
    /**
     * permite añadir un nuevo mensaje
     * @param mensaje el mensaje que se va a añadir
     */
    public void addMessage(String mensaje);

    /**
     * permite leer los mensajes publicados
     * @return arreglo con los mensajes
     */
    public ArrayList<Mensaje> getAllMessages();
}
