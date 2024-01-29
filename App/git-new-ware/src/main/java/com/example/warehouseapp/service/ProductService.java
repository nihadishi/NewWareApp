package com.example.warehouseapp.service;

import com.example.warehouseapp.dao.entity.ProductEntity;
import com.example.warehouseapp.dao.repository.ProductRepository;
import com.example.warehouseapp.exception.NotFoundException;
import com.example.warehouseapp.mapper.ProductMapper;
import com.example.warehouseapp.model.requestDto.ProductRequestDto;
import com.example.warehouseapp.model.responseDto.ProductResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public void addProduct(ProductRequestDto productRequestDto) {
        log.info("ActionaLog.start  product add method ");
        var id=productRequestDto.getCategoryId();
        String base64 = Base64.getEncoder().encodeToString(String.valueOf(id).getBytes());

        ProductEntity productEntity = productMapper.dtoToEntity(productRequestDto);
        productEntity.setQrCode(base64);
        productRepository.save(productEntity);

        log.info("ActionLog.end  product add method ");
    }

    public List<ProductResponseDto> getProducts() {
        log.info("ActionLog.start  product get method ");
        var result = productMapper.entityToDtos(productRepository.findAll());
        log.info("ActionLog.end  product get method ");

        return result;

    }

    public ProductResponseDto getProduct(Long id) {
        log.info("ActionLog.start  product get method with id: {}", id);

        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("PRODUCT_NOT_FOUND");
        });

        log.info("ActionLog.end product get method with id: {}", id);

        return productMapper.entityToDto(productEntity);
    }


    public void checkCount(String base64){
        log.info("ActionLog.start  checkOut method with id: {}",base64);

        ProductEntity entity=productRepository.findByQrCode(base64);
        var count=entity.getCheckCount()+1;
        entity.setCheckCount( count);
        productRepository.save(entity);
        log.info("ActionLog.end  checkOut method with id: {}",base64);

    }

    public void updateProduct(ProductRequestDto productRequestDto, Long id) {
        log.info("ActionLog.start  product update method with id:", id);

        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("PRODUCT_NOT_FOUND");
        });
        BeanUtils.copyProperties(productRequestDto, productEntity, "id");
        productRepository.save(productEntity);

        log.info("ActionLog.end  product update method with id:", id);

    }

    public void deleteProduct(Long id) {
        log.info("ActionLog.start  product delete method with id: {}", id);

        if (!productRepository.existsById(id)) {
            throw new NotFoundException("PRODUCT_NOT_FOUND");
        }
        productRepository.deleteById(id);
        log.info("ActionLog.end  product delete method with id: {}", id);

    }
}
