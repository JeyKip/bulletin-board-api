package com.bulletin.board.services.impls;

import com.bulletin.board.services.KeyGeneratorService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KeyGeneratorServiceImpl implements KeyGeneratorService {
    @Override
    public String next() {
        return UUID.randomUUID().toString();
    }
}
