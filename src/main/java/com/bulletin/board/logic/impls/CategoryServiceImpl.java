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
        var category = categoryMapper.fromSaveRequest(keyGeneratorService.next(), request);

        return categoryRepository.save(category)
                .map(categoryMapper::fromEntity);
    }

    @Override
    public Mono<CategoryResponse> update(String categoryId, SaveCategoryRequest request) {
        return categoryRepository.findById(categoryId)
                .flatMap(entity -> {
                    var category = categoryMapper.fromSaveRequest(request, entity);

                    return categoryRepository.save(category)
                            .map(categoryMapper::fromEntity);
                })
                .switchIfEmpty(Mono.error(new Exception())); // TODO: throw not found exception here instead
    }

    @Override
    public Mono<List<CategoryResponse>> getAll() {
        return categoryRepository.findAll()
                .map(categoryMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Mono<CategoryResponse> getById(String categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::fromEntity)
                .switchIfEmpty(Mono.error(new Exception())); // TODO: throw not found exception here instead
    }

    @Override
    public Mono<Void> delete(String categoryId) {
        return categoryRepository.deleteById(categoryId);
    }
}
