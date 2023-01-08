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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("categoryCode", "REEL");
        params.add("orderCondition", "highPrice");

        mockMvc.perform(MockMvcRequestBuilders.get("/product/list2").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/list2"))
                .andDo(MockMvcResultHandlers.print());
    }
}