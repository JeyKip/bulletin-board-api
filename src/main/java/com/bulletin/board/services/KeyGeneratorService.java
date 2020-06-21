package com.bulletin.board.services;

public interface KeyGeneratorService extends BaseGeneratorService<String> {
    String next();
}
