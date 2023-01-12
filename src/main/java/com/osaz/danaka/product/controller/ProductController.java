package com.osaz.danaka.product.controller;

import com.osaz.danaka.common.Pagenation;
import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.product.model.dto.OrderDTO;
import com.osaz.danaka.product.model.dto.ProductCartDTO;
import com.osaz.danaka.product.model.dto.ProductDTO;
import com.osaz.danaka.product.model.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // # update : 2023-01-03(최종수정)
    // # title : 상품 목록
    // # author : 오승재
    // # description : 카테고리 별 상품 조회, 페이징 처리, 정렬
    @GetMapping("/list2")
    public ModelAndView selectByCategory(HttpServletRequest request, ModelAndView mv){

        /* 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         * */
        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        /* 0보다 작은 숫자값을 입력해도 1페이지를 보여준다 */
        if(pageNo <= 0) {
            pageNo = 1;
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");
        String categoryCode = request.getParameter("categoryCode");
        String orderCondition = request.getParameter("orderCondition");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);
        searchMap.put("categoryCode", categoryCode);
        searchMap.put("orderCondition", orderCondition);

        /* 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         * */
        int totalCount = productService.selectTotalCount(searchMap);

        log.info("총 상품 수 = {}", totalCount);

        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 10;		//얘도 파라미터로 전달받아도 된다.
        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, categoryCode, orderCondition);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, categoryCode, orderCondition);
        }

        log.info("검색 조건 = {}", selectCriteria);

        /* 조회해온다 */
        List<ProductDTO> productList = productService.selectListByCategory(selectCriteria);
        log.info("상품 리스트 = {}" + productList);

        mv.addObject("productList", productList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.setViewName("product/list2");

        return mv;
    }

    // # update : 2023-01-08(최종수정)
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 상세페이지용 상품 정보 가져오기
    @GetMapping("/item2")
    public ModelAndView selectOneProduct(HttpServletRequest request, ModelAndView mv){

        String productNo = request.getParameter("productNo");

        // 해당하는 상품 DTO 조회
        ProductDTO product = productService.selectOneProduct(productNo);
        // 해당하는 상품에 속한 옵션 조회
        List<ProductDTO> optionList = productService.selectOptionList(product.getProductName());
        log.info("옵션 리스트 = {}", optionList);

        mv.addObject("product", product);
        mv.addObject("optionList", optionList);
//        mv.addObject("refProductList", refProductList);
        mv.setViewName("product/item2");

        return mv;
    }

    // # update : 2023-01-10
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 관련상품 이미지 + 경로 조회
    @GetMapping("/refItems")
    public ModelAndView selectRefItems(HttpServletRequest request, ModelAndView mv) {

        String productNo = request.getParameter("productNo");

        // 해당하는 상품과 같은 카테고리 상품들의 이미지와 상품번호 조회
        List<ProductDTO> refProductList = productService.selectRefProducts(productNo);

        log.info("에이잭스 테스트 = {}", refProductList);

        mv.addObject("refProductList", refProductList);
        mv.setViewName("/product/refItems" + "::#refItems");

        return mv;
    }

    // # update : 2023-01-12(최종수정)
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 해당하는 상품 위시리스트에 넣기
    @GetMapping("/wish")
    public ModelAndView insertWishProduct (Principal principal, HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) throws Exception {

        HashMap<String, String> wishMap = new HashMap<>();
        String productNo = request.getParameter("productNo");
        String orgProductNo = request.getParameter("orgProductNo");
        String userNo = request.getParameter("userNo");

        wishMap.put("userNo", userNo);
        wishMap.put("productNo", productNo);

        // 성공했을 시, 실패했을 시 각각 추가하기
        boolean result = productService.insertWishProduct(wishMap);

        mv.addObject("productNo", orgProductNo);
        mv.setViewName("redirect:/product/item2");

        if(result) {
            rttr.addFlashAttribute("successMessage", "찜하기 성공!");
        } else {
            rttr.addFlashAttribute("failMessage", "찜하기 실패..");
        }

        return mv;
    }

    // # update : 2023-01-12(최종수정)
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 해당하는 상품 장바구니에 넣기
    @GetMapping("/cart")
    public ModelAndView insertCartProduct (Principal principal, HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) throws Exception {

        HashMap<String, String> cartMap = new HashMap<>();
        String productNo = request.getParameter("productNo");
        String orgProductNo = request.getParameter("orgProductNo");
        String amount = request.getParameter("amount");
        log.info("원래 상품번호 = {}", orgProductNo);
        String userNo = request.getParameter("userNo");

        cartMap.put("productNo", productNo);
        cartMap.put("userNo", userNo);
        cartMap.put("amount", amount);

        // 성공했을 시, 실패했을 시 각각 추가하기
        boolean result = productService.insertCartProduct(cartMap);

        mv.addObject("productNo", orgProductNo);
        mv.setViewName("redirect:/product/item2");
        if(result) {
            rttr.addFlashAttribute("successMessage", "장바구니 담기 성공!");
        } else {
            rttr.addFlashAttribute("failMessage", "장바구니 담기 실패..");
        }

        return mv;
    }

    // # update : 2023-01-08(최종수정)
    // # title : 상품 구매페이지
    // # author : 오승재
    // # description : 상세페이지에서 받아온 데이터로 구매페이지 출력용
    @GetMapping("/purchase")
    public ModelAndView purchasePage(HttpServletRequest request, ModelAndView mv) {

        String productNo = request.getParameter("option");
        log.info("상품번호 = {}", productNo);
        String amount = request.getParameter("amount");
        log.info("수량 = {}", amount);
        String orgProductNo = request.getParameter("orgProductNo");

        ProductDTO product = productService.selectOneProduct(productNo);
        mv.addObject("product", product);
        mv.addObject("orgProductNo", orgProductNo);
        mv.addObject("amount", amount);

        mv.setViewName("/product/purchase");
        return mv;
    }

    // # update : 2023-01-12
    // # title : 상품 구매페이지
    // # author : 오승재
    // # description : 장바구니에서 받아온 데이터로 구매페이지 출력용
    @PostMapping("/purchase")
    public ModelAndView purchaseCart(HttpServletRequest request, ModelAndView mv) {

        String[] cartNos = request.getParameterValues("cartNo");
        HashMap<String, Object> map = new HashMap<>();
        map.put("cartNos", cartNos);
        
        List<ProductCartDTO> cartList = productService.selectCartList(map);

        mv.addObject("cartList", cartList);
        mv.setViewName("/product/purchase");

        return mv;
    }

    // # update : 2023-01-12
    // # title : 상품 구매페이지
    // # author : 오승재
    // # description : 상품 결제 일단은 db에 넣기만
    @PostMapping("/pay")
    public ModelAndView insertOrder(HttpServletRequest request, OrderDTO order, ModelAndView mv, RedirectAttributes rttr) throws Exception {

        String orderId = UUID.randomUUID().toString();

        order.setOrderId(orderId);
        if(order.getPackageId() == null){
            order.setPackageId("");
        }
        log.info("주문 정보 = {}", order);

        // 성공했을 시, 실패했을 시 각각 추가하기
        boolean result = productService.insertOrder(order);

        mv.addObject("productNo", request.getParameter("orgProductNo"));
        mv.setViewName("redirect:/product/item2");

        if(result) {
            rttr.addFlashAttribute("successMessage", "구매 성공!");
        } else {
            rttr.addFlashAttribute("failMessage", "구매 실패..");
        }

        return mv;
    }
}
