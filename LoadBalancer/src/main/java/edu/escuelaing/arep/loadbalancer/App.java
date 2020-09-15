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
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
