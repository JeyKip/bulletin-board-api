package com.bulletin.board.data.repositories;

import com.bulletin.board.data.entities.CategoryEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveMongoRepository<CategoryEntity, String> {
    Mono<CategoryEntity> findByName(String name);
}
