package com.example.warehouseapp.model.requestDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    @NotBlank(message = "Name can't be null or empty")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    String name;

    @NotNull(message = "Sales price can;t be null")
    float salesPrice;

    @NotNull(message = "Purchase price can;t be null")
    float purchasePrice;

    @NotNull(message = "Product count can;t be null")
    @Min(value = 0, message = "Product count must be greater than or equal to 0")
    long productCount;

    Long categoryId;
}
