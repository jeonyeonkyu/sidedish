package com.codesquad.team14.controller;

import com.codesquad.team14.domain.Category;
import com.codesquad.team14.dto.CategoryDTO;
import com.codesquad.team14.dto.DetailedItemDTO;
import com.codesquad.team14.dto.ItemDTO;
import com.codesquad.team14.dto.requestDTO.RequestBestItemDTO;
import com.codesquad.team14.dto.requestDTO.RequestItemDTO;
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
    public List<CategoryDTO> readAllBest() {
        return categoryService.readAllBest();
    }

    @GetMapping("/best/{categoryId}")
    public CategoryDTO readOneBestCategory(@PathVariable Long categoryId) {
        return categoryService.readOneBestCategory(categoryId);
    }

    @GetMapping("/{categoryName}")
    public List<ItemDTO> getAllFromCategory(@PathVariable String categoryName) {
        return categoryService.readAllByCategoryName(categoryName);
    }

    @GetMapping("/detail/{itemId}")
    public DetailedItemDTO readDetailed(@PathVariable Long itemId) {
        return categoryService.readDetailed(itemId);
    }

    @PostMapping("/main")
    public void addMain(@RequestBody List<RequestItemDTO> mainItems) {
        Category category = categoryService.findCategoryByName("MAIN");
        categoryService.insertData(category, mainItems);
    }

    @PostMapping("/soup")
    public void addSoup(@RequestBody List<RequestItemDTO> soupItems) {
        Category category = categoryService.findCategoryByName("SOUP");
        categoryService.insertData(category, soupItems);
    }

    @PostMapping("/side")
    public void addSide(@RequestBody List<RequestItemDTO> sideItems) {
        Category category = categoryService.findCategoryByName("side");
        categoryService.insertData(category, sideItems);
    }

    @PostMapping("/best")
    public void addBest(@RequestBody List<RequestBestItemDTO> bests) {
        categoryService.insertBestData(bests);
    }
}
