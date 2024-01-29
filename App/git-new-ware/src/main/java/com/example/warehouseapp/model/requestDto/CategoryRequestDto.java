package com.example.warehouseapp.model.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryRequestDto {
    @NotBlank(message = "Name may not be null or empty")
    @Size(min = 2 , message = "Name must be at least 2 characters long")
    String name;
}
