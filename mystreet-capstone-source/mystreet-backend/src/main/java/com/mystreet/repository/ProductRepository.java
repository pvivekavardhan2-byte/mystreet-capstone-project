package com.mystreet.repository;

import com.mystreet.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("select p from Product p where (:brand is null or lower(p.brand)=lower(:brand)) and (:size is null or p.sizesCsv like concat('%', :size, '%'))")
    List<Product> search(@Param("brand") String brand, @Param("size") String size);
}
