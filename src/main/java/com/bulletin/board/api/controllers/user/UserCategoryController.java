package com.bulletin.board.api.controllers.user;

import com.bulletin.board.api.models.response.CategoryResponse;
import com.bulletin.board.logic.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class UserCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    private Mono<List<CategoryResponse>> getAll() {
        return categoryService.getAll();
    }
}
