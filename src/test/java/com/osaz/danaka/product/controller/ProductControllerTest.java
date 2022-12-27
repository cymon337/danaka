package com.osaz.danaka.product.controller;

import com.osaz.danaka.config.DanakaApplication;
import com.osaz.danaka.config.MybatisConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {DanakaApplication.class, MybatisConfig.class})
class ProductControllerTest {

    @Autowired
    private ProductController productController;
    private MockMvc mockMvc;

    @Test
    public void testInit(){

        assertNotNull(productController);
        assertNotNull(mockMvc);
    }

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void 카테고리별_상품_조회용_컨트롤러_테스트_동작_확인() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/product/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }
}