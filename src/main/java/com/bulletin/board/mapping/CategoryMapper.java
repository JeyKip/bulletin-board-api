package com.bulletin.board.mapping;

import com.bulletin.board.api.models.request.SaveCategoryRequest;
import com.bulletin.board.api.models.response.CategoryResponse;
import com.bulletin.board.data.entities.CategoryEntity;
import org.mapstruct.*;

@Mapper
public interface CategoryMapper {
    CategoryEntity fromSaveRequest(String categoryId, SaveCategoryRequest request);
    CategoryResponse fromEntity(CategoryEntity entity);

    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "imageUrl", source = "request.imageUrl")
    CategoryEntity fromSaveRequest(SaveCategoryRequest request, CategoryEntity entity);
}
