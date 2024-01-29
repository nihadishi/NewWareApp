package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.requestDto.CategoryRequestDto;
import com.example.warehouseapp.model.requestDto.ProductRequestDto;
import com.example.warehouseapp.model.responseDto.CategoryResponseDto;
import com.example.warehouseapp.model.responseDto.ProductResponseDto;
import com.example.warehouseapp.model.responseDto.SimpleMessageDto;
import com.example.warehouseapp.service.CategoryService;
import com.example.warehouseapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public SimpleMessageDto<ProductRequestDto> addProduct(@Valid @RequestBody ProductRequestDto categoryRequestDto) {
        productService.addProduct(categoryRequestDto);
        return new SimpleMessageDto<>(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value());
    }

    @GetMapping
    public SimpleMessageDto<List<ProductResponseDto>> getProducts() {
        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), productService.getProducts());
    }

    @GetMapping("{id}")
    public SimpleMessageDto<ProductResponseDto> getProduct(@PathVariable Long id) {
        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), productService.getProduct(id));
    }

    @PostMapping("/{base64}")
    public SimpleMessageDto<String> checkCount(@PathVariable String base64) {
        productService.checkCount(base64);
        return new SimpleMessageDto<>(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value());

    }

    @PutMapping("{id}")
    public SimpleMessageDto<ProductRequestDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto productRequestDto) {
        productService.updateProduct(productRequestDto, id);
        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
    }


    @DeleteMapping("{id}")
    public SimpleMessageDto<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new SimpleMessageDto<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
    }
}
