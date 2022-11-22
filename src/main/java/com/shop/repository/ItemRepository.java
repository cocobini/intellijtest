package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>,
                                        QuerydslPredicateExecutor<Item>,
                                        ItemRepositoryCustom {

    List<Item> findByItemName(String itemName);

    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    // 컬럼명을 사용하지 않는 점 유의! '객체'를 대상으로 하는 것
    @Query("SELECT i FROM Item i WHERE i.itemDetail LIKE "
            + "%:itemDetail ORDER BY i.price DESC")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

}