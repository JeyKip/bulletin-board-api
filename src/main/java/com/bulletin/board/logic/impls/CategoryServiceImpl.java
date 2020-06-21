package com.bulletin.board.logic.impls;

import com.bulletin.board.api.models.request.SaveCategoryRequest;
import com.bulletin.board.api.models.response.CategoryResponse;
import com.bulletin.board.data.entities.CategoryEntity;
import com.bulletin.board.data.repositories.CategoryRepository;
import com.bulletin.board.logic.CategoryService;
import com.bulletin.board.services.KeyGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final KeyGeneratorService keyGeneratorService;
    private final CategoryRepository categoryRepository;

    @Override
    public Mono<CategoryResponse> create(SaveCategoryRequest request) {
        var category = new CategoryEntity();
        category.setCategoryId(keyGeneratorService.next());
        category.setName(request.getName());
        category.setImageUrl(request.getImageUrl());

        var savedCategory = categoryRepository.save(category);

        return Mono.just(CategoryResponse.builder()
                .categoryId(savedCategory.getCategoryId())
                .name(savedCategory.getName())
                .imageUrl(savedCategory.getImageUrl())
                .build());
    }

    @Override
    public Mono<CategoryResponse> update(String categoryId, SaveCategoryRequest request) {
        var categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isPresent()) {
            var category = categoryOpt.get();
            category.setName(request.getName());
            category.setImageUrl(request.getImageUrl());

            var savedCategory = categoryRepository.save(category);

            return Mono.just(CategoryResponse.builder()
                    .categoryId(savedCategory.getCategoryId())
                    .name(savedCategory.getName())
                    .imageUrl(savedCategory.getImageUrl())
                    .build());
        } else {
            // throw not found exception here
            return Mono.empty();
        }
    }

    @Override
    public Mono<List<CategoryResponse>> getAll() {
        var categories = categoryRepository.findAll()
                .stream()
                .map(category -> CategoryResponse.builder()
                        .categoryId(category.getCategoryId())
                        .name(category.getName())
                        .imageUrl(category.getImageUrl())
                        .build())
                .collect(Collectors.toList());

        return Mono.just(categories);
    }

    @Override
    public Mono<CategoryResponse> getById(String categoryId) {
        var categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isPresent()) {
            var category = categoryOpt.get();

            return Mono.just(CategoryResponse.builder()
                    .categoryId(category.getCategoryId())
                    .name(category.getName())
                    .imageUrl(category.getImageUrl())
                    .build());
        } else {
            // throw not found exception here
            return Mono.empty();
        }
    }

    @Override
    public Mono<Void> delete(String categoryId) {
        return Mono.fromRunnable(() -> categoryRepository.deleteById(categoryId));
    }
}
