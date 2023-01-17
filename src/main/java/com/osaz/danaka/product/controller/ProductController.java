package com.osaz.danaka.product.controller;

import com.osaz.danaka.common.Pagenation;
import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.product.model.dto.OrderDTO;
import com.osaz.danaka.product.model.dto.ProductDTO;
import com.osaz.danaka.product.model.dto.imgPathDTO;
import com.osaz.danaka.product.model.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
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

    // # update : 2023-01-08(최종수정)
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 해당하는 상품 위시리스트에 넣기
    @GetMapping("/wish")
    public ModelAndView insertWishProduct (HttpSession session, HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) throws Exception {

        HashMap<String, String> wishMap = new HashMap<>();
        String productNo = request.getParameter("productNo");
        String orgProductNo = request.getParameter("orgProductNo");
        String userNo = (String) session.getAttribute("userNo");
        // 테스트용
//        String userNo = "1";

        wishMap.put("userNo", userNo);
        wishMap.put("productNo", productNo);

        // 성공했을 시, 실패했을 시 각각 추가하기
        boolean result = productService.insertWishProduct(wishMap);

        mv.addObject("productNo", orgProductNo);
        mv.setViewName("redirect:/product/item2");
        rttr.addFlashAttribute("successMessage", "찜하기 성공!");

        return mv;
    }

    // # update : 2023-01-08(최종수정)
    // # title : 상품 상세페이지
    // # author : 오승재
    // # description : 해당하는 상품 장바구니에 넣기
    @GetMapping("/cart")
    public ModelAndView insertCartProduct (HttpSession session, HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr) throws Exception {

        HashMap<String, String> cartMap = new HashMap<>();
        String productNo = request.getParameter("productNo");
        String orgProductNo = request.getParameter("orgProductNo");
        String amount = request.getParameter("amount");
        log.info("원래 상품번호 = {}", orgProductNo);
        String userNo = (String) session.getAttribute("userNo");
        // 테스트용
//        String userNo = "1";

        cartMap.put("productNo", productNo);
        cartMap.put("userNo", userNo);
        cartMap.put("amount", amount);

        // 성공했을 시, 실패했을 시 각각 추가하기
        boolean result = productService.insertCartProduct(cartMap);

        mv.addObject("productNo", orgProductNo);
        mv.setViewName("redirect:/product/item2");
        rttr.addFlashAttribute("successMessage", "장바구니 담기 성공!");

        return mv;
    }

    // # update : 2023-01-08(최종수정)
    // # title : 상품 구매페이지
    // # author : 오승재
    // # description : 상세페이지에서 받아온 데이터로 구매페이지 렌더링
    @GetMapping("/purchase")
    public ModelAndView purchasePage(HttpServletRequest request, ModelAndView mv) {

        String productNo = request.getParameter("option");
        log.info("상품번호 = {}", productNo);
        String amount = request.getParameter("amount");
        log.info("수량 = {}", amount);
//        String packageId = request.getParameter("packageId");

        if(productNo != null && !"".equals(productNo)) {

            ProductDTO product = productService.selectOneProduct(productNo);
            mv.addObject("product", product);
            mv.addObject("amount", amount);
        }

        mv.setViewName("/product/purchase");
        return mv;
    }

    // # update : 2023-01-10
    // # title : 상품 구매페이지
    // # author : 오승재
    // # description : 상품 결제 일단은 db에 넣기만
    @PostMapping("/pay")
    public ModelAndView insertOrder(HttpSession session, OrderDTO order, ModelAndView mv, RedirectAttributes rttr) throws Exception {


        String userNo = (String) session.getAttribute("userNo");
        String orderId = UUID.randomUUID().toString();

        order.setUserNo(userNo);
        order.setOrderId(orderId);
        if(order.getPackageId() == null){
            order.setPackageId("");
        }
        log.info("주문 정보 = {}", order);

        // 성공했을 시, 실패했을 시 각각 추가하기
        boolean result = productService.insertOrder(order);

        mv.addObject("productNo", order.getProductNo());

        if(result) {
            mv.setViewName("redirect:/product/item2");
            rttr.addFlashAttribute("successMessage", "구매 성공!");
        } else {
            mv.setViewName("redirect:/product/item2");
            rttr.addFlashAttribute("successMessage", "구매 실패..");
        }

        return mv;
    }

    @GetMapping("productEnroll")
    public void insertProduct() {}

    @PostMapping("productEnroll")
    public ModelAndView insertProduct(ModelAndView mv, HttpServletRequest request, ProductDTO product, RedirectAttributes rttr, @RequestParam(name="file", required = false) MultipartFile[] file){

        /* T_PRODUCT 테이블 데이터 먼저 추가 */
        productService.insertProduct(product);

        /* T_PRODUCT 테이블 마지막 상품 번호 구하기 */
        String lastNum = productService.selectProductLastNum();

        /* 이미지 저장될 패키지 경로 변수 */
        String imgCategoryPath = null;

        /* 케이스에 따라 카테고리 테이블 데이터 추가 */
        HashMap<String, String> categoryMap = new HashMap<>();

        String category = request.getParameter("categoryCode"); //카테고리코드 추출하여 변수 저장

        if(Objects.equals(category, "RD")){
            String rodModel = request.getParameter("rodModel");
            String reelType = request.getParameter("reelType");
            String lineMin = request.getParameter("lineMin");
            String lineMax = request.getParameter("lineMax");
            String rodPrice = request.getParameter("rodPrice");
            categoryMap.put("rodModel", rodModel);
            categoryMap.put("reelType", reelType);
            categoryMap.put("lineMin", lineMin);
            categoryMap.put("lineMax", lineMax);
            categoryMap.put("rodPrice", rodPrice);
            categoryMap.put("lastNum", lastNum);
            categoryMap.put("category", "T_ROD");   //동적쿼리에 사용될 테이블명 전달
            imgCategoryPath = "rod";    //카테고리코드에 따라 동적으로 변결될 하위폴더명 설정
        } else if (Objects.equals(category, "RL")) {
            String reelModel = request.getParameter("reelModel");
            String reelType = request.getParameter("reelType");
            String reelPrice = request.getParameter("reelPrice");
            categoryMap.put("reelModel", reelModel);
            categoryMap.put("reelType", reelType);
            categoryMap.put("reelPrice", reelPrice);
            categoryMap.put("lastNum", lastNum);
            categoryMap.put("category", "T_REEL");
            imgCategoryPath = "reel";
        } else if (Objects.equals(category, "LN")) {
            String lineSize = request.getParameter("lineSize");
            String linePrice = request.getParameter("linePrice");
            categoryMap.put("lineSize", lineSize);
            categoryMap.put("linePrice", linePrice);
            categoryMap.put("lastNum", lastNum);
            categoryMap.put("category", "T_LINE");
            imgCategoryPath = "line";
        } else if (category == null){
            System.out.println("값 없음");
        }
        productService.insertCategory(categoryMap);

        /* 전달받은 파일 배열을 반복문을 이용하여 이름변경처리 및 T_IMG_PATH 데이터 추가 */
        for (int i = 0; i < file.length; i++) {
            String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/image/" + imgCategoryPath;
            UUID uuid = UUID.randomUUID();
            String changeName = uuid+"_"+file[i].getOriginalFilename();
            File saveFile = new File(projectPath, changeName);

            /* 같은 input 의 name 속성 value 값 배열로 받기 */
            String[] imgCategory = request.getParameterValues("imgCategory");

            try {
                file[i].transferTo(saveFile);

                imgPathDTO imgFile = new imgPathDTO();

                imgFile.setProductNo(Integer.parseInt(lastNum));
                imgFile.setImgCategory(imgCategory[i]);
                imgFile.setOrgFileName(file[i].getOriginalFilename());
                imgFile.setSysFileName(changeName);
                imgFile.setSavePath("/image/" + imgCategoryPath + "/" + changeName);

                productService.saveFile(imgFile);
            } catch (Exception e) {
                System.out.println("Exception" + e);
                throw new RuntimeException(e);
            }
        }

        mv.setViewName("redirect:/product/list2?categoryCode=" + category);
        rttr.addFlashAttribute("successMessage", "상품 등록 완료!");

        return mv;

    }


    /* @GetMapping("productEnroll")
    public void insertProduct() {}

     *//*@RequestParam(name="file", required = false) MultipartFile file,*//*
    @PostMapping("productEnroll")
    public ModelAndView insertProduct(ModelAndView mv, HttpServletRequest request, ProductDTO product, RedirectAttributes rttr, @RequestParam(name="file", required = false) MultipartFile[] file){

         *//* T_PRODUCT 테이블 데이터 먼저 추가 *//*
        productService.insertProduct(product);

         *//* T_PRODUCT 테이블 마지막 상품 번호 구하기 *//*
        String lastNum = productService.selectProductLastNum();

         *//* 이미지 저장될 패키지 경로 변수 *//*
        String imgCategoryPath = null;

         *//* 케이스에 따라 카테고리 테이블 데이터 추가 *//*
        HashMap<String, String> categoryMap = new HashMap<>();

        String category = request.getParameter("categoryCode"); //카테고리코드 추출하여 변수 저장

        if(Objects.equals(category, "RD")){
            String rodModel = request.getParameter("rodModel");
            String reelType = request.getParameter("reelType");
            String lineMin = request.getParameter("lineMin");
            String lineMax = request.getParameter("lineMax");
            String rodPrice = request.getParameter("rodPrice");
            categoryMap.put("rodModel", rodModel);
            categoryMap.put("reelType", reelType);
            categoryMap.put("lineMin", lineMin);
            categoryMap.put("lineMax", lineMax);
            categoryMap.put("rodPrice", rodPrice);
            categoryMap.put("lastNum", lastNum);
            categoryMap.put("category", "T_ROD");   //동적쿼리에 사용될 테이블명 전달
            imgCategoryPath = "rod";    //카테고리코드에 따라 동적으로 변결될 하위폴더명 설정
        } else if (Objects.equals(category, "RL")) {
            String reelModel = request.getParameter("reelModel");
            String reelType = request.getParameter("reelType");
            String reelPrice = request.getParameter("reelPrice");
            categoryMap.put("reelModel", reelModel);
            categoryMap.put("reelType", reelType);
            categoryMap.put("reelPrice", reelPrice);
            categoryMap.put("lastNum", lastNum);
            categoryMap.put("category", "T_REEL");
            imgCategoryPath = "reel";
        } else if (Objects.equals(category, "LN")) {
            String lineSize = request.getParameter("lineSize");
            String linePrice = request.getParameter("linePrice");
            categoryMap.put("lineSize", lineSize);
            categoryMap.put("linePrice", linePrice);
            categoryMap.put("lastNum", lastNum);
            categoryMap.put("category", "T_LINE");
            imgCategoryPath = "line";
        } else if (category == null){
            System.out.println("값 없음");
        }
        productService.insertCategory(categoryMap);

        // List<ProductDTO> fileList = new ArrayList<>();

         *//* 이미지 저장될 패키지 경로 변수화, 카테고리코드에 따라 하위폴더 지정 ( *//*
        // String imgCategoryPath = null;

        // if(Objects.equals(category, "RD")){
        //     imgCategoryPath = "rod";
        // } else if (Objects.equals(category, "RL")) {
        //     imgCategoryPath = "reel";
        // } else if (Objects.equals(category, "LN")) {
        //     imgCategoryPath = "line";
        // } else if (category == null){
        //     System.out.println("값 없음");
        // }

         *//* 전달받은 파일 배열을 반복문을 이용하여 이름변경처리 및 T_IMG_PATH 데이터 추가 *//*
        for (int i = 0; i < file.length; i++) {
            String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/image/" + imgCategoryPath;
            UUID uuid = UUID.randomUUID();
            String changeName = uuid+"_"+file[i].getOriginalFilename();
            File saveFile = new File(projectPath, changeName);

             *//* 같은 input 의 name 속성 value 값 배열로 받기 *//*
            String[] imgCategory = request.getParameterValues("imgCategory");

            try {
                file[i].transferTo(saveFile);

                imgPathDTO imgFile = new imgPathDTO();

                imgFile.setProductNo(Integer.parseInt(lastNum));
                imgFile.setImgCategory(imgCategory[i]);
                imgFile.setOrgFileName(file[i].getOriginalFilename());
                imgFile.setSysFileName(changeName);
                imgFile.setSavePath("/image/" + imgCategoryPath + "/" + changeName);

                productService.saveFile(imgFile);
            } catch (Exception e) {
                System.out.println("Exception" + e);
                throw new RuntimeException(e);
            }
        }

        mv.setViewName("redirect:/product/list2?categoryCode=" + category);
        rttr.addFlashAttribute("successMessage", "상품 등록 완료!");

        return mv;

    } */
}
