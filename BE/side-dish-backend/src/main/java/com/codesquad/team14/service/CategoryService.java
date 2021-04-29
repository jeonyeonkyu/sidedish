package com.codesquad.team14.service;

import com.codesquad.team14.domain.Category;
import com.codesquad.team14.domain.Item;
import com.codesquad.team14.dto.CategoryDTO;
import com.codesquad.team14.dto.DetailedItemDTO;
import com.codesquad.team14.dto.ItemDTO;
import com.codesquad.team14.dto.requestDTO.RequestItemDTO;
import com.codesquad.team14.exception.CategoryNotFoundException;
import com.codesquad.team14.exception.ItemNotFoundException;
import com.codesquad.team14.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> readAllBest() {
        return categoryRepository.findAllByBestIsTrue().stream()
                .map(CategoryDTO::from)
                .collect(Collectors.toList());
    }

    public CategoryDTO readOneBestCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return CategoryDTO.from(category);
    }

    public List<ItemDTO> readAllByCategoryName(String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName).orElseThrow(CategoryNotFoundException::new);
        return category.getItems().values().stream().map(ItemDTO::from).collect(Collectors.toList());
    }

    public DetailedItemDTO readDetailed(Long itemId) {
        for (Category category : categoryRepository.findAll()) {
            if (category.getItems().containsKey(itemId)) {
                return DetailedItemDTO.from(category.getItem(itemId).orElseThrow(ItemNotFoundException::new));
            }
        }

        return null;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name).orElseThrow(CategoryNotFoundException::new);
    }

    public void insertData(Category category, List<RequestItemDTO> requestItemDtoList) {
        for (RequestItemDTO itemDto : requestItemDtoList) {
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
