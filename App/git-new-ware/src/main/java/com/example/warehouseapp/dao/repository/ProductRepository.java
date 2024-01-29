package com.example.warehouseapp.dao.repository;


import com.example.warehouseapp.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    ProductEntity findByQrCode(String base64);
}
