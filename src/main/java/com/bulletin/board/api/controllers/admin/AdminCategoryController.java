package com.bulletin.board.api.controllers.admin;

import com.bulletin.board.api.models.request.SaveCategoryRequest;
import com.bulletin.board.api.models.response.CategoryResponse;
import com.bulletin.board.logic.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public Mono<CategoryResponse> create(@Validated @RequestBody SaveCategoryRequest request) {
        return categoryService.create(request);
    }

    @PutMapping("/{categoryId}")
    public Mono<CategoryResponse> update(@PathVariable String categoryId, @Validated @RequestBody SaveCategoryRequest request) {
        return categoryService.update(categoryId, request);
    }

    @GetMapping
    private Mono<List<CategoryResponse>> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    public Mono<CategoryResponse> getById(@PathVariable String categoryId) {
        return categoryService.getById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public Mono<Void> delete(@PathVariable String categoryId) {
        return categoryService.delete(categoryId);
    }
}
