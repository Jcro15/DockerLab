package edu.escuelaing.arep.app.services.impl;

import edu.escuelaing.arep.app.model.Mensaje;
import edu.escuelaing.arep.app.persistence.MessagePersistence;
import edu.escuelaing.arep.app.persistence.impl.DBConnection;
import edu.escuelaing.arep.app.services.MessageService;

import java.util.ArrayList;

/**
 * Implementacion se servicios de mensaje que se apoya en una persistencia de mongo para realizar las consultas necesarias
 */
public class MessageServiceImpl implements MessageService {

    MessagePersistence messagePersistence= new DBConnection();

    /**
     * añade un mensaje y utiliza la persistencia para almacenarlo
     * @param mensaje el mensaje que se va a añadir
     */
    @Override
    public void addMessage(String mensaje) {
        messagePersistence.writeMessage(new Mensaje(mensaje));
    }

    /**
     * utiliza el componente de persistencia para leer los mensajes
     * @return lista con los mensajes encontrados
     */
    @Override
    public ArrayList<Mensaje> getAllMessages() {
        return messagePersistence.loadMessages();
    }
}
