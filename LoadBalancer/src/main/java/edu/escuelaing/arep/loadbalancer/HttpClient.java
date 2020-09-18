package edu.escuelaing.arep.loadbalancer;

import okhttp3.*;

import java.io.IOException;


public class HttpClient {

    private OkHttpClient httpClient;
    private String baseUrl="http://192.168.99.100";
    private String[] ports={":8081",":8082",":8083"};
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private int serverNumber=0;



    public HttpClient() {
        httpClient=new OkHttpClient();
    }

    /**
     * envia una petición get al servidor correspondiente segun el round robin
     * @param path direccion del endpoint
     * @return la respuesta del servidor
     * @throws IOException
     */
    public String getMessages(String path) throws IOException {
        Request request = request = new Request.Builder()
                .url(baseUrl+ports[serverNumber]+path)
                .get()
                .build();
        System.out.println("sendig GET request to "+baseUrl+ports[serverNumber]+path);
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * envia una petición post al servidor correspondiente segun el round robin
     * @param message el cuerpo que se enviara en la peticion
     * @param path la direccion del endpoint
     * @return la respuesta del servidor
     * @throws IOException
     */
    public String postMessage( String message,String path) throws IOException {
        RequestBody body = RequestBody.create(message,JSON);
        Request request = new Request.Builder()
                .url(baseUrl+ports[serverNumber]+path)
                .post(body)
                .build();
        System.out.println("sendig POST request to "+baseUrl+ports[serverNumber]+path);
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }
    /**
     * Aumenta el contador del numero de servidor y realiza la operacio de modulo para mantenerlo en el rango de la lista
     */
    public void roundRobin(){
        this.serverNumber=(this.serverNumber+1)% ports.length;
    }
}
