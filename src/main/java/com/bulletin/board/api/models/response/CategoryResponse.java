package com.bulletin.board.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CategoryResponse {
    private String categoryId;
    private String name;
    private String imageUrl;
}
