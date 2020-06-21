package com.bulletin.board.api.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SaveCategoryRequest {
    @NotBlank
    @Size(min = 2, max = 64)
    private String name;

    @NotBlank
    private String imageUrl;
}
