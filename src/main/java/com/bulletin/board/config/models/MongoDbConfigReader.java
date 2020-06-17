package com.bulletin.board.config.models;

public interface MongoDbConfigReader {
    String getConnectionUri();
    String getDatabaseName();
}
