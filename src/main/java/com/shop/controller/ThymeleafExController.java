package com.shop.controller;

import com.shop.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafExController {

    @GetMapping("/ex01")
    public String thymeleafEx01(Model model) {
        model.addAttribute("data", "타임리프 예제");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value="/ex02")
    public String thymeleafEx02(Model model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemName("테스트상품1");
        itemDto.setItemDetail("상세 설명1");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value="/ex07")
    public String thymeleafEx07() {
        return "thymeleafEx/thymeleafEx07";
    }

}
