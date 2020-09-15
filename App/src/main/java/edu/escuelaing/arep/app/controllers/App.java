package edu.escuelaing.arep.app.controllers;

import com.google.gson.Gson;
import edu.escuelaing.arep.app.services.MessageService;
import edu.escuelaing.arep.app.services.impl.MessageServiceImpl;

import static spark.Spark.*;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    /**
     * encargado de inicializar la aplicacion de publicacion de mensajes, utiliza un servidor http para publicar
     * un metodo get usado para leer todos los mensajes publicados y un metodo post usado para publicar un nuevo mensaje,
     * para esto utiliza un objeto que implementa la clase MessageService
     * @param args
     */
    public static void main( String[] args )
    {
        MessageService messageService= new MessageServiceImpl();
        port(getPort());

        get("/mensajes",(request, response) -> {
            response.status(200);
            response.type("application/json");
            return new Gson().toJson(messageService.getAllMessages());
        });

        post("/mensajes",(request, response) -> {
            messageService.addMessage(request.body());
            return "";
        });


    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
