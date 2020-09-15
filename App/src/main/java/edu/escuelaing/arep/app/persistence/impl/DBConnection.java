package edu.escuelaing.arep.app.persistence.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.escuelaing.arep.app.model.Mensaje;
import edu.escuelaing.arep.app.persistence.MessagePersistence;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

/**
 * Implementación de la interfaz de persistencia de mensajes que utiliza una base de datos MongoDB para persistir los mensajes
 */
public class DBConnection implements MessagePersistence {
    public MongoClientURI uri;
    public MongoClient mongoClient;

    public DBConnection() {
        uri = new MongoClientURI(
                "mongodb://Camilo:password@ip-172-31-62-68.ec2.internal:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=Arep&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
        mongoClient = new MongoClient(uri);
    }

    /**
     * almacena el mensaje en una base de datos mongoDB
     * @param mensaje el mensaje que se va a almacenar
     */
    @Override
    public void writeMessage(Mensaje mensaje) {
        MongoDatabase database = mongoClient.getDatabase("Arep");
        MongoCollection<Document> collection =database.getCollection("Mensajes");
        Document document=new Document();
        document.put("mensaje",mensaje.getMensaje());
        document.put("fecha",mensaje.getFecha());
        collection.insertOne(document);
    }

    /**
     * retorna la lista de mensajes almacenados en la base de datos MongoDB
     * @return la lista de mensajes almacenados en la base de datos MongoDB
     */
    @Override
    public ArrayList<Mensaje> loadMessages() {
        ArrayList<Mensaje> mensajes=new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("Arep");
        MongoCollection<Document> collection =database.getCollection("Mensajes");
        FindIterable fit = collection.find();
        ArrayList<Document> docs = new ArrayList<Document>();
        StringBuilder results = new StringBuilder();
        fit.into(docs);

        for (Document document:docs) {
            String mensaje= (String) document.get("mensaje");
            Date date=(Date)document.get("fecha");
            mensajes.add(new Mensaje(mensaje,date));
        }


        return mensajes;
    }
}
