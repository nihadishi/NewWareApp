package com.example.warehouseapp.mapper;

import com.example.warehouseapp.dao.entity.CategoryEntity;
import com.example.warehouseapp.dao.entity.ProductEntity;
import com.example.warehouseapp.model.requestDto.CategoryRequestDto;
import com.example.warehouseapp.model.requestDto.ProductRequestDto;
import com.example.warehouseapp.model.responseDto.CategoryResponseDto;
import com.example.warehouseapp.model.responseDto.ProductResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity dtoToEntity(ProductRequestDto productRequestDto);

    List<ProductEntity> dtoToEntities(List<ProductRequestDto> productRequestDtoList);

    ProductResponseDto entityToDto(ProductEntity productEntity);
    List<ProductResponseDto> entityToDtos(List<ProductEntity> reservationEntities);


}
