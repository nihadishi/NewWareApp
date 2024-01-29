package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.requestDto.CategoryRequestDto;
import com.example.warehouseapp.model.responseDto.CategoryResponseDto;
import com.example.warehouseapp.model.responseDto.SimpleMessageDto;
import com.example.warehouseapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    public final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public SimpleMessageDto<CategoryRequestDto> addCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.addCategory(categoryRequestDto);
        return new SimpleMessageDto<>(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value());
    }

    @GetMapping
    public SimpleMessageDto<List<CategoryResponseDto>> getCategories() {
        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), categoryService.getCategories());
    }

    @GetMapping("{id}")
    public SimpleMessageDto<CategoryResponseDto> getCategory(@PathVariable Long id) {
        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), categoryService.getCategory(id));
    }


    @PutMapping("{id}")
    public SimpleMessageDto<CategoryRequestDto> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto academicDegreeRequestDto) {
        categoryService.updateDepartment(academicDegreeRequestDto, id);
        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
    }

    @DeleteMapping("{id}")
    public SimpleMessageDto<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
    }
}
