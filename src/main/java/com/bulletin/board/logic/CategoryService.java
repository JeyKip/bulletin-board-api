package com.bulletin.board.logic;

import com.bulletin.board.api.models.request.SaveCategoryRequest;
import com.bulletin.board.api.models.response.CategoryResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CategoryService {
    Mono<CategoryResponse> create(SaveCategoryRequest request);
    Mono<CategoryResponse> update(String categoryId, SaveCategoryRequest request);
    Mono<List<CategoryResponse>> getAll();
    Mono<CategoryResponse> getById(String categoryId);
    Mono<Void> delete(String categoryId);
}
