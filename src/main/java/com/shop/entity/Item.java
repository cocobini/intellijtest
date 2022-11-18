package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id") // 컬럼 이름 별도 지정
    @GeneratedValue(strategy = GenerationType.AUTO) // PK 구현 설정
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50)
    private String itemName; // 상품명

    @Column(name = "price", nullable = false) // 컬럼 네임 지정 안 하면 필드명이 컬럼명
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고

    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상세 정보

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 판매 상태

    private LocalDateTime regTime; // 등록 시간

    private LocalDateTime updateTime; // 수정 시간
}
