package com.codesquad.team14.controller;

import com.codesquad.team14.domain.Category;
import com.codesquad.team14.dto.CategoryDto;
import com.codesquad.team14.dto.DetailedItemDto;
import com.codesquad.team14.dto.ItemDto;
import com.codesquad.team14.dto.requestDto.RequestItemDto;
import com.codesquad.team14.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banchan")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/best")
    public List<CategoryDto> readAllBest() {
        return categoryService.readAllBest();
    }

    @GetMapping("/best/{categoryId}")
    public CategoryDto readOneBestCategory(@PathVariable Long categoryId) {
        return categoryService.readOneBestCategory(categoryId);
    }

    @GetMapping("/{categoryName}")
    public List<ItemDto> getAllFromCategory(@PathVariable String categoryName) {
        return categoryService.readAllByCategoryName(categoryName);
    }

    @GetMapping("/detail/{itemId}")
    public DetailedItemDto readDetailed(@PathVariable Long itemId) {
        return categoryService.readDetailed(itemId);
    }

    @PostMapping("/main")
    public void addMain(@RequestBody List<RequestItemDto> mainItems) {
        Category category = categoryService.findCategoryByName("main");
        categoryService.insertData(category, mainItems);
    }

    @PostMapping("/soup")
    public void addSoup(@RequestBody List<RequestItemDto> soupItems) {
        Category category = categoryService.findCategoryByName("soup");
        categoryService.insertData(category, soupItems);
    }

    @PostMapping("/side")
    public void addSide(@RequestBody List<RequestItemDto> sideItems) {
        Category category = categoryService.findCategoryByName("side");
        categoryService.insertData(category, sideItems);
    }
}
