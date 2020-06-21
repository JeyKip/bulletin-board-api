package com.bulletin.board.data.repositories;

import com.bulletin.board.data.entities.CategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByName(String name);
}
