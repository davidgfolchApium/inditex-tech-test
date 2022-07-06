package com.inditex.techtest.repo;

import com.inditex.techtest.repo.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductPriceRepository extends CrudRepository<ProductPriceEntity, Long> {

    @Query("""
            from ProductPriceEntity where startDate<=:date and endDate>=:date and brandId=:brandId and productId=:id
            order by priority desc""")
    List<ProductPriceEntity> getForDate(long brandId, long id, LocalDateTime date);

}
