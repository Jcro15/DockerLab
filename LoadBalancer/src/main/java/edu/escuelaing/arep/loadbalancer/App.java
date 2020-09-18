package edu.escuelaing.arep.loadbalancer;

import com.google.gson.Gson;

import static spark.Spark.*;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    /**
     * Controlador que provee 2 endpoints Get /mensajes y Post /mensajes, cada uno de estos redirige la petición hacia
     * alguno de los nodos basandose en el algoritmo de round robin
     * @param args
     */
    public static void main( String[] args )
    {
        port(getPort());
        staticFileLocation("/static");
        HttpClient client=new HttpClient();
        get("/", (req, res) -> {
            res.redirect("index.html");
            return "";
        });

        get("/mensajes",(req, res) -> {
            res.status(200);
            res.type("application/json");
            String response=client.getMessages("/mensajes");

            client.roundRobin();
            return response;
        });

        post("/mensajes",(request, response) -> {
            client.postMessage(request.body(),"/mensajes");
            client.roundRobin();
            return "";
        });


    }
    /**
     * retorna el puerto definido por el entorno, si no hay puerto definido retorna 4567
     * @return el puerto definido por el entorno, si no hay puerto definido retorna 4567
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
