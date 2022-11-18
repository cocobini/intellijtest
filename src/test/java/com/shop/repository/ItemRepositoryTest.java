package com.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createdItemTest() {
        Item item = new Item();
        item.setItemName("test item");
        item.setItemDetail("test info...");
        item.setPrice(10000);
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    @Test
    @DisplayName("상품 목록 테스트")
    public void createdItemListTest() {
        for (int i=0; i<10; i++) {
            Item item = new Item();
            item.setItemName("test item0" +i);
            item.setItemDetail("test info...");
            item.setPrice(10000);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        } //end for

        // List<Item> itemList =  itemRepository.findByItemName("test item01");
        // List<Item> itemList =  itemRepository.findByItemNameOrItemDetail("test item04", "test info...");
        List<Item> itemList = itemRepository.findByItemDetail("test info...");

        for(Item i : itemList) {
            System.out.println(i.toString());
        }
    }


    @Test
    @DisplayName("Querydsl 조회 테스트")
    public void querydslTest(){
        for (int i=0; i<5; i++) {
            Item item = new Item();
            item.setItemName("test item0" +i);
            item.setItemDetail("test info...");
            item.setPrice(10000);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        } //end for

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QItem qItem = QItem.item;

        // JPA 쿼리
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"test info"+"%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch();

        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }


    @Test
    @DisplayName("페이징")
    public void pagingTest(){
        for (int i=0; i<5; i++) {
            Item item = new Item();
            item.setItemName("test item0" +i);
            item.setItemDetail("test info...");
            item.setPrice(10000);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        } //end for
        for (int i=5; i<10; i++) {
            Item item = new Item();
            item.setItemName("test item0" +i);
            item.setItemDetail("test info...");
            item.setPrice(10000);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        } //end for
        for (int i=10; i<15; i++) {
            Item item = new Item();
            item.setItemName("test item0" +i);
            item.setItemDetail("test info...");
            item.setPrice(10000);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        } //end for
        for (int i=15; i<20; i++) {
            Item item = new Item();
            item.setItemName("test item0" +i);
            item.setItemDetail("test info...");
            item.setPrice(10000);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        } //end for

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;

        // 조건절에 사용할 변수들 선언
        String itemDetail ="test info...";
        int price = 10000;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
        // booleanBuilder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);
        Page<Item> itemPagingResult =
                itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total e: " + itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem: resultItemList) {
            System.out.println(resultItem.toString());
        }



    }


}