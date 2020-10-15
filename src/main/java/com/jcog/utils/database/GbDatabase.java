package com.jcog.utils.database;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collections;

public class GbDatabase {
    private final static String DEFAULT_HOST = "localhost";
    private final static int DEFAULT_PORT = 27017;

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final boolean writePermission;

    public GbDatabase(String dbName, String user, String password, boolean writePermission) {
        this(DEFAULT_HOST, dbName, user, password, writePermission);
    }

    public GbDatabase(String host, String dbName, String user, String password, boolean writePermission) {
        this.writePermission = writePermission;

        MongoCredential credential = MongoCredential.createCredential(
                user,
                dbName,
                password.toCharArray()
        );
        mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Collections.singletonList(
                                        new ServerAddress(host, DEFAULT_PORT)
                                )))
                        .credential(credential)
                        .build()
        );
        mongoDatabase = mongoClient.getDatabase(dbName);
    }

    public void close() {
        mongoClient.close();
    }

    public MongoCollection<Document> getCollection(String collection) {
        return mongoDatabase.getCollection(collection);
    }

    public boolean hasWritePermission() {
        return writePermission;
    }
}
