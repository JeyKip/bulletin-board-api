package com.bulletin.board.data.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
public class CategoryEntity {
    @Id
    private String categoryId;

    @Indexed(unique = true)
    private String name;
    private String imageUrl;
}
