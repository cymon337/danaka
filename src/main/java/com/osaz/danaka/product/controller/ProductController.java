package com.osaz.danaka.product.controller;

import com.osaz.danaka.common.Pagenation;
import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.product.model.dto.*;
import com.osaz.danaka.product.model.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

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
    public ModelAndView selectByCategory(@RequestParam(required = false) String currentPage, @RequestParam(required = false) String searchCondition,
                                         @RequestParam(required = false) String searchValue, @RequestParam(required = false) String categoryCode,
                                         @RequestParam(required = false) String orderCondition, ModelAndView mv) {

        /* 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         * */
        int pageNo = 1;

        if (currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        /* 0보다 작은 숫자값을 입력해도 1페이지를 보여준다 */
        if (pageNo <= 0) {
            pageNo = 1;
        }

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
        int limit = 10;        //얘도 파라미터로 전달받아도 된다.
        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {
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
    public ModelAndView selectOneProduct(@AuthenticationPrincipal MemberDTO member, String productNo, ModelAndView mv) {

        HashMap<String, String> map = new HashMap<>();
        map.put("userNo", member.getUserNo());
        map.put("productNo", productNo);
        // 위시리스트 체크 조회
        boolean wished = productService.selectWishCheck(map);
        // 해당하는 상품 DTO 조회
        ProductDTO product = productService.selectOneProduct(productNo);
        // 해당하는 상품에 속한 옵션 조회
        List<ProductDTO> optionList = productService.selectOptionList(product.getProductName());
        log.info("옵션 리스트 = {}", optionList);

        mv.addObject("product", product);
        mv.addObject("optionList", optionList);
        mv.addObject("wishCheck", wished);
        mv.setViewName("product/item2");

        return mv;
    }

    // # update : 2023-01-10
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 관련상품 이미지 + 경로 조회
    @GetMapping("/refItems")
    public ModelAndView selectRefItems(String productNo, ModelAndView mv) {

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
    // # description : 해당하는 상품 위시리스트에 넣기 / 지우기
    @GetMapping("/wish")
    public ModelAndView insertWishProduct(@AuthenticationPrincipal MemberDTO member, String productNo, String orgProductNo, ModelAndView mv, RedirectAttributes rttr) {

        HashMap<String, String> wishMap = new HashMap<>();
        String userNo = member.getUserNo();

        wishMap.put("userNo", userNo);
        wishMap.put("productNo", productNo);

        boolean wishCheck = productService.selectWishCheck(wishMap);
        boolean result;

        if(wishCheck) {

            try {
                result = productService.deleteWish(wishMap);

                if (result) {
                    rttr.addFlashAttribute("successMessage", "찜 취소 성공!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                rttr.addFlashAttribute("failMessage", "찜 취소 실패..");
            }
        } else {

            try {
                result = productService.insertWishProduct(wishMap);

                if (result) {
                    rttr.addFlashAttribute("successMessage", "찜하기 성공!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                rttr.addFlashAttribute("failMessage", "찜하기 실패..");
            }
        }

        mv.addObject("productNo", orgProductNo);
        mv.setViewName("redirect:/product/item2");

        return mv;
    }

    // # update : 2023-01-12(최종수정)
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 해당하는 상품 장바구니에 넣기
    @GetMapping("/cart")
    public ModelAndView insertCartProduct(@AuthenticationPrincipal MemberDTO member, String productNo, String orgProductNo, String amount, ModelAndView mv, RedirectAttributes rttr) {

        HashMap<String, String> cartMap = new HashMap<>();
        log.info("원래 상품번호 = {}", orgProductNo);
        log.info("로그인 회원 = {}", member);
        String userNo = member.getUserNo();

        cartMap.put("productNo", productNo);
        cartMap.put("userNo", userNo);
        cartMap.put("amount", amount);

        boolean result;

        try {
            result = productService.insertCartProduct(cartMap);

            if (result) {
                rttr.addFlashAttribute("successMessage", "장바구니 담기 성공!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("failMessage", "장바구니 담기 실패..");
        }

        mv.addObject("productNo", orgProductNo);
        mv.setViewName("redirect:/product/item2");

        return mv;
    }

    // # update : 2023-01-08(최종수정)
    // # title : 상품 구매페이지
    // # author : 오승재
    // # description : 상세페이지에서 받아온 데이터로 구매페이지 출력용
    @GetMapping("/purchase")
    public ModelAndView purchasePage(@RequestParam(value = "option") String productNo, String amount, String orgProductNo, ModelAndView mv) {

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
    public ModelAndView purchaseCart(@RequestParam(value = "cartNoList") String cartNos, ModelAndView mv) {

        String[] cartNumbers = cartNos.split(",");

            HashMap<String, Object> map = new HashMap<>();
            map.put("cartNos", cartNumbers);

            List<ProductCartDTO> cartList = productService.selectCartList(map);

            mv.addObject("cartList", cartList);
            mv.setViewName("/product/purchase");

        return mv;
    }

    // # update : 2023-01-12
    // # title : 상품 구매페이지
    // # author : 오승재
    // # description : 상품 결제 일단은 db에 넣기만
    @PostMapping(value = "/pay")
    public ModelAndView insertOrder(@RequestParam(value = "productNo", required = false) String[] productNos, @RequestParam(value = "totPrice") String[] totPrices,
                                    @RequestParam(value = "packageId", required = false, defaultValue = "0") String[] packageIds, String userNo, @RequestParam(value = "amount") String[] amounts, String address,
                                    @RequestParam(required = false) String orderRequest, String payType, String orgProductNo, ModelAndView mv, RedirectAttributes rttr) {

        log.info("널체크 = {}", packageIds);

        String orderId = UUID.randomUUID().toString();
        List<OrderDTO> orderList = new ArrayList<>();
        OrderDTO order;

        for (int i = 0; i < productNos.length; i++) {
            order = new OrderDTO();

            order.setUserNo(userNo);
            order.setProductNo(productNos[i]);
            order.setOrderId(orderId);
            order.setPackageId(packageIds[i]);
            order.setAddress(address);
            order.setAmount(amounts[i]);
            order.setTotPrice(totPrices[i]);
            order.setOrderRequest(orderRequest);
            order.setPayType(payType);

            orderList.add(order);
        }
        log.info("주문 목록 = {}", orderList);

        boolean result;
        try {
            result = productService.insertOrder(orderList);

            if (result) {
                rttr.addFlashAttribute("successMessage", "구매 성공!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("failMessage", "구매 실패..");
        }

        if (orgProductNo != null) {
            mv.addObject("productNo", orgProductNo);
            mv.setViewName("redirect:/product/item2");
        } else {
            mv.addObject("categoryCode", "RD");
            mv.setViewName("redirect:/product/list2");
        }
        return mv;
    }

    // # update : 2023-01-14
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 리뷰 출력 ajax 메소드
    @GetMapping("/review")
    public ModelAndView selectReview(@AuthenticationPrincipal MemberDTO member, @RequestParam(required = false)String currentPage, String productNo, String productName,
                                     ModelAndView mv) {

        HashMap<String, String> map = new HashMap<>();
        map.put("productName", productName);
        map.put("userNo", member.getUserNo());
        map.put("productNo", productNo);

        log.info("map값 체크 = {}", map);
        HashMap orderInfo = productService.selectOrder(map);
        log.info("마이바티스에서 받은 해시맵 체크 = {}", orderInfo);

        int pageNo = 1;
        if (currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        if (pageNo <= 0) {
            pageNo = 1;
        }

        int totalCount = productService.selectTotalReviewCount(map);
        int limit = 10;
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        selectCriteria.setSearchValue(productName);
        log.info("검색조건 = {}", selectCriteria);
        List<ReviewDTO> reviewList = productService.selectReviewList(selectCriteria);

        mv.addObject("orderInfo", orderInfo);
        mv.addObject("reviewList", reviewList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.setViewName("product/productBoard");

        return mv;
    }

    // # update : 2023-01-15
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 문의 출력 ajax 메소드
    @GetMapping("/qna")
    public ModelAndView selectQna(@RequestParam(required = false) String currentPage, String productNo, ModelAndView mv) {

        int pageNo = 1;
        if (currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        if (pageNo <= 0) {
            pageNo = 1;
        }

        int totalCount = productService.selectTotalQnaCount(productNo);
        int limit = 10;
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        selectCriteria.setSearchValue(productNo);
        List<QnaDTO> qnaList = productService.selectQnaList(selectCriteria);

        mv.addObject("qnaList", qnaList);
        mv.addObject("selectCriteria", selectCriteria);
        mv.setViewName("product/productBoard");

        return mv;
    }

    // # update : 2023-01-15
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 리뷰 insert ajax메소드
    @PostMapping(value = "/insertReview", produces="application/json; charset=UTF-8")
    @ResponseBody
    public String insertReview(ReviewDTO review) {

        log.info("새 리뷰 = {}", review);
        String msg = "";

        if(review.getReviewBody() == null || "".equals(review.getReviewBody())) {
            msg = "리뷰 내용을 작성해주세요.";
            return msg;
        }

        try {
            boolean result = productService.insertReview(review);
            if(result) {
                msg = "리뷰 등록 성공!";
            }
        } catch (Exception e) {
            msg = "리뷰 등록 실패..";
            e.printStackTrace();
            return msg;
        }

        return msg;
    }

    // # update : 2023-01-15
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 문의 insert ajax메소드
    @PostMapping(value = "/insertQna", produces="application/json; charset=UTF-8")
    @ResponseBody
    public String insertQna(QnaDTO qna) {

        String msg = "";
        log.info("새 상품문의 = {}", qna);

        if(qna.getSecretStatus() == null) {
            qna.setSecretStatus("N");
        }

        if(qna.getQnaBody() == null || "".equals(qna.getQnaBody())) {
            msg = "문의 내용을 작성해주세요.";
            return msg;
        }

        try {
            boolean result = productService.insertQna(qna);
            if(result) {
                msg = "문의 등록 성공!";
            }
        } catch (Exception e) {
            msg = "문의 등록 실패..";
            e.printStackTrace();
            return msg;
        }

        return msg;
    }

    // # update : 2023-01-16
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 리뷰 삭제 ajax메소드
    @GetMapping(value = "/deleteReview", produces="application/json; charset=UTF-8")
    @ResponseBody
    public String deleteReview(String reviewNo) {

        String msg = " ";

        try {
            boolean result = productService.deleteReview(reviewNo);
            if(result) {
                msg = "리뷰 삭제 성공!";
            }
        } catch (Exception e) {
            msg = "리뷰 삭제 실패..";
            e.printStackTrace();
            return msg;
        }

        return msg;
    }

    // # update : 2023-01-16
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 문의 삭제 ajax메소드
    @GetMapping(value = "/deleteQna", produces="application/json; charset=UTF-8")
    @ResponseBody
    public String deleteQna(String qnaNo) {

        String msg = " ";

        try {
            boolean result = productService.deleteQna(qnaNo);
            if(result) {
                msg = "문의 삭제 성공!";
            }
        } catch (Exception e) {
            msg = "문의 삭제 실패..";
            e.printStackTrace();
            return msg;
        }

        return msg;
    }

    // # update : 2023-01-16
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 리뷰 수정 ajax메소드
    @PostMapping(value = "/updateReview", produces="application/json; charset=UTF-8")
    @ResponseBody
    public String updateReview(String reviewNo, String updateReviewBody) {

        String msg = " ";

        if(updateReviewBody == null || "".equals(updateReviewBody)) {
            msg = "수정할 내용을 작성해주세요.";

            return msg;
        }

        HashMap<String, String> updateMap = new HashMap<>();
        updateMap.put("reviewNo", reviewNo);
        updateMap.put("reviewBody", updateReviewBody);

        try {
            boolean result = productService.updateReview(updateMap);
            if(result) {
                msg = "리뷰 수정 성공!";
            }
        } catch (Exception e) {
            msg = "리뷰 수정 실패..";
            e.printStackTrace();
            return msg;
        }

        return msg;
    }

    // # update : 2023-01-16
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 상품 문의 수정 ajax메소드
    @PostMapping(value = "/updateQna", produces="application/json; charset=UTF-8")
    @ResponseBody
    public String updateQna(String qnaNo, @RequestParam(required = false) String updateQnaBody, @RequestParam(required = false) String qnaReply) {

        String msg = " ";
        HashMap<String, String> updateMap = new HashMap<>();
        updateMap.put("qnaNo", qnaNo);

        if(updateQnaBody != null && !"".equals(updateQnaBody)) {
            updateMap.put("qnaBody", updateQnaBody);
        } else if(qnaReply != null && !"".equals(qnaReply)) {
            updateMap.put("qnaReply", qnaReply);
        } else {
            msg = "내용을 작성해주세요.";

            return msg;
        }

        try {
            boolean result = productService.updateQna(updateMap);
            if(result) {
                msg = "성공!";
            }
        } catch (Exception e) {
            msg = "실패..";
            e.printStackTrace();
            return msg;
        }

        return msg;
    }
}
