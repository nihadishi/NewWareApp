package com.example.warehouseapp.service;

import com.example.warehouseapp.dao.entity.CategoryEntity;
import com.example.warehouseapp.dao.repository.CategoryRepository;
import com.example.warehouseapp.exception.NotFoundException;
import com.example.warehouseapp.mapper.CategoryMapper;
import com.example.warehouseapp.model.requestDto.CategoryRequestDto;
import com.example.warehouseapp.model.responseDto.CategoryResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public void addCategory(CategoryRequestDto categoryRequestDto) {
        log.info("ActionLog.start  category add method -->");

        CategoryEntity categoryEntity = categoryMapper.dtoToEntity(categoryRequestDto);
        categoryRepository.save(categoryEntity);

        log.info("ActionLog.end  category add method --> ");
    }

    public List<CategoryResponseDto> getCategories() {
        log.info("ActionLog.start  category get method ->");
        var result = categoryMapper.entityToDtos(categoryRepository.findAll());
        log.info("ActionLog.end  category get method ->");

        return result;

    }

    public CategoryResponseDto getCategory(Long id) {
        log.info("ActionLog.start  category get method with id: {}", id);

        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("CATEGORY_NOT_FOUND");
        });

        log.info("ActionLog.end category get method with id: {}", id);

        return categoryMapper.entityToDto(categoryEntity);
    }

    public void updateDepartment(CategoryRequestDto categoryRequestDto, Long id) {
        log.info("ActionLog.start  category update method with id: {}", id);

        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("CATEGORY_NOT_FOUND");
        });
        BeanUtils.copyProperties(categoryRequestDto, categoryEntity, "id");
        categoryRepository.save(categoryEntity);

        log.info("ActionLog.end  category update method with id: {}", id);

    }

    public void deleteCategory(Long id) {
        log.info("ActionLog.start  category delete method with id: {}", id);

        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("CATEGORY_NOT_FOUND");
        }
        categoryRepository.deleteById(id);
        log.info("ActionLog.end  category delete method with id: {}", id);

    }
}
