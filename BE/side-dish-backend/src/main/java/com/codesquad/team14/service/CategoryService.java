package com.codesquad.team14.service;

import com.codesquad.team14.domain.Category;
import com.codesquad.team14.domain.Item;
import com.codesquad.team14.dto.CategoryDTO;
import com.codesquad.team14.dto.DetailedItemDTO;
import com.codesquad.team14.dto.ItemDTO;
import com.codesquad.team14.dto.requestDTO.RequestBestItemDTO;
import com.codesquad.team14.dto.requestDTO.RequestItemDTO;
import com.codesquad.team14.exception.CategoryNotBestException;
import com.codesquad.team14.exception.CategoryNotFoundException;
import com.codesquad.team14.exception.ItemNotFoundException;
import com.codesquad.team14.repository.CategoryRepository;
import com.codesquad.team14.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final int BEST_CATEGORY_ID_MIN = 4;
    private static final int BEST_CATEGORY_ID_MAX = 9;

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    public CategoryService(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    public List<CategoryDTO> readAllBest() {
        return categoryRepository.findAllByBestIsTrue().stream()
                .map(CategoryDTO::from)
                .collect(Collectors.toList());
    }

    public CategoryDTO readOneBestCategory(Long categoryId) {
        if (!(categoryId >= BEST_CATEGORY_ID_MIN && categoryId <= BEST_CATEGORY_ID_MAX)) {
            throw new CategoryNotBestException();
        }

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
            Item item = Item.of(itemDto.getTitle(),
                    itemDto.getDescription(),
                    itemDto.getNormalPrice(),
                    itemDto.getSalePrice(),
                    itemDto.getBadgeInString(),
                    itemDto.getDeliveryTypeInString(),
                    itemDto.getImages(),
                    category.getId());
            category.addItem(itemRepository.save(item));
            System.out.println(item.toString());
        }

        save(category);
    }

    public void insertBestData(List<RequestBestItemDTO> bestCategoryItem) {
        for (RequestBestItemDTO categoryDTO : bestCategoryItem) {
            Category category = categoryRepository.findById(categoryDTO.getCategory_id()).orElseThrow(CategoryNotFoundException::new);
            insertData(category, categoryDTO.getItems());
        }
    }
}
