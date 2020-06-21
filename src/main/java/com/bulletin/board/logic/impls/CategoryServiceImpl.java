package com.bulletin.board.logic.impls;

import com.bulletin.board.api.models.request.SaveCategoryRequest;
import com.bulletin.board.api.models.response.CategoryResponse;
import com.bulletin.board.data.repositories.CategoryRepository;
import com.bulletin.board.logic.CategoryService;
import com.bulletin.board.mapping.CategoryMapper;
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
    private final CategoryMapper categoryMapper;

    @Override
    public Mono<CategoryResponse> create(SaveCategoryRequest request) {
        return Mono.fromSupplier(() -> {
            var category = categoryMapper.fromSaveRequest(keyGeneratorService.next(), request);
            var savedCategory = categoryRepository.save(category);

            return categoryMapper.fromEntity(savedCategory);
        });
    }

    @Override
    public Mono<CategoryResponse> update(String categoryId, SaveCategoryRequest request) {
        return Mono.fromSupplier(() -> {
            var categoryOpt = categoryRepository.findById(categoryId);
            if (categoryOpt.isPresent()) {
                var category = categoryMapper.fromSaveRequest(request, categoryOpt.get());
                var savedCategory = categoryRepository.save(category);

                return categoryMapper.fromEntity(savedCategory);
            } else {
                // throw not found exception here
                return null;
            }
        });
    }

    @Override
    public Mono<List<CategoryResponse>> getAll() {
        var categories = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::fromEntity)
                .collect(Collectors.toList());

        return Mono.just(categories);
    }

    @Override
    public Mono<CategoryResponse> getById(String categoryId) {
        return Mono.fromSupplier(() -> {
            return categoryRepository.findById(categoryId)
                    .map(categoryMapper::fromEntity)
                    .orElse(null); // throw not found exception here
        });
    }

    @Override
    public Mono<Void> delete(String categoryId) {
        return Mono.fromRunnable(() -> categoryRepository.deleteById(categoryId));
    }
}
