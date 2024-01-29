package com.example.warehouseapp.model.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {
    Long id;
    String name;
    float salesPrice;
    float purchasePrice;
    long checkCount;
    long  productCount;
    String qrCode;
    Long categoryId;
}
