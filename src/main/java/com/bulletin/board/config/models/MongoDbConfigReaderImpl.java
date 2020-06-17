package com.bulletin.board.config.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MongoDbConfigReaderImpl implements MongoDbConfigReader {
    @NotBlank
    public String connectionUri;

    @NotBlank
    public String databaseName;
}
