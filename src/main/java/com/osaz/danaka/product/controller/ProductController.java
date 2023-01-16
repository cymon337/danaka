package com.osaz.danaka.product.controller;

import com.osaz.danaka.product.model.dto.ProductDTO;
import com.osaz.danaka.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ModelAndView selectByCategory(@RequestParam String categoryCode, ModelAndView mv){

        List<ProductDTO> productList = productService.selectByCategory(categoryCode);

        mv.addObject(productList);
        mv.setViewName("product/list");

        return mv;
    }

    @GetMapping("/list/item")
    public ModelAndView selectOne(@RequestParam String productNo, ModelAndView mv){

        ProductDTO product = productService.selectOne(productNo);

        mv.addObject(product);
        mv.setViewName("product/list/item");

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
