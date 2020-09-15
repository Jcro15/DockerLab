package edu.escuelaing.arep.app.persistence;

import edu.escuelaing.arep.app.model.Mensaje;

import java.util.ArrayList;

/**
 * Abstracción utilizada para conectar con la capa de persistencia, las implementaciones de esta interfaz
 * deben permitir leer todos los mensajes y escribir uno nuevo
 */
public interface MessagePersistence {
    /**
     * Almacena en persistencia un mensaje
     * @param mensaje el mensaje que se va a almacenar
     */
    void writeMessage(Mensaje mensaje);

    /**
     * retorna todos los mensajes almacenados hasta el momento
     * @return un arreglo con los mensajes almacenados
     */
    ArrayList<Mensaje> loadMessages();
}
