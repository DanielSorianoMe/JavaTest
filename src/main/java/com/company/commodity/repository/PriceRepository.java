package com.company.commodity.repository;

import com.company.commodity.entity.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

    @Query("SELECT p FROM Price p JOIN p.brand b WHERE b.id = ?1 AND p.productId = ?2 AND (?3 BETWEEN p.startDate AND p.endDate) ORDER BY p.priority ASC")
    List<Price> findByBrandProductAndDate(Long brandId, Long productId, LocalDateTime appDate);

}
