package com.example.warehouseapp.mapper;

import com.example.warehouseapp.dao.entity.CategoryEntity;
import com.example.warehouseapp.model.requestDto.CategoryRequestDto;
import com.example.warehouseapp.model.responseDto.CategoryResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity dtoToEntity(CategoryRequestDto categoryRequestDto);

    List<CategoryEntity> dtoToEntities(List<CategoryRequestDto> categoryRequestDtoList);

    CategoryResponseDto entityToDto(CategoryEntity categoryEntity);
    List<CategoryResponseDto> entityToDtos(List<CategoryEntity> reservationEntities);

}
