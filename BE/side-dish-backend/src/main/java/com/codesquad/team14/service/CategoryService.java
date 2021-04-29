package com.codesquad.team14.service;

import com.codesquad.team14.domain.Category;
import com.codesquad.team14.domain.Item;
import com.codesquad.team14.dto.CategoryDto;
import com.codesquad.team14.dto.DetailedItemDto;
import com.codesquad.team14.dto.ItemDto;
import com.codesquad.team14.dto.requestDto.RequestItemDto;
import com.codesquad.team14.exception.CategoryNotFoundException;
import com.codesquad.team14.exception.ItemNotFoundException;
import com.codesquad.team14.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> readAllBest() {
        List<Category> bestCategories = categoryRepository.findAllByBestIsTrue();
        return bestCategories.stream()
                .map(CategoryDto::from)
                .collect(Collectors.toList());
    }

    public CategoryDto readOneBestCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return CategoryDto.from(category);
    }

    public List<ItemDto> readAllByCategoryName(String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName).orElseThrow(CategoryNotFoundException::new);
        return category.getItems().values().stream().map(ItemDto::from).collect(Collectors.toList());
    }

    public DetailedItemDto readDetailedItem(String categoryName, Long itemId) {
        Category category = categoryRepository.findCategoryByName(categoryName).orElseThrow(CategoryNotFoundException::new);
        if (!category.getItems().containsKey(itemId)) {
            throw new ItemNotFoundException();
        }

        return DetailedItemDto.from(category.getItems().get(itemId));
    }

    public DetailedItemDto readDetailed(Long itemId) {
        for (Category category : categoryRepository.findAll()) {
            if (category.getItems().containsKey(itemId)) {
                return DetailedItemDto.from(category.getItems().get(itemId));
            }
        }

        throw new ItemNotFoundException();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name).orElseThrow(CategoryNotFoundException::new);
    }

    public void insertData(Category category, List<RequestItemDto> requestItemDtoList) {
        for (RequestItemDto itemDto : requestItemDtoList) {
            if (itemDto.getN_price().isEmpty()) {
                itemDto.setN_price("0");
            }
            Item item = Item.of(itemDto.getTitle(),
                    itemDto.getDescription(),
                    itemDto.getNormalPrice(),
                    itemDto.getSalePrice(),
                    itemDto.getBadgeInString(),
                    itemDto.getDeliveryTypeInString(),
                    itemDto.getImages(),
                    category.getId());
            category.addItem(item);
        }

        save(category);
    }
}
