package com.codesquad.team14.dto;

import com.codesquad.team14.domain.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryDTO {

    @JsonProperty("category_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("items")
    private Set<ItemDTO> items;

    private CategoryDTO(Long id, String name, Set<ItemDTO> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public static CategoryDTO from(Category category) {
        Set<ItemDTO> itemDtos = category.getItems().values().stream().map(ItemDTO::from).collect(Collectors.toSet());
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                itemDtos
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<ItemDTO> getItems() {
        return items;
    }
}
